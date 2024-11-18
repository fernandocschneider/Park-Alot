CREATE VIEW Relatorio_Clientes_Veiculos AS
SELECT c.clint_name, c.clint_phone, c.clint_mail, v.veicule_plate, v.veicule_brand, v.veicule_model, v.veicule_year
FROM Cliente c
JOIN Veiculo v ON c.clint_cpf = v.clint_cpf;

SELECT * FROM Relatorio_Clientes_Veiculos;


CREATE VIEW Relatorio_Manutencao_Funcionario AS
SELECT f.worker_name, f.worker_role, m.fix_description, m.fix_date, m.fix_cost
FROM Manutencao m
JOIN Funcionario f ON m.worker_role = f.worker_cpf;

SELECT * FROM Relatorio_Manutencao_Funcionario;


CREATE VIEW Relatorio_Clientes_Enderecos AS
SELECT c.client_name, e.adress_street, e.adress_neighbor, e.adress_extra, e.adress_city
FROM Cliente c
JOIN Endereco e ON c.client_cpf = e.client_cpf;

SELECT * FROM Relatorio_Clientes_Enderecos;


