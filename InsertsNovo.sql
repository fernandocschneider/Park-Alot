INSERT INTO Cliente (clie_cpf, clie_nome, clie_tele, clie_email, clie_idade, clie_sexo) VALUES
('12345678901', 'João Silva', 1234567, 'joao.silva@example.com', 45, 'M'),
('23456789012', 'Maria Oliveira', 2345678, 'maria.oliveira@example.com', 30, 'F'),
('34567890123', 'Carlos Pereira', 3456789, 'carlos.pereira@example.com', 50, 'M'),
('45678901234', 'Ana Costa', 4567890, 'ana.costa@example.com', 35, 'F'),
('56789012345', 'Paulo Souza', 5678901, 'paulo.souza@example.com', 42, 'M'),
('67890123456', 'Clara Mendes', 6789012, 'clara.mendes@example.com', 28, 'F'),
('78901234567', 'Roberto Nunes', 7890123, 'roberto.nunes@example.com', 60, 'M'),
('89012345678', 'Julia Almeida', 8901234, 'julia.almeida@example.com', 37, 'F'),
('90123456789', 'Marcos Lima', 9012345, 'marcos.lima@example.com', 48, 'M'),
('01234567890', 'Fernanda Rocha', 0123456, 'fernanda.rocha@example.com', 33, 'F');

INSERT INTO Funcionario (func_cpf, func_nome, func_tele, func_email, func_cargo, func_salar, func_data_adm) VALUES
('87459361027', 'Mariana Santos', 6789012, 'mariana.santos@example.com', 'Mecânico', 2500.00, '2020-01-15'),
('21579403862', 'Ricardo Lima', 7890123, 'ricardo.lima@example.com', 'Atendente', 1800.00, '2019-05-23'),
('63987150482', 'Lucas Ferreira', 8901234, 'lucas.ferreira@example.com', 'Supervisor', 3200.00, '2018-03-10'),
('57029314865', 'Patrícia Cardoso', 9012345, 'patricia.cardoso@example.com', 'Gerente', 4500.00, '2017-08-01'),
('38294716509', 'Fernando Santos', 0123456, 'fernando.santos@example.com', 'Mecânico', 2700.00, '2018-09-15'),
('92643178054', 'Adriana Melo', 1122334, 'adriana.melo@example.com', 'Atendente', 1900.00, '2021-02-20'),
('48923756012', 'Gustavo Ribeiro', 2233445, 'gustavo.ribeiro@example.com', 'Supervisor', 3300.00, '2019-11-11'),
('70391825647', 'Camila Sousa', 3344556, 'camila.sousa@example.com', 'Gerente', 4600.00, '2016-05-25'),
('18274593056', 'Sérgio Costa', 4455667, 'sergio.costa@example.com', 'Mecânico', 2600.00, '2017-07-30'),
('36589127408', 'Beatriz Martins', 5566778, 'beatriz.martins@example.com', 'Atendente', 2000.00, '2020-12-05');

INSERT INTO Veiculo (veic_placa, veic_marca, veic_model, veic_cor, veic_ano, clie_cpf) VALUES
('ABC1234', 'FIAT', 'Uno', 'Vermelho', '2009', '12345678901'),
('DEF5678', 'FIAT', 'Punto', 'Azul', '2010', '23456789012'),
('GHI9012', 'Toyota', 'Corolla', 'Branco', '2018', '34567890123'),
('JKL3456', 'Honda', 'Civic', 'Preto', '2020', '45678901234'),
('MNO7890', 'Chevrolet', 'Onix', 'Prata', '2021', '56789012345'),
('PQR1234', 'Hyundai', 'HB20', 'Cinza', '2017', '67890123456'),
('STU5678', 'Ford', 'Ka', 'Verde', '2016', '78901234567'),
('VWX9012', 'Volkswagen', 'Gol', 'Amarelo', '2015', '89012345678'),
('YZA3456', 'Renault', 'Sandero', 'Azul', '2019', '90123456789'),
('BCD7890', 'Nissan', 'March', 'Vermelho', '2022', '01234567890');

INSERT INTO Endereco (ender_rua, ender_bairro, ender_comp, ender_cida, clie_cpf) VALUES
('Rua das Acácias', 'Centro', 'Apto 101', 'Maravilha', '12345678901'),
('Avenida dos Girassóis', 'Agostini', 'Casa 2', 'Descanso', '23456789012'),
('Travessa das Violetas', 'Centro', 'Bloco B', 'Itapiranga', '34567890123'),
('Alameda dos Ipês', 'Centro', 'Apto 202', 'Guaraciaba', '45678901234'),
('Praça das Palmeiras', 'Bela Vista', 'Casa 5', 'Maravilha', '56789012345'),
('Estrada das Rosas', 'Centro', 'Apto 303', 'Descanso', '67890123456'),
('Beco das Hortênsias', 'Agostini', 'Casa 3', 'Itapiranga', '78901234567'),
('Viela dos Jasmins', 'Centro', 'Bloco C', 'Guaraciaba', '89012345678'),
('Largo dos Lírios', 'Bela Vista', 'Casa 6', 'Maravilha', '90123456789'),
('Rua das Orquídeas', 'Centro', 'Apto 404', 'Descanso', '01234567890');

INSERT INTO Manutencao (manut_desc, manut_data, manut_custo, func_cpf) VALUES
('Troca de óleo', '2023-06-10', 200.00, '87459361027'),
('Revisão geral', '2023-07-15', 600.00, '21579403862'),
('Troca de pneus', '2023-08-20', 800.00, '63987150482'),
('Alinhamento', '2023-09-25', 150.00, '57029314865'),
('Balanceamento', '2023-10-30', 200.00, '38294716509'),
('Troca de bateria', '2023-11-05', 300.00, '92643178054'),
('Troca de filtros', '2023-12-10', 100.00, '48923756012'),
('Troca de pastilhas de freio', '2024-01-15', 250.00, '70391825647'),
('Revisão de suspensão', '2024-02-20', 500.00, '18274593056'),
('Troca de óleo', '2024-03-25', 200.00, '36589127408');

INSERT INTO Vaga (vaga_dispon, vaga_temp, vaga_local, manut_id) VALUES
('D', '02:00:00', 'Setor A', 1),
('I', '03:00:00', 'Setor B', 2),
('D', '01:30:00', 'Setor C', 3),
('I', '04:00:00', 'Setor D', 4),
('D', '02:30:00', 'Setor E', 5),
('I', '03:30:00', 'Setor F', 6),
('D', '01:45:00', 'Setor G', 7),
('I', '04:15:00', 'Setor H', 8),
('D', '02:15:00', 'Setor I', 9),
('I', '03:45:00', 'Setor J', 10);

INSERT INTO Reserva (reser_data_ini, reser_data_fim, reser_stat, vaga_cod, veic_placa) VALUES
('2024-02-10', '2024-02-11', 'Confirmada', 1, 'ABC1234'),
('2024-04-15', '2024-04-16', 'Confirmada', 2, 'DEF5678'),
('2024-06-20', '2024-06-21', 'Confirmada', 3, 'GHI9012'),
('2024-08-25', '2024-08-26', 'Confirmada', 4, 'JKL3456'),
('2024-10-30', '2024-10-31', 'Confirmada', 5, 'MNO7890'),
('2024-01-05', '2024-01-06', 'Confirmada', 6, 'PQR1234'),
('2024-03-10', '2024-03-11', 'Confirmada', 7, 'STU5678'),
('2024-05-15', '2024-05-16', 'Confirmada', 8, 'VWX9012'),
('2024-07-20', '2024-07-21', 'Confirmada', 9, 'YZA3456'),
('2024-09-25', '2024-09-26', 'Confirmada', 10, 'BCD7890');

INSERT INTO Pagamento (paga_valor, paga_data_pag, paga_metod, reser_id) VALUES
(100.00, '2024-02-11', 'Cartão de Crédito', 1),
(150.00, '2024-04-16', 'Dinheiro', 2),
(120.00, '2024-06-21', 'Cartão de Débito', 3),
(130.00, '2024-08-26', 'Cartão de Crédito', 4),
(110.00, '2024-10-31', 'Pix', 5),
(140.00, '2024-01-06', 'Dinheiro', 6),
(160.00, '2024-03-11', 'Cartão de Débito', 7),
(125.00, '2024-05-16', 'Cartão de Crédito', 8),
(135.00, '2024-07-21', 'Pix', 9),
(145.00, '2024-09-26', 'Dinheiro', 10);

INSERT INTO Historico_utilizacao (hist_data_entra, hist_data_saida, vaga_cod, veic_placa) VALUES
('2024-02-10', '2024-02-11', 1, 'ABC1234'),
('2024-04-15', '2024-04-16', 2, 'DEF5678'),
('2024-06-20', '2024-06-21', 3, 'GHI9012'),
('2024-08-25', '2024-08-26', 4, 'JKL3456'),
('2024-10-30', '2024-10-31', 5, 'MNO7890'),
('2024-01-05', '2024-01-06', 6, 'PQR1234'),
('2024-03-10', '2024-03-11', 7, 'STU5678'),
('2024-05-15', '2024-05-16', 8, 'VWX9012'),
('2024-07-20', '2024-07-21', 9, 'YZA3456'),
('2024-09-25', '2024-09-26', 10, 'BCD7890');
