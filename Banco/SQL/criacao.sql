CREATE TABLE
    cliente (
        client_id BIGSERIAL PRIMARY KEY, -- Identificador único do cliente
        client_cpf VARCHAR(11) NOT NULL UNIQUE, -- CPF do cliente
        client_name VARCHAR(40) NOT NULL, -- Nome completo do cliente
        client_phone VARCHAR(11) NOT NULL, -- Telefone de contato do cliente
        client_mail VARCHAR(30) NOT NULL UNIQUE, -- E-mail do cliente
        client_age INT NOT NULL, -- Idade do cliente
        client_sex CHAR(1) NOT NULL -- Sexo do cliente (M - Masculino, F - Feminino)
    );

COMMENT ON TABLE Cliente IS 'Tabela de gerenciamento de clientes';

COMMENT ON COLUMN Cliente.client_id IS 'Identificador único do cliente';

COMMENT ON COLUMN Cliente.client_cpf IS 'Cpf do Cliente';

COMMENT ON COLUMN Cliente.client_name IS 'Nome do Cliente';

COMMENT ON COLUMN Cliente.client_phone IS 'Número de telefone do cliente';

COMMENT ON COLUMN Cliente.client_mail IS 'Email do Cliente';

COMMENT ON COLUMN Cliente.client_age IS 'Idade do Cliente';

COMMENT ON COLUMN Cliente.client_sex IS 'Sexo do Cliente (M - Masculino, F - Feminino)';

CREATE TABLE
    veiculo (
        veiculo_id BIGSERIAL PRIMARY KEY, -- Identificador único do veículo
        veicule_sign VARCHAR(8) NOT NULL UNIQUE, -- Placa do veículo
        veicule_brand VARCHAR(16) NOT NULL, -- Marca do veículo
        veicule_model VARCHAR(16) NOT NULL, -- Modelo do veículo
        veicule_color VARCHAR(16), -- Cor do veículo
        veicule_year VARCHAR(4), -- Ano de fabricação do veículo
        client_id BIGINT NOT NULL, -- Referência ao cliente proprietário
        CONSTRAINT fk_veiculo_cliente FOREIGN KEY (client_id) REFERENCES cliente (client_id) ON DELETE CASCADE
    );

COMMENT ON TABLE Endereco IS 'Tabela de gerenciamento de endereço dos clientes';

COMMENT ON COLUMN Endereco.adress_number IS 'Número da casa do endereço';

COMMENT ON COLUMN Endereco.adress_street IS 'Rua do endereço do cliente';

COMMENT ON COLUMN Endereco.adress_neighbor IS 'Bairro do endereço do cliente';

COMMENT ON COLUMN Endereco.adress_extra IS 'Complemento do endereço do cliente';

COMMENT ON COLUMN Endereco.adress_city IS 'Cidade do endereço do cliente';

COMMENT ON COLUMN Endereco.client_cpf IS 'Cpf do cliente associado ao endereço';

CREATE TABLE
    Funcionario (
        worker_id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        worker_cpf VARCHAR(11) NOT NULL UNIQUE,
        worker_name VARCHAR(40) NOT NULL,
        worker_phone VARCHAR(11) NOT NULL,
        worker_mail VARCHAR(30) NOT NULL UNIQUE,
        worker_role VARCHAR(30) NOT NULL,
        worker_salary DECIMAL(10, 2) NOT NULL,
        worker_admission INT NOT NULL,
        CONSTRAINT pk_funcionario PRIMARY KEY (worker_id)
    );

COMMENT ON TABLE Funcionario IS 'Tabela de gerenciamento de funcionários';

COMMENT ON COLUMN Funcionario.worker_id IS 'Identificador único do funcionário';

COMMENT ON COLUMN Funcionario.worker_cpf IS 'CPF único do funcionário';

COMMENT ON COLUMN Funcionario.worker_name IS 'Nome completo do funcionário';

COMMENT ON COLUMN Funcionario.worker_phone IS 'Telefone de contato do funcionário';

COMMENT ON COLUMN Funcionario.worker_mail IS 'E-mail único do funcionário';

COMMENT ON COLUMN Funcionario.worker_role IS 'Cargo ou função desempenhada pelo funcionário';

COMMENT ON COLUMN Funcionario.worker_salary IS 'Salário atual do funcionário';

COMMENT ON COLUMN Funcionario.worker_admission IS 'Ano de admissão do funcionário';

CREATE TABLE
    HistoricoUtilizacao (
        history_id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        history_entry_date DATE NOT NULL,
        history_leave_date DATE NOT NULL,
        spot_id BIGINT NOT NULL,
        veicule_sign VARCHAR(20) NOT NULL,
        CONSTRAINT fk_historico_utilizacao_spot FOREIGN KEY (spot_id) REFERENCES Vaga (spot_id),
        CONSTRAINT fk_historico_utilizacao_veiculo FOREIGN KEY (veicule_sign) REFERENCES Veiculo (veicule_sign)
    );

COMMENT ON TABLE HistoricoUtilizacao IS 'Tabela de histórico de utilização das vagas de estacionamento';

COMMENT ON COLUMN HistoricoUtilizacao.history_id IS 'Identificador único do histórico de utilização';

COMMENT ON COLUMN HistoricoUtilizacao.history_entry_date IS 'Data de entrada do veículo na vaga';

COMMENT ON COLUMN HistoricoUtilizacao.history_leave_date IS 'Data de saída do veículo da vaga';

COMMENT ON COLUMN HistoricoUtilizacao.spot_id IS 'ID da vaga utilizada';

COMMENT ON COLUMN HistoricoUtilizacao.veicule_sign IS 'Placa do veículo que utilizou a vaga';

CREATE TABLE
    Manutencao (
        fix_id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        fix_description VARCHAR(50) NOT NULL,
        fix_date DATE,
        fix_cost DECIMAL(10, 2) NOT NULL,
        worker_cpf VARCHAR(11) NOT NULL,
        CONSTRAINT fk_manutencao_worker FOREIGN KEY (worker_cpf) REFERENCES Funcionario (worker_cpf)
    );

COMMENT ON TABLE Manutencao IS 'Tabela de registros de manutenção realizadas no sistema de estacionamento';

COMMENT ON COLUMN Manutencao.fix_id IS 'Identificador único da manutenção';

COMMENT ON COLUMN Manutencao.fix_description IS 'Descrição da manutenção realizada';

COMMENT ON COLUMN Manutencao.fix_date IS 'Data em que a manutenção foi realizada';

COMMENT ON COLUMN Manutencao.fix_cost IS 'Custo da manutenção realizada';

COMMENT ON COLUMN Manutencao.worker_cpf IS 'CPF do funcionário responsável pela manutenção';

CREATE TABLE
    Pagamento (
        payment_id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        payment_cost DECIMAL(10, 2) NOT NULL,
        payment_date DATE NOT NULL,
        payment_method VARCHAR(50) NOT NULL,
        booking_id BIGINT NOT NULL,
        CONSTRAINT fk_pagamento_reserva FOREIGN KEY (booking_id) REFERENCES Reserva (booking_id)
    );

COMMENT ON TABLE Pagamento IS 'Tabela de registros de pagamentos de reservas no sistema de estacionamento';

COMMENT ON COLUMN Pagamento.payment_id IS 'Identificador único do pagamento';

COMMENT ON COLUMN Pagamento.payment_cost IS 'Custo do pagamento, correspondente ao valor pago pela reserva';

COMMENT ON COLUMN Pagamento.payment_date IS 'Data em que o pagamento foi realizado';

COMMENT ON COLUMN Pagamento.payment_method IS 'Método de pagamento utilizado (ex: cartão de crédito, dinheiro, etc.)';

COMMENT ON COLUMN Pagamento.booking_id IS 'Identificador da reserva associada ao pagamento';

CREATE TABLE
    reserva (
        booking_id BIGSERIAL PRIMARY KEY, -- Identificador único da reserva
        booking_status VARCHAR(20) NOT NULL, -- Status da reserva (confirmada, pendente, finalizada)
        spot_id BIGINT NOT NULL, -- Vaga associada à reserva
        veicule_sign VARCHAR(8) NOT NULL, -- Placa do veículo que fez a reserva
        client_id BIGINT NOT NULL, -- Cliente associado à reserva
        CONSTRAINT fk_reserva_spot FOREIGN KEY (spot_id) REFERENCES vaga (spot_id) ON DELETE CASCADE,
        CONSTRAINT fk_reserva_veiculo FOREIGN KEY (veicule_sign) REFERENCES veiculo (veicule_sign) ON DELETE CASCADE,
        CONSTRAINT fk_reserva_cliente FOREIGN KEY (client_id) REFERENCES cliente (client_id) ON DELETE CASCADE
    );

COMMENT ON TABLE Reserva IS 'Tabela de reservas de vagas para veículos no estacionamento';

COMMENT ON COLUMN Reserva.booking_id IS 'Identificador único da reserva';

COMMENT ON COLUMN Reserva.booking_status IS 'Status da reserva (ex: "confirmada", "pendente", "finalizada")';

COMMENT ON COLUMN Reserva.spot_id IS 'Identificador da vaga associada à reserva';

COMMENT ON COLUMN Reserva.veicule_sign IS 'Identificador do veículo que fez a reserva';

CREATE TABLE
    vaga (
        spot_id BIGSERIAL PRIMARY KEY, -- Identificador único da vaga
        spot_available BOOLEAN NOT NULL, -- Indicador de disponibilidade da vaga
        spot_time INT NOT NULL, -- Tempo máximo permitido para estacionamento
        spot_local VARCHAR(50) NOT NULL -- Localização ou descrição do local da vaga
    );

COMMENT ON TABLE Vaga IS 'Tabela que representa as vagas de estacionamento, incluindo disponibilidade, tempo e localização';

COMMENT ON COLUMN Vaga.spot_id IS 'Identificador único da vaga';

COMMENT ON COLUMN Vaga.spot_available IS 'Indica se a vaga está disponível (TRUE) ou ocupada (FALSE)';

COMMENT ON COLUMN Vaga.spot_time IS 'Tempo máximo permitido para estacionamento na vaga';

COMMENT ON COLUMN Vaga.spot_local IS 'Localização ou descrição do local da vaga';

CREATE TABLE
    veiculo (
        veiculo_id BIGSERIAL PRIMARY KEY, -- Identificador único do veículo
        veicule_sign VARCHAR(8) NOT NULL UNIQUE, -- Placa do veículo, deve ser única
        veicule_brand VARCHAR(16) NOT NULL, -- Marca do veículo
        veicule_model VARCHAR(16) NOT NULL, -- Modelo do veículo
        veicule_color VARCHAR(16), -- Cor do veículo
        veicule_year CHAR(4), -- Ano de fabricação do veículo
        client_id BIGINT NOT NULL, -- Identificador do cliente (proprietário)
        FOREIGN KEY (client_id) REFERENCES cliente (client_id) ON DELETE CASCADE -- Relacionamento com a tabela 'cliente'
    );

COMMENT ON TABLE Veiculo IS 'Tabela que representa veículos, contendo informações sobre placa, marca, modelo, cor, ano de fabricação e o proprietário (cliente).';

COMMENT ON COLUMN Veiculo.veiculo_id IS 'Identificador único do veículo';

COMMENT ON COLUMN Veiculo.veicule_sign IS 'Placa do veículo (única)';

COMMENT ON COLUMN Veiculo.veicule_brand IS 'Marca do veículo';

COMMENT ON COLUMN Veiculo.veicule_model IS 'Modelo do veículo';

COMMENT ON COLUMN Veiculo.veicule_color IS 'Cor do veículo';

COMMENT ON COLUMN Veiculo.veicule_year IS 'Ano de fabricação do veículo';

COMMENT ON COLUMN Veiculo.client_id IS 'Identificador do proprietário do veículo (referência ao Cliente)';