-- V004__create_functions_procedures.sql
-- Criação de funções e procedimentos úteis para o sistema

-- Função para calcular distância entre duas posições
CREATE OR REPLACE FUNCTION calcular_distancia(
    x1 NUMBER, y1 NUMBER, x2 NUMBER, y2 NUMBER
) RETURN NUMBER IS
    distancia NUMBER;
BEGIN
    distancia := SQRT(POWER(x2 - x1, 2) + POWER(y2 - y1, 2));
    RETURN ROUND(distancia, 2);
END calcular_distancia;
/

-- Procedimento para limpar dados antigos
CREATE OR REPLACE PROCEDURE limpar_dados_antigos IS
    dias_historico NUMBER;
    dias_alertas NUMBER;
    linhas_removidas NUMBER;
BEGIN
    -- Buscar configurações
    SELECT CAST(valor AS NUMBER) INTO dias_historico 
    FROM Sistema_config WHERE chave = 'DIAS_MANTER_HISTORICO';
    
    SELECT CAST(valor AS NUMBER) INTO dias_alertas 
    FROM Sistema_config WHERE chave = 'DIAS_MANTER_ALERTAS';
    
    -- Limpar histórico antigo
    DELETE FROM Historico_posicao 
    WHERE data_atualizacao < SYSTIMESTAMP - INTERVAL dias_historico DAY;
    linhas_removidas := SQL%ROWCOUNT;
    
    DBMS_OUTPUT.PUT_LINE('Removidas ' || linhas_removidas || ' entradas do histórico');
    
    -- Limpar alertas antigos
    DELETE FROM Alerta_evento 
    WHERE data_geracao < SYSTIMESTAMP - INTERVAL dias_alertas DAY;
    linhas_removidas := SQL%ROWCOUNT;
    
    DBMS_OUTPUT.PUT_LINE('Removidos ' || linhas_removidas || ' alertas antigos');
    
    -- Limpar logs de auditoria antigos (manter 6 meses)
    DELETE FROM Audit_log 
    WHERE data_operacao < SYSTIMESTAMP - INTERVAL '6' MONTH;
    linhas_removidas := SQL%ROWCOUNT;
    
    DBMS_OUTPUT.PUT_LINE('Removidos ' || linhas_removidas || ' logs de auditoria');
    
    COMMIT;
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Erro na limpeza: ' || SQLERRM);
        RAISE;
END limpar_dados_antigos;
/

-- Procedimento para gerar alerta de bateria baixa
CREATE OR REPLACE PROCEDURE verificar_bateria_sensores IS
    limite_bateria NUMBER;
    CURSOR sensores_baixa_bateria IS
        SELECT s.id_sensor_iot, s.bateria_percentual, m.id_moto, m.placa
        FROM Sensor_iot s
        JOIN Moto m ON s.id_sensor_iot = m.id_sensor_iot
        WHERE s.bateria_percentual < limite_bateria;
BEGIN
    -- Buscar limite configurado
    SELECT CAST(valor AS NUMBER) INTO limite_bateria 
    FROM Sistema_config WHERE chave = 'LIMITE_BATERIA_BAIXA';
    
    -- Verificar sensores e gerar alertas
    FOR sensor IN sensores_baixa_bateria LOOP
        -- Verificar se já não existe alerta recente para esta moto
        IF NOT EXISTS (
            SELECT 1 FROM Alerta_evento 
            WHERE id_moto = sensor.id_moto 
            AND tipo_alerta = 'BATERIA_BAIXA'
            AND data_geracao > SYSTIMESTAMP - INTERVAL '4' HOUR
        ) THEN
            -- Inserir novo alerta
            INSERT INTO Alerta_evento (tipo_alerta, data_geracao, id_moto)
            VALUES ('BATERIA_BAIXA', SYSTIMESTAMP, sensor.id_moto);
            
            DBMS_OUTPUT.PUT_LINE('Alerta criado para moto ' || sensor.placa || 
                               ' (bateria: ' || sensor.bateria_percentual || '%)');
        END IF;
    END LOOP;
    
    COMMIT;
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Erro na verificação de bateria: ' || SQLERRM);
        RAISE;
END verificar_bateria_sensores;
/

-- Função para obter estatísticas do pátio
CREATE OR REPLACE FUNCTION obter_estatisticas_patio(p_id_patio NUMBER) 
RETURN VARCHAR2 IS
    v_stats VARCHAR2(1000);
    v_total_motos NUMBER;
    v_capacidade NUMBER;
    v_taxa_ocupacao NUMBER;
    v_motos_disponveis NUMBER;
    v_motos_alugadas NUMBER;
    v_motos_manutencao NUMBER;
BEGIN
    -- Buscar dados do pátio
    SELECT capacidade_maxima INTO v_capacidade
    FROM Patio WHERE id_patio = p_id_patio;
    
    -- Contar motos por status
    SELECT COUNT(*) INTO v_total_motos
    FROM Moto WHERE id_patio = p_id_patio;
    
    SELECT COUNT(*) INTO v_motos_disponveis
    FROM Moto WHERE id_patio = p_id_patio AND status = 'DISPONIVEL';
    
    SELECT COUNT(*) INTO v_motos_alugadas
    FROM Moto WHERE id_patio = p_id_patio AND status = 'ALUGADA';
    
    SELECT COUNT(*) INTO v_motos_manutencao
    FROM Moto WHERE id_patio = p_id_patio AND status = 'MANUTENCAO';
    
    -- Calcular taxa de ocupação
    IF v_capacidade > 0 THEN
        v_taxa_ocupacao := ROUND((v_total_motos * 100.0) / v_capacidade, 1);
    ELSE
        v_taxa_ocupacao := 0;
    END IF;
    
    -- Montar string de estatísticas
    v_stats := 'Total: ' || v_total_motos || 
               ' | Disponíveis: ' || v_motos_disponveis ||
               ' | Alugadas: ' || v_motos_alugadas ||
               ' | Manutenção: ' || v_motos_manutencao ||
               ' | Ocupação: ' || v_taxa_ocupacao || '%';
    
    RETURN v_stats;
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 'Pátio não encontrado';
    WHEN OTHERS THEN
        RETURN 'Erro: ' || SQLERRM;
END obter_estatisticas_patio;
/

-- Trigger para auditoria automática na tabela Moto
CREATE OR REPLACE TRIGGER trg_moto_audit
    AFTER INSERT OR UPDATE OR DELETE ON Moto
    FOR EACH ROW
DECLARE
    v_operacao VARCHAR2(10);
    v_dados_antigos CLOB;
    v_dados_novos CLOB;
    v_registro_id NUMBER;
BEGIN
    IF INSERTING THEN
        v_operacao := 'INSERT';
        v_dados_novos := 'placa:' || :NEW.placa || '|status:' || :NEW.status || 
                        '|patio:' || :NEW.id_patio || '|modelo:' || :NEW.Modelo_id_modelo;
        v_registro_id := :NEW.id_moto;
    ELSIF UPDATING THEN
        v_operacao := 'UPDATE';
        v_dados_antigos := 'placa:' || :OLD.placa || '|status:' || :OLD.status || 
                          '|patio:' || :OLD.id_patio || '|modelo:' || :OLD.Modelo_id_modelo;
        v_dados_novos := 'placa:' || :NEW.placa || '|status:' || :NEW.status || 
                        '|patio:' || :NEW.id_patio || '|modelo:' || :NEW.Modelo_id_modelo;
        v_registro_id := :NEW.id_moto;
    ELSIF DELETING THEN
        v_operacao := 'DELETE';
        v_dados_antigos := 'placa:' || :OLD.placa || '|status:' || :OLD.status || 
                          '|patio:' || :OLD.id_patio || '|modelo:' || :OLD.Modelo_id_modelo;
        v_registro_id := :OLD.id_moto;
    END IF;
    
    INSERT INTO Audit_log (tabela_nome, operacao, registro_id, data_operacao, 
                          dados_anteriores, dados_novos)
    VALUES ('MOTO', v_operacao, v_registro_id, SYSTIMESTAMP, 
            v_dados_antigos, v_dados_novos);
            
EXCEPTION
    WHEN OTHERS THEN
        -- Log do erro sem impedir a operação principal
        NULL;
END trg_moto_audit;
/

-- Criar job para executar limpeza automática semanalmente
BEGIN
    DBMS_SCHEDULER.CREATE_JOB (
        job_name        => 'JOB_LIMPEZA_DADOS',
        job_type        => 'PLSQL_BLOCK',
        job_action      => 'BEGIN limpar_dados_antigos; END;',
        start_date      => SYSTIMESTAMP,
        repeat_interval => 'FREQ=WEEKLY;BYDAY=SUN;BYHOUR=2',
        enabled         => TRUE,
        comments        => 'Limpeza automática de dados antigos'
    );
END;
/

-- Criar job para verificação de bateria a cada 2 horas
BEGIN
    DBMS_SCHEDULER.CREATE_JOB (
        job_name        => 'JOB_VERIFICAR_BATERIA',
        job_type        => 'PLSQL_BLOCK', 
        job_action      => 'BEGIN verificar_bateria_sensores; END;',
        start_date      => SYSTIMESTAMP,
        repeat_interval => 'FREQ=HOURLY;INTERVAL=2',
        enabled         => TRUE,
        comments        => 'Verificação automática de bateria dos sensores'
    );
END;
/

COMMIT;