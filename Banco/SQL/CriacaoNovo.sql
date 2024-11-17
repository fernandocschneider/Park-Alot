-- Criação da tabela Cliente
CREATE TABLE
    cliente (
        client_id BIGSERIAL PRIMARY KEY,
        client_cpf VARCHAR(11) NOT NULL UNIQUE,
        client_name VARCHAR(40) NOT NULL,
        client_phone VARCHAR(11) NOT NULL,
        client_mail VARCHAR(30) NOT NULL UNIQUE,
        client_age INTEGER NOT NULL,
        client_sex CHAR(1) NOT NULL
    );

-- Comentários da tabela Cliente
COMMENT ON TABLE cliente IS 'Tabela que armazena as informações dos clientes, como CPF, nome, telefone e idade.';

-- Comentários das colunas da tabela Cliente
COMMENT ON COLUMN cliente.client_id IS 'Identificador único do cliente (chave primária).';

COMMENT ON COLUMN cliente.client_cpf IS 'CPF único do cliente.';

COMMENT ON COLUMN cliente.client_name IS 'Nome completo do cliente.';

COMMENT ON COLUMN cliente.client_phone IS 'Número de telefone do cliente.';

COMMENT ON COLUMN cliente.client_mail IS 'Endereço de email único do cliente.';

COMMENT ON COLUMN cliente.client_age IS 'Idade do cliente.';

COMMENT ON COLUMN cliente.client_sex IS 'Sexo do cliente (M para masculino, F para feminino).';

-- Criação da tabela Veiculo
CREATE TABLE
    veiculo (
        veiculo_id SERIAL PRIMARY KEY,
        veicule_sign VARCHAR(8) NOT NULL UNIQUE,
        veicule_brand VARCHAR(16) NOT NULL,
        veicule_model VARCHAR(16) NOT NULL,
        veicule_color VARCHAR(16),
        veicule_year VARCHAR(4),
        client_id BIGINT NOT NULL,
        CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES cliente (client_id)
    );

-- Comentários da tabela e colunas
COMMENT ON TABLE veiculo IS 'Tabela que armazena os veículos registrados no sistema';

COMMENT ON COLUMN veiculo.veiculo_id IS 'Identificador único do veículo';

COMMENT ON COLUMN veiculo.veicule_sign IS 'Placa do veículo (valor único)';

COMMENT ON COLUMN veiculo.veicule_brand IS 'Marca do veículo';

COMMENT ON COLUMN veiculo.veicule_model IS 'Modelo do veículo';

COMMENT ON COLUMN veiculo.veicule_color IS 'Cor do veículo';

COMMENT ON COLUMN veiculo.veicule_year IS 'Ano de fabricação do veículo';

COMMENT ON COLUMN veiculo.client_id IS 'Identificador do proprietário do veículo';

-- Criação da tabela Endereco
CREATE TABLE
    endereco (
        adress_number BIGSERIAL PRIMARY KEY,
        adress_street VARCHAR(30) NOT NULL,
        adress_neighbor VARCHAR(30) NOT NULL,
        adress_extra VARCHAR(30),
        adress_city VARCHAR(40) NOT NULL,
        client_cpf VARCHAR(11) NOT NULL,
        CONSTRAINT fk_cliente FOREIGN KEY (client_cpf) REFERENCES cliente (client_cpf)
    );

-- Comentários da tabela Endereco
COMMENT ON TABLE endereco IS 'Tabela que armazena os endereços dos clientes, incluindo rua, bairro, cidade e CPF do cliente.';

COMMENT ON COLUMN endereco.adress_number IS 'Identificador único do endereço (chave primária).';

COMMENT ON COLUMN endereco.adress_street IS 'Nome da rua onde o cliente reside.';

COMMENT ON COLUMN endereco.adress_neighbor IS 'Nome do bairro onde o cliente reside.';

COMMENT ON COLUMN endereco.adress_extra IS 'Informações adicionais sobre o endereço, como complemento.';

COMMENT ON COLUMN endereco.adress_city IS 'Nome da cidade onde o cliente reside.';

COMMENT ON COLUMN endereco.client_cpf IS 'CPF do cliente associado a este endereço (chave estrangeira).';

-- Criação da tabela Funcionario
CREATE TABLE
    funcionario (
        worker_id SERIAL PRIMARY KEY,
        worker_cpf VARCHAR(11) NOT NULL UNIQUE,
        worker_name VARCHAR(40) NOT NULL,
        worker_phone VARCHAR(11) NOT NULL,
        worker_mail VARCHAR(30) NOT NULL UNIQUE,
        worker_role VARCHAR(30) NOT NULL,
        worker_salary NUMERIC(10, 2) NOT NULL,
        worker_admission INTEGER NOT NULL
    );

-- Comentários da tabela e colunas
COMMENT ON TABLE funcionario IS 'Tabela que representa um funcionário no sistema.';

COMMENT ON COLUMN funcionario.worker_id IS 'ID do funcionário, gerado automaticamente.';

COMMENT ON COLUMN funcionario.worker_cpf IS 'CPF do funcionário, campo único.';

COMMENT ON COLUMN funcionario.worker_name IS 'Nome do funcionário.';

COMMENT ON COLUMN funcionario.worker_phone IS 'Telefone do funcionário.';

COMMENT ON COLUMN funcionario.worker_mail IS 'Email do funcionário, campo único.';

COMMENT ON COLUMN funcionario.worker_role IS 'Cargo do funcionário.';

COMMENT ON COLUMN funcionario.worker_salary IS 'Salário do funcionário.';

COMMENT ON COLUMN funcionario.worker_admission IS 'Ano de admissão do funcionário.';

-- Criação da tabela Vaga
CREATE TABLE
    vaga (
        spot_id SERIAL PRIMARY KEY,
        spot_available BOOLEAN NOT NULL,
        spot_time INT NOT NULL,
        spot_local VARCHAR(50) NOT NULL
    );

-- Comentários da tabela e colunas
COMMENT ON TABLE vaga IS 'Tabela que armazena as vagas de estacionamento disponíveis no sistema';

COMMENT ON COLUMN vaga.spot_id IS 'Identificador único da vaga de estacionamento';

COMMENT ON COLUMN vaga.spot_available IS 'Disponibilidade da vaga (true = disponível, false = ocupada)';

COMMENT ON COLUMN vaga.spot_time IS 'Tempo permitido para uso da vaga (em minutos)';

COMMENT ON COLUMN vaga.spot_local IS 'Localização específica da vaga no estacionamento';

-- Criação da tabela Reserva
CREATE TABLE
    reserva (
        booking_id SERIAL PRIMARY KEY,
        booking_status VARCHAR(20) NOT NULL,
        spot_id BIGINT NOT NULL,
        veicule_sign VARCHAR(8) NOT NULL,
        client_id BIGINT NOT NULL,
        CONSTRAINT fk_spot_id FOREIGN KEY (spot_id) REFERENCES vaga (spot_id),
        CONSTRAINT fk_veicule_sign FOREIGN KEY (veicule_sign) REFERENCES veiculo (veicule_sign),
        CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES cliente (client_id)
    );

-- Comentários da tabela e colunas
COMMENT ON TABLE reserva IS 'Tabela que armazena as reservas de vagas de estacionamento';

COMMENT ON COLUMN reserva.booking_id IS 'Identificador único da reserva';

COMMENT ON COLUMN reserva.booking_status IS 'Status atual da reserva (ex.: ativa, concluída)';

COMMENT ON COLUMN reserva.spot_id IS 'Identificador da vaga de estacionamento associada à reserva';

COMMENT ON COLUMN reserva.veicule_sign IS 'Placa do veículo associado à reserva';

COMMENT ON COLUMN reserva.client_id IS 'Identificador do cliente que realizou a reserva';

-- Criação da tabela Pagamento
CREATE TABLE
    pagamento (
        payment_id SERIAL PRIMARY KEY,
        payment_cost DECIMAL(10, 2) NOT NULL,
        payment_date DATE NOT NULL,
        payment_method VARCHAR(50) NOT NULL,
        booking_id BIGINT NOT NULL,
        CONSTRAINT fk_booking_id FOREIGN KEY (booking_id) REFERENCES reserva (booking_id)
    );

-- Comentários da tabela e colunas
COMMENT ON TABLE pagamento IS 'Tabela que armazena os registros de pagamento de reservas no sistema';

COMMENT ON COLUMN pagamento.payment_id IS 'Identificador único do pagamento';

COMMENT ON COLUMN pagamento.payment_cost IS 'Custo total do pagamento';

COMMENT ON COLUMN pagamento.payment_date IS 'Data em que o pagamento foi realizado';

COMMENT ON COLUMN pagamento.payment_method IS 'Método utilizado para o pagamento (ex.: cartão, dinheiro)';

COMMENT ON COLUMN pagamento.booking_id IS 'Identificador da reserva associada ao pagamento';

-- Criação da tabela Manutencao
CREATE TABLE
    manutencao (
        fix_id SERIAL PRIMARY KEY,
        fix_description VARCHAR(50) NOT NULL,
        fix_date DATE,
        fix_cost NUMERIC(10, 2) NOT NULL,
        worker_cpf VARCHAR(11) NOT NULL,
        CONSTRAINT fk_worker FOREIGN KEY (worker_cpf) REFERENCES funcionario (worker_cpf)
    );

-- Comentários da tabela e colunas
COMMENT ON TABLE manutencao IS 'Tabela que registra a manutenção de veículos no sistema.';

COMMENT ON COLUMN manutencao.fix_id IS 'ID da manutenção, gerado automaticamente.';

COMMENT ON COLUMN manutencao.fix_description IS 'Descrição da manutenção realizada.';

COMMENT ON COLUMN manutencao.fix_date IS 'Data em que a manutenção foi realizada.';

COMMENT ON COLUMN manutencao.fix_cost IS 'Custo da manutenção.';

COMMENT ON COLUMN manutencao.worker_cpf IS 'CPF do funcionário responsável pela manutenção.';

-- Criação da tabela Historico_Utilizacao
CREATE TABLE
    historico_utilizacao (
        history_id BIGSERIAL PRIMARY KEY,
        history_entry_date DATE NOT NULL,
        history_leave_date DATE NOT NULL,
        spot_id BIGINT NOT NULL,
        veicule_sign VARCHAR(8) NOT NULL,
        CONSTRAINT fk_history_spot FOREIGN KEY (spot_id) REFERENCES vaga (spot_id),
        CONSTRAINT fk_history_veiculo FOREIGN KEY (veicule_sign) REFERENCES veiculo (veicule_sign)
    );

-- Comentários da tabela e colunas
COMMENT ON TABLE historico_utilizacao IS 'Tabela que representa o histórico de utilização de vagas por veículos.';

COMMENT ON COLUMN historico_utilizacao.history_id IS 'ID único do histórico de utilização.';

COMMENT ON COLUMN historico_utilizacao.history_entry_date IS 'Data de entrada do veículo na vaga.';

COMMENT ON COLUMN historico_utilizacao.history_leave_date IS 'Data de saída do veículo da vaga.';

COMMENT ON COLUMN historico_utilizacao.spot_id IS 'ID da vaga utilizada.';

COMMENT ON COLUMN historico_utilizacao.veicule_sign IS 'Placa do veículo que utilizou a vaga.';


-- Índices nas tabelas para melhorar o desempenho de busca
CREATE INDEX idx_cliente_nome ON Cliente(client_name);
CREATE INDEX idx_veiculo_marca ON Veiculo(veicule_brand);
CREATE INDEX idx_reserva_id ON Reserva(booking_id);
CREATE INDEX idx_funcionario_nome ON Funcionario(worker_name);
CREATE INDEX idx_manutencao_data ON Manutencao(fix_date);
CREATE INDEX idx_pagamento_data ON Pagamento(payment_date);
CREATE INDEX idx_hist_utilizacao_data ON Historico_utilizacao(history_entry_date);
