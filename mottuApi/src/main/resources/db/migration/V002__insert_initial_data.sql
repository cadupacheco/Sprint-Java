-- V002__insert_initial_data.sql
-- Inserção de dados iniciais para teste e funcionamento básico

-- Inserir usuários padrão
INSERT INTO Usuario_sistema (nome, email, senha, perfil, ativo) VALUES 
('Administrador', 'admin@mottu.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKXOGP0DUjdLLK/x8z2Icd0hLBqi', 'ADMIN', 1);
-- Senha: admin123

INSERT INTO Usuario_sistema (nome, email, senha, perfil, ativo) VALUES 
('Operador Teste', 'operador@mottu.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKXOGP0DUjdLLK/x8z2Icd0hLBqi', 'OPERADOR', 1);
-- Senha: admin123

INSERT INTO Usuario_sistema (nome, email, senha, perfil, ativo) VALUES 
('Manutenção Teste', 'manutencao@mottu.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKXOGP0DUjdLLK/x8z2Icd0hLBqi', 'MANUTENCAO', 1);
-- Senha: admin123

-- Inserir modelos de moto
INSERT INTO Modelo (fabricante, nome_modelo, cilindrada, tipo) VALUES 
('Honda', 'CB 600F', 600, 'Naked');

INSERT INTO Modelo (fabricante, nome_modelo, cilindrada, tipo) VALUES 
('Yamaha', 'MT-07', 689, 'Naked');

INSERT INTO Modelo (fabricante, nome_modelo, cilindrada, tipo) VALUES 
('Kawasaki', 'Ninja 400', 399, 'Esportiva');

INSERT INTO Modelo (fabricante, nome_modelo, cilindrada, tipo) VALUES 
('Honda', 'PCX 160', 160, 'Scooter');

INSERT INTO Modelo (fabricante, nome_modelo, cilindrada, tipo) VALUES 
('BMW', 'G 310 GS', 313, 'Adventure');

-- Inserir pátios
INSERT INTO Patio (nome_patio, localizacao_patio, area_total, capacidade_maxima) VALUES 
('Pátio Central SP', 'São Paulo - Centro', 1500.50, 50);

INSERT INTO Patio (nome_patio, localizacao_patio, area_total, capacidade_maxima) VALUES 
('Pátio Zona Sul', 'São Paulo - Vila Madalena', 800.75, 30);

INSERT INTO Patio (nome_patio, localizacao_patio, area_total, capacidade_maxima) VALUES 
('Pátio RJ Copacabana', 'Rio de Janeiro - Copacabana', 1200.00, 40);

INSERT INTO Patio (nome_patio, localizacao_patio, area_total, capacidade_maxima) VALUES 
('Pátio BH Centro', 'Belo Horizonte - Centro', 900.25, 35);

-- Inserir sensores IoT
INSERT INTO Sensor_iot (tipo_sensor, data_transmissao, bateria_percentual) VALUES 
('GPS', SYSDATE, 95.5);

INSERT INTO Sensor_iot (tipo_sensor, data_transmissao, bateria_percentual) VALUES 
('GPS', SYSDATE - 1, 78.2);

INSERT INTO Sensor_iot (tipo_sensor, data_transmissao, bateria_percentual) VALUES 
('RFID', SYSDATE, 89.7);

INSERT INTO Sensor_iot (tipo_sensor, data_transmissao, bateria_percentual) VALUES 
('GPS', SYSDATE - 0.5, 45.3);

INSERT INTO Sensor_iot (tipo_sensor, data_transmissao, bateria_percentual) VALUES 
('BLUETOOTH', SYSDATE, 92.1);

-- Inserir motos de exemplo
INSERT INTO Moto (placa, chassi, ano_fabricacao, status, Modelo_id_modelo, id_patio, id_sensor_iot, data_atualizacao) VALUES 
('ABC1234', 'CHASSIS001ABC123456789', 2023, 'DISPONIVEL', 1, 1, 1, SYSDATE);

INSERT INTO Moto (placa, chassi, ano_fabricacao, status, Modelo_id_modelo, id_patio, id_sensor_iot, data_atualizacao) VALUES 
('DEF5678', 'CHASSIS002DEF789123456', 2022, 'ALUGADA', 2, 1, 2, SYSDATE);

INSERT INTO Moto (placa, chassi, ano_fabricacao, status, Modelo_id_modelo, id_patio, data_atualizacao) VALUES 
('GHI9012', 'CHASSIS003GHI456789123', 2023, 'MANUTENCAO', 3, 2, SYSDATE);

INSERT INTO Moto (placa, chassi, ano_fabricacao, status, Modelo_id_modelo, id_patio, id_sensor_iot, data_atualizacao) VALUES 
('JKL3456', 'CHASSIS004JKL987654321', 2021, 'DISPONIVEL', 4, 3, 3, SYSDATE);

INSERT INTO Moto (placa, chassi, ano_fabricacao, status, Modelo_id_modelo, id_patio, data_atualizacao) VALUES 
('MNO7890', 'CHASSIS005MNO321654987', 2022, 'DISPONIVEL', 5, 4, SYSDATE);

-- Inserir algumas posições históricas de exemplo
INSERT INTO Historico_posicao (data_atualizacao, id_moto, posicao_x, posicao_y, acuracia_localizacao, origem_detectada, status_no_momento) VALUES 
(SYSTIMESTAMP - INTERVAL '2' HOUR, 1, 100.50, 200.75, 5.2, 'GPS', 'PARADO');

INSERT INTO Historico_posicao (data_atualizacao, id_moto, posicao_x, posicao_y, acuracia_localizacao, origem_detectada, status_no_momento) VALUES 
(SYSTIMESTAMP - INTERVAL '1' HOUR, 1, 105.20, 205.30, 4.8, 'GPS', 'MOVIMENTO');

INSERT INTO Historico_posicao (data_atualizacao, id_moto, posicao_x, posicao_y, acuracia_localizacao, origem_detectada, status_no_momento) VALUES 
(SYSTIMESTAMP - INTERVAL '30' MINUTE, 2, 150.75, 300.25, 6.1, 'GPS', 'ALUGADA');

-- Inserir alguns alertas de exemplo
INSERT INTO Alerta_evento (tipo_alerta, data_geracao, id_moto) VALUES 
('MOVIMENTACAO_SUSPEITA', SYSTIMESTAMP - INTERVAL '3' HOUR, 1);

INSERT INTO Alerta_evento (tipo_alerta, data_geracao, id_moto) VALUES 
('BATERIA_BAIXA', SYSTIMESTAMP - INTERVAL '1' HOUR, 4);

INSERT INTO Alerta_evento (tipo_alerta, data_geracao, id_moto) VALUES 
('SAIDA_PERIMETRO', SYSTIMESTAMP - INTERVAL '2' HOUR, 2);

-- Commit das transações
COMMIT;