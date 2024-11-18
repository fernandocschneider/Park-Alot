-- Gatilho de Auditoria para a tabela de Reserva
DELIMITER //

CREATE TRIGGER audit_reserva
AFTER INSERT OR UPDATE OR DELETE ON Reserva
FOR EACH ROW
BEGIN
    DECLARE action_type VARCHAR(50);

    -- Determinar o tipo de ação (INSERT, UPDATE ou DELETE)
    IF (NEW.booking_id IS NOT NULL AND OLD.booking_id IS NULL) THEN
        SET action_type = 'INSERT';
    ELSEIF (NEW.booking_id IS NOT NULL AND OLD.booking_id IS NOT NULL) THEN
        SET action_type = 'UPDATE';
    ELSEIF (NEW.booking_id IS NULL AND OLD.booking_id IS NOT NULL) THEN
        SET action_type = 'DELETE';
    END IF;

    -- Inserir o registro de auditoria na tabela AuditLog
    INSERT INTO AuditLog (table_name, operation_type, operation_date, user_name, old_value, new_value)
    VALUES ('Reserva', action_type, NOW(), USER(), 
            IFNULL(OLD.booking_id, 'N/A'), IFNULL(NEW.booking_id, 'N/A'));
END;

DELIMITER ;

--################################################################################


-- Gatilho de Validação para garantir que a placa seja única na tabela de Veículo
DELIMITER //

CREATE TRIGGER validate_veiculo_placa
BEFORE INSERT ON Veiculo
FOR EACH ROW
BEGIN
    -- Verificar se a placa já existe na tabela de Veiculos
    IF EXISTS (SELECT 1 FROM Veiculo WHERE veicule_plate = NEW.veicule_plate) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Erro: Placa do veículo já cadastrada.';
    END IF;
END;

DELIMITER ;
