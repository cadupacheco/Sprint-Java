-- V001__add_flyway_to_existing_schema.sql
-- Adiciona controle Flyway ao schema existente (sem recriar tabelas)

-- Configuração inicial do Flyway para schema existente
-- Assumindo que as tabelas já existem conforme seu SQL original

-- Adicionar colunas que podem estar faltando para Spring Boot/JPA
-- Só executa se não existir

BEGIN
    -- Verificar se coluna 'perfil' existe em Usuario_sistema
    DECLARE
        v_count INTEGER;
    BEGIN
        SELECT COUNT(*) INTO v_count
        FROM user_tab_columns
        WHERE table_name = 'USUARIO_SISTEMA' AND column_name = 'PERFIL';

        IF v_count = 0 THEN
            EXECUTE IMMEDIATE 'ALTER TABLE Usuario_sistema ADD (perfil VARCHAR2(20) DEFAULT ''OPERADOR'')';
        END IF;
    END;

    -- Verificar se coluna 'ativo' existe em Usuario_sistema
    DECLARE
        v_count INTEGER;
    BEGIN
        SELECT COUNT(*) INTO v_count
        FROM user_tab_columns
        WHERE table_name = 'USUARIO_SISTEMA' AND column_name = 'ATIVO';

        IF v_count = 0 THEN
            EXECUTE IMMEDIATE 'ALTER TABLE Usuario_sistema ADD (ativo NUMBER(1) DEFAULT 1)';
        END IF;
    END;

    -- Ajustar tamanho da senha para suportar BCrypt (255 chars)
    DECLARE
        v_count INTEGER;
    BEGIN
        SELECT data_length INTO v_count
        FROM user_tab_columns
        WHERE table_name = 'USUARIO_SISTEMA' AND column_name = 'SENHA';

        IF v_count < 255 THEN
            EXECUTE IMMEDIATE 'ALTER TABLE Usuario_sistema MODIFY (senha VARCHAR2(255))';
        END IF;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            NULL; -- Coluna não existe, será adicionada depois
    END;
END;
/

-- Adicionar indices se não existirem
DECLARE
    v_count INTEGER;
BEGIN
    SELECT COUNT(*) INTO v_count
    FROM user_indexes
    WHERE index_name = 'IDX_USUARIO_EMAIL';

    IF v_count = 0 THEN
        EXECUTE IMMEDIATE 'CREATE INDEX idx_usuario_email ON Usuario_sistema(email)';
    END IF;
END;
/