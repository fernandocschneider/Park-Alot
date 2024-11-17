CREATE VIEW Relatorio_Clientes_Veiculos AS
SELECT c.clie_nome, c.clie_tele, c.clie_email, v.veic_placa, v.veic_marca, v.veic_model, v.veic_ano
FROM Cliente c
JOIN Veiculo v ON c.clie_cpf = v.clie_cpf;

SELECT * FROM Relatorio_Clientes_Veiculos;


CREATE VIEW Relatorio_Manutencao_Funcionario AS
SELECT f.func_nome, f.func_cargo, m.manut_desc, m.manut_data, m.manut_custo
FROM Manutencao m
JOIN Funcionario f ON m.func_cpf = f.func_cpf;

SELECT * FROM Relatorio_Manutencao_Funcionario;


CREATE VIEW Relatorio_Clientes_Enderecos AS
SELECT c.clie_nome, e.ender_rua, e.ender_bairro, e.ender_comp, e.ender_cida
FROM Cliente c
JOIN Endereco e ON c.clie_cpf = e.clie_cpf;

SELECT * FROM Relatorio_Clientes_Enderecos;


