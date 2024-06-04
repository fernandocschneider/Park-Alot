insert into Veiculo (veic_placa, veic_marca, veic_model, veic_cor, veic_ano, clie_cpf)
values
    ('ABC1234', 'Toyota', 'Corolla', 'Prata', 2020, 12345678912),
    ('MHI5G78', 'Honda', 'Civic', 'Preto', 2018, 98765432163),
    ('DEF9876', 'Ford', 'Focus', 'Azul', 2019, 54321678978),
	('QEF8D67', 'Fiat', 'Uno', 'Branco', 2005, 56828436985);

insert into Cliente (clie_cpf, clie_nome, clie_tele, clie_email, clie_ender)
values
    (56828436985, 'Alice Silva', 49987654321, 'alice@gmail.com', 'Rua Duque de Caxias, 1234'),
    (12345678912, 'Carlos Santos', 49912345678, 'carlos@gmail.com', 'Rua Marcílio Dias, 5678'),
    (54321678978, 'Mariana Oliveira', 49998511862, 'mariana@gmail.com', 'Rua Almirante Barroso, 8910'),
    (98765432163, 'José Albuquerque', 49987763450, 'josealbuq@gmail.com', 'Rua Marques do Herval, 6543');
   
insert into  Vaga (vaga_cod, vaga_dispon, vaga_temp, vaga_local, manut_id)
values
    (1, 'D', '08:00:00', 'Estacionamento A', 101),
    (2, 'I', '09:30:00', 'Estacionamento B', 102),
    (3, 'D', '10:15:00', 'Estacionamento C', 103);
   
insert into Funcionario (func_cpf, func_nome, func_tele, func_email, func_cargo, func_salar, func_data_adm)
values
    ('56789411230', 'João Ferreira', '49988454897', 'joao@gmail.com', 'Gerente', 5000.00, '2022-01-15'),
    ('68547239617', 'Maria de Abreu', '49998215369', 'maria@gmail.com', 'Atendente', 2500.00, '2022-03-10'),
    ('89723415562', 'Pedro Ribeiro', '49981523679', 'pedro@gmail.com', 'Técnico', 3200.00, '2022-02-20');
