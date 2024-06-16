INSERT INTO Cliente (clie_cpf, clie_nome, clie_tele, clie_email) VALUES
('12345678901', 'João Silva', 999999999, 'joao.silva@example.com'),
('23456789012', 'Maria Souza', 988888888, 'maria.souza@example.com'),
('34567890123', 'Carlos Pereira', 977777777, 'carlos.pereira@example.com'),
('45678901234', 'Ana Costa', 966666666, 'ana.costa@example.com'),
('56789012345', 'Lucas Oliveira', 955555555, 'lucas.oliveira@example.com'),
('67890123456', 'Fernanda Lima', 944444444, 'fernanda.lima@example.com');

INSERT INTO Funcionario (func_cpf, func_nome, func_tele, func_email, func_cargo, func_salar, func_data_adm) VALUES
('78901234567', 'Pedro Santos', 999999999, 'pedro.santos@example.com', 'Gerente', 5000.00, '2020-01-01'),
('89012345678', 'Mariana Almeida', 988888888, 'mariana.almeida@example.com', 'Atendente', 3000.00, '2021-02-01'),
('90123456789', 'Roberto Lima', 977777777, 'roberto.lima@example.com', 'Manobrista', 2500.00, '2019-03-01'),
('01234567890', 'Fernanda Rocha', 966666666, 'fernanda.rocha@example.com', 'Segurança', 3500.00, '2018-04-01'),
('12345678091', 'Lucas Dias', 955555555, 'lucas.dias@example.com', 'Manutenção', 4000.00, '2022-05-01'),
('23456789012', 'Patrícia Souza', 944444444, 'patricia.souza@example.com', 'Supervisora', 4500.00, '2023-06-01');

INSERT INTO Veiculo (veic_placa, veic_marca, veic_model, veic_cor, veic_ano, clie_cpf) VALUES
('ABC1234', 'Toyota', 'Corolla', 'Prata', '2020', '12345678901'),
('XYZ5678', 'Honda', 'Civic', 'Preto', '2019', '23456789012'),
('LMN3456', 'Ford', 'Focus', 'Branco', '2021', '34567890123'),
('OPQ7890', 'Chevrolet', 'Cruze', 'Vermelho', '2018', '45678901234'),
('RST9012', 'Hyundai', 'Elantra', 'Azul', '2022', '56789012345'),
('UVW2345', 'Nissan', 'Sentra', 'Cinza', '2020', '67890123456');

INSERT INTO Endereco (ender_rua, ender_bairro, ender_comp, clie_cpf) VALUES
('Rua A', 'Bairro X', 'Apt 101', '12345678901'),
('Rua B', 'Bairro Y', 'Casa 2', '23456789012'),
('Rua C', 'Bairro Z', 'Apt 202', '34567890123'),
('Rua D', 'Bairro W', 'Casa 5', '45678901234'),
('Rua E', 'Bairro V', 'Apt 303', '56789012345'),
('Rua F', 'Bairro U', 'Casa 7', '67890123456');

INSERT INTO Manutencao (manut_desc, manut_data, manut_custo, func_cpf, manut_id) VALUES
('Troca de lâmpadas', '2024-01-01', 100.00, '78901234567', 1),
('Reparação do piso', '2024-02-01', 200.00, '89012345678', 2),
('Pintura de sinalização', '2024-03-01', 150.00, '90123456789', 3),
('Limpeza geral', '2024-04-01', 50.00, '01234567890', 4),
('Manutenção do elevador', '2024-05-01', 300.00, '12345678091', 5),
('Substituição de câmeras', '2024-06-01', 400.00, '23456789012', 6);

INSERT INTO Vaga (vaga_dispon, vaga_temp, vaga_local, manut_id) VALUES
('D', '01:00:00', 'A1', 1),
('I', '02:00:00', 'A2', 2),
('D', '03:00:00', 'B1', 3),
('I', '04:00:00', 'B2', 4),
('D', '05:00:00', 'C1', 5),
('I', '06:00:00', 'C2', 6);

INSERT INTO Reserva (reser_data_ini, reser_data_fim, reser_stat, vaga_cod, veic_placa) VALUES
('2024-01-01', '2024-01-02', 'Confirmada', 1, 'ABC1234'),
('2024-02-01', '2024-02-03', 'Pendente', 2, 'XYZ5678'),
('2024-03-01', '2024-03-02', 'Cancelada', 3, 'LMN3456'),
('2024-04-01', '2024-04-02', 'Confirmada', 4, 'OPQ7890'),
('2024-05-01', '2024-05-03', 'Pendente', 5, 'RST9012'),
('2024-06-01', '2024-06-02', 'Confirmada', 6, 'UVW2345');

INSERT INTO Pagamento (paga_valor, paga_data_pag, paga_metod, reser_id) VALUES
(100.00, '2024-01-02', 'Cartão de Crédito', 1),
(150.00, '2024-02-03', 'Boleto Bancário', 2),
(200.00, '2024-03-02', 'Cartão de Débito', 3),
(250.00, '2024-04-02', 'Pix', 4),
(300.00, '2024-05-03', 'Transferência Bancária', 5),
(350.00, '2024-06-02', 'Dinheiro', 6);

INSERT INTO Historico_utilizacao (hist_data_entra, hist_data_saida, vaga_cod, veic_placa) VALUES
('2024-01-01', '2024-01-01', 1, 'ABC1234'),
('2024-02-01', '2024-02-01', 2, 'XYZ5678'),
('2024-03-01', '2024-03-01', 3, 'LMN3456'),
('2024-04-01', '2024-04-01', 4, 'OPQ7890'),
('2024-05-01', '2024-05-01', 5, 'RST9012'),
('2024-06-01', '2024-06-01', 6, 'UVW2345');




