DELIMITER / / CREATE PROCEDURE reservar_vaga (
    IN p_veic_placa VARCHAR(10),
    IN p_vaga_cod INT,
    IN p_data_ini DATE,
    IN p_data_fim DATE
) BEGIN DECLARE veiculo_existe INT;

DECLARE vaga_disponivel INT;

-- Verifica se o veículo existe
SELECT
    COUNT(*) INTO veiculo_existe
FROM
    Veiculo
WHERE
    veic_placa = p_veic_placa;

IF veiculo_existe = 0 THEN SIGNAL SQLSTATE '45000'
SET
    MESSAGE_TEXT = 'Erro: Veículo não encontrado.';

END IF;

-- Verifica se a vaga está disponível
SELECT
    COUNT(*) INTO vaga_disponivel
FROM
    Reserva
WHERE
    vaga_cod = p_vaga_cod
    AND reser_stat = 'Confirmada';

IF vaga_disponivel > 0 THEN SIGNAL SQLSTATE '45000'
SET
    MESSAGE_TEXT = 'Erro: Vaga já está ocupada.';

END IF;

-- Insere a reserva se a vaga estiver disponível e o veículo existir
INSERT INTO
    Reserva (
        reser_data_ini,
        reser_data_fim,
        reser_stat,
        vaga_cod,
        veic_placa
    )
VALUES
    (
        p_data_ini,
        p_data_fim,
        'Confirmada',
        p_vaga_cod,
        p_veic_placa
    );

-- Retorna uma mensagem de sucesso
SELECT
    'Reserva realizada com sucesso' AS mensagem;

END / / DELIMITER;

-- ###########################
DELIMITER / / CREATE PROCEDURE atualizar_status_reserva (IN p_reser_id INT, IN p_novo_status VARCHAR(20)) BEGIN DECLARE reserva_existe INT;

-- Verifica se a reserva existe
SELECT
    COUNT(*) INTO reserva_existe
FROM
    Reserva
WHERE
    reser_id = p_reser_id;

IF reserva_existe = 0 THEN SIGNAL SQLSTATE '45000'
SET
    MESSAGE_TEXT = 'Erro: Reserva não encontrada.';

END IF;

-- Verifica se o novo status é válido
IF p_novo_status NOT IN ('Confirmada', 'Cancelada', 'Concluída') THEN SIGNAL SQLSTATE '45000'
SET
    MESSAGE_TEXT = 'Erro: Status inválido.';

END IF;

-- Atualiza o status da reserva
UPDATE Reserva
SET
    reser_stat = p_novo_status
WHERE
    reser_id = p_reser_id;

-- Retorna uma mensagem de sucesso
SELECT
    'Status da reserva atualizado com sucesso' AS mensagem;

END / / DELIMITER;