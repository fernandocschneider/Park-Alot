INSERT INTO Cliente (clie_cpf, clie_nome, clie_tele, clie_email, clie_ender) VALUES
('12345678901', 'João Silva', '11987654321', 'joao.silva@gmail.com', 'Rua Caçanjuru, 0123'),
('23456789012', 'Maria Oliveira', '11987654322', 'maria.oliveira@gmail.com', 'Rua Das Flores, 456'),
('2584631517', 'Carlos Gomes', '11987654323', 'carlos.gomes@gmail.com', 'Rua Dos Pinheiros, 789'),
('3692581470', 'Ana Costa', '11987654324', 'ana.costa@gmail.com', 'Rua Das Palmeiras, 1011');

INSERT INTO Funcionario (func_cpf, func_nome, func_tele, func_email, func_cargo, func_salar, func_data_adm) VALUES
('34567890123', 'Carlos Souza', '11987654323', 'carlos.souza@gmail.com', 'Supervisor', 4500.00, '2023-01-10'),
('45678901234', 'Ana Pereira', '11987654324', 'ana.pereira@gmail.com', 'Operador', 2500.00, '2023-02-15'),
('56789012345', 'Roberto Lima', '11987654325', 'roberto.lima@gmail.com', 'Gerente', 6000.00, '2023-03-20'),
('67890123456', 'Juliana Martins', '11987654326', 'juliana.martins@gmail.com', 'Analista', 3500.00, '2023-04-25');

INSERT INTO Vaga (vaga_cod, vaga_dispon, vaga_temp, vaga_local, manut_id) VALUES
('V001', 'D', '00:30:00', 'Subsolo A1', 'M001'),
('V002', 'I', '01:00:00', 'Subsolo A2', 'M002'),
('V003', 'D', '00:45:00', 'Subsolo B1', 'M003'),
('V004', 'I', '00:50:00', 'Subsolo B2', 'M004');

INSERT INTO Veiculo (veic_placa, veic_marca, veic_model, veic_cor, veic_ano, clie_cpf) VALUES
('ABC1234', 'Toyota', 'Corolla', 'Preto', '2020', '12345678901'),
('DEF5678', 'Honda', 'Civic', 'Branco', '2021', '23456789012'),
('GHI9012', 'Ford', 'Focus', 'Azul', '2019', '2584631517'),
('JKL3456', 'Chevrolet', 'Cruze', 'Vermelho', '2022', '3692581470');

INSERT INTO Manutencao (manut_id, manut_desc, manut_data, manut_custo, func_cpf) VALUES
('M001', 'Troca de lâmpada', '2023-03-01', 150.00, '34567890123'),
('M002', 'Pintura do espaço', '2023-03-05', 300.00, '45678901234'),
('M003', 'Reparo no piso', '2023-03-10', 500.00, '56789012345'),
('M004', 'Instalação de câmeras', '2023-03-15', 800.00, '67890123456');

INSERT INTO Reserva (reser_id, reser_data_ini, reser_data_fim, reser_stat, vaga_cod, veic_placa) VALUES
('R001', '2023-06-01', '2023-06-02', 'Confirmada', 'V001', 'ABC1234'),
('R002', '2023-06-03', '2023-06-04', 'Cancelada', 'V002', 'DEF5678'),
('R003', '2023-06-05', '2023-06-06', 'Confirmada', 'V003', 'GHI9012'),
('R004', '2023-06-07', '2023-06-08', 'Confirmada', 'V004', 'JKL3456');

INSERT INTO Pagamento (paga_id, paga_valor, paga_data_pag, paga_metod, reser_id) VALUES
('P001', 50.00, '2023-06-01', 'Cartão de Crédito', 'R001'),
('P002', 100.00, '2023-06-03', 'Boleto Bancário', 'R002'),
('P003', 75.00, '2023-06-05', 'Pix', 'R003'),
('P004', 85.00, '2023-06-07', 'Dinheiro', 'R004');

INSERT INTO Historico_Utilizacao (hist_id, hist_data_entra, hist_data_saida, vaga_cod, veic_placa) VALUES
('H001', '2023-06-01', '2023-06-02', 'V001', 'ABC1234'),
('H002', '2023-06-03', '2023-06-04', 'V002', 'DEF5678'),
('H003', '2023-06-05', '2023-06-06', 'V003', 'GHI9012'),
('H004', '2023-06-07', '2023-06-08', 'V004', 'JKL3456');

INSERT INTO Manutencao_Vaga (manut_id, vaga_cod) VALUES
('M001', 'V001'),
('M002', 'V002'),
('M003', 'V003'),
('M004', 'V004');
