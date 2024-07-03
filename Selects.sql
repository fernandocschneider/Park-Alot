-- 1) Relacionar todos os veículos da marca FIAT fabricados depois de 2008. Ordenar o relatório pela placa dos veículos em ordem descendente:

SELECT *
FROM Veiculo
WHERE veic_marca = 'FIAT' AND veic_ano > '2008'
ORDER BY veic_placa DESC;

-- 2) Relacionar as reservas registradas em meses pares e de clientes dos bairros Centro e Agostini. Ordene o relatório pelo nome do cliente de forma descendente:

SELECT R.*, C.clie_nome
FROM Reserva R
inner JOIN Veiculo V ON R.veic_placa = V.veic_placa
inner JOIN Cliente C ON V.clie_cpf = C.clie_cpf
inner JOIN Endereco E ON C.clie_cpf = E.clie_cpf
WHERE CAST(EXTRACT(MONTH FROM R.reser_data_ini) AS INTEGER) % 2 = 0
AND (E.ender_bairro = 'Centro' OR E.ender_bairro = 'Agostini')
ORDER BY C.clie_nome DESC;

-- 3) Relacionar todas as reservas de clientes das cidades de Maravilha, Descanso, Itapiranga e Guaraciaba que têm mais de 40 anos e do sexo masculino.
-- Ordene o relatório das cidades com mais reservas para cidades com menos reservas:

SELECT E.ender_cida, COUNT(*) AS num_reservas
FROM Reserva R
inner JOIN Veiculo V ON R.veic_placa = V.veic_placa
inner JOIN Cliente C ON V.clie_cpf = C.clie_cpf
left JOIN Endereco E ON C.clie_cpf = E.clie_cpf
WHERE E.ender_cida IN ('Maravilha', 'Descanso', 'Itapiranga', 'Guaraciaba')
AND C.clie_idade > 40 AND C.clie_sexo = 'M'
GROUP BY E.ender_cida
ORDER BY num_reservas DESC;

-- 4) Relacionar o mês, o total de veículos estacionados e o valor total em pagamentos realizados em 2024.
-- Relacionar do mês com o maior valor para o mês com o menor valor:

SELECT EXTRACT(MONTH FROM P.paga_data_pag) AS mes,
       COUNT(DISTINCT H.veic_placa) AS total_veiculos,
       SUM(P.paga_valor) AS valor_total
FROM Pagamento P
full JOIN Reserva R ON P.reser_id = R.reser_id
full JOIN Historico_utilizacao H ON R.veic_placa = H.veic_placa
WHERE EXTRACT(YEAR FROM P.paga_data_pag) = 2024
GROUP BY mes
ORDER BY valor_total DESC;
