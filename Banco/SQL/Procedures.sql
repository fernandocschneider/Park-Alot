-- Procedure 1: Criar nova reserva com validações
CREATE OR REPLACE PROCEDURE criar_reserva(
    p_spot_id BIGINT,
    p_veicule_sign VARCHAR(8),
    p_client_id BIGINT
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_vaga_disponivel BOOLEAN;
BEGIN
    -- Verificar se a vaga está disponível
    SELECT spot_available INTO v_vaga_disponivel
    FROM vaga
    WHERE spot_id = p_spot_id;
    
    IF NOT v_vaga_disponivel THEN
        RAISE EXCEPTION 'Vaga não está disponível';
    END IF;
    
    -- Criar a reserva
    INSERT INTO reserva (
        booking_status,
        spot_id,
        veicule_sign,
        client_id
    ) VALUES (
        'ativa',
        p_spot_id,
        p_veicule_sign,
        p_client_id
    );
    
    -- A trigger atualizar_disponibilidade_vaga será executada automaticamente
END;
$$;

-- Procedure 2: Calcular e registrar pagamento da reserva
CREATE OR REPLACE PROCEDURE finalizar_reserva_com_pagamento(
    p_booking_id BIGINT,
    p_payment_method VARCHAR(50)
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_total_cost DECIMAL(10,2);
    v_spot_time INT;
BEGIN
    -- Obter o tempo da vaga
    SELECT v.spot_time INTO v_spot_time
    FROM reserva r
    JOIN vaga v ON r.spot_id = v.spot_id
    WHERE r.booking_id = p_booking_id;
    
    -- Calcular custo (exemplo: R$ 0.50 por minuto)
    v_total_cost := v_spot_time * 0.50;
    
    -- Registrar pagamento
    INSERT INTO pagamento (
        payment_cost,
        payment_date,
        payment_method,
        booking_id
    ) VALUES (
        v_total_cost,
        CURRENT_DATE,
        p_payment_method,
        p_booking_id
    );
    
    -- Atualizar status da reserva
    UPDATE reserva
    SET booking_status = 'concluída'
    WHERE booking_id = p_booking_id;
    
    -- A trigger registrar_historico será executada automaticamente
END;
$$;