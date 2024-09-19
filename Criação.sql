CREATE TABLE Cliente (
  client_id SERIAL NOT NULL,
  client_cpf   varchar(11) NOT NULL, 
  client_name  varchar(40) NOT NULL, 
  client_phone  int4 NOT NULL, 
  client_mail varchar(30), 
  client_age int4 NOT NULL, 
  client_sex  char(1) CHECK(client_sex in ('M' , 'F')), 
  PRIMARY KEY (client_cpf));

COMMENT ON TABLE Cliente IS 'Tabela de Gerenciamento de Clientes';
COMMENT ON COLUMN Cliente.client_cpf IS 'Cpf do Cliente';
COMMENT ON COLUMN Cliente.client_name IS 'Nome do Cliente';
COMMENT ON COLUMN Cliente.client_phone IS 'Número de telefone do cliente';
COMMENT ON COLUMN Cliente.client_mail IS 'Email do Cliente';
COMMENT ON COLUMN Cliente.client_age IS 'Idade do Cliente';
COMMENT ON COLUMN Cliente.client_sex IS 'Sexo do Cliente

M - Masculino
F - Feminino';

CREATE TABLE Funcionario (
  worker_id SERIAL NOT NULL,
  worker_cpf      varchar(11) NOT NULL, 
  worker_name     varchar(40) NOT NULL, 
  worker_phone     int4 NOT NULL, 
  worker_mail    varchar(30), 
  worker_role    varchar(30) NOT NULL, 
  worker_salary    numeric(10, 2) NOT NULL, 
  worker_admission date NOT NULL, 
  PRIMARY KEY (worker_cpf));

COMMENT ON TABLE Funcionario IS 'Tabela de gerenciamento de funcionários';
COMMENT ON COLUMN Funcionario.worker_cpf IS 'Cpf do funcionário';
COMMENT ON COLUMN Funcionario.worker_name IS 'Nome do funcionário';
COMMENT ON COLUMN Funcionario.worker_phone IS 'Telefone do funcionário';
COMMENT ON COLUMN Funcionario.worker_mail IS 'Email do funcionário';
COMMENT ON COLUMN Funcionario.worker_role IS 'Cargo do funcionário';
COMMENT ON COLUMN Funcionario.worker_salary IS 'Salário do funcionário';
COMMENT ON COLUMN Funcionario.worker_admission IS 'Data de admissão do funcionário';

CREATE TABLE Veiculo (
  veicule_sign varchar(8) NOT NULL, 
  veicule_brand varchar(16) NOT NULL, 
  veicule_model varchar(16) NOT NULL, 
  veicule_color   varchar(16), 
  veicule_year   varchar(4), 
  client_cpf   varchar(11) NOT NULL, 
  PRIMARY KEY (veicule_sign));

COMMENT ON TABLE Veiculo IS 'Tabela de cadastro dos veículos';
COMMENT ON COLUMN Veiculo.veicule_sign IS 'Placa dos veículos cadastrados';
COMMENT ON COLUMN Veiculo.veicule_brand IS 'Marca dos veículos cadastrados';
COMMENT ON COLUMN Veiculo.veicule_model IS 'Modelo do veículo cadastrado';
COMMENT ON COLUMN Veiculo.veicule_color IS 'Cor do veículo cadastrado';
COMMENT ON COLUMN Veiculo.veicule_year IS 'Ano do modelo do carro';

CREATE TABLE Endereco (
  adress_number    SERIAL NOT NULL, 
  adress_street    varchar(30) NOT NULL, 
  adress_neighbor varchar(30) NOT NULL, 
  adress_extra   varchar(30), 
  adress_city   varchar(40) NOT NULL, 
  client_cpf     varchar(11) NOT NULL, 
  PRIMARY KEY (adress_number));

COMMENT ON TABLE Endereco IS 'Tabela de gerenciamento de endereço dos clientes';
COMMENT ON COLUMN Endereco.adress_number IS 'Número da casa do endereço';
COMMENT ON COLUMN Endereco.adress_street IS 'rua do endereço do cliente';
COMMENT ON COLUMN Endereco.adress_neighbor IS 'Bairro do endereço do cliente';
COMMENT ON COLUMN Endereco.adress_extra IS 'Completemento do endereço do cliente';
COMMENT ON COLUMN Endereco.adress_city IS 'Cidade do endereço do cliente';

CREATE TABLE Manutencao (
  fix_id    SERIAL NOT NULL, 
  fix_description  varchar(50) NOT NULL, 
  fix_date  date, 
  fix_cost numeric(6, 2) NOT NULL, 
  worker_cpf    varchar(11) NOT NULL, 
  PRIMARY KEY (fix_id));

COMMENT ON TABLE Manutencao IS 'Tabela de gerenciamento da manutenção';
COMMENT ON COLUMN Manutencao.fix_id IS 'Id da manutenção da vaga';
COMMENT ON COLUMN Manutencao.fix_description IS 'Descrição da manutenção da vaga';
COMMENT ON COLUMN Manutencao.fix_date IS 'Data da manutenção';
COMMENT ON COLUMN Manutencao.fix_cost IS 'Custo da manutenção da vaga';

CREATE TABLE Vaga (
  spot_code    SERIAL NOT NULL, 
  spot_available char(1) NOT NULL CHECK(spot_available in ('D' , 'I')), 
  spot_time   time(7) NOT NULL, 
  spot_local  varchar(50), 
  fix_id    int4 NOT NULL, 
  PRIMARY KEY (spot_code));

COMMENT ON TABLE Vaga IS 'Tabela de Gereciamento das vagas';
COMMENT ON COLUMN Vaga.spot_code IS 'Código da vaga';
COMMENT ON COLUMN Vaga.spot_available IS 'Disponibilidade da vaga
D - Disponivel
I - Indisponível';
COMMENT ON COLUMN Vaga.spot_time IS 'Tempo de alocação da vaga';
COMMENT ON COLUMN Vaga.spot_local IS 'Localização das vagas';

CREATE TABLE Reserva (
  booking_id       SERIAL NOT NULL, 
  booking_initial_date date NOT NULL, 
  booking_final_date date NOT NULL, 
  booking_status     varchar(20) NOT NULL, 
  spot_code       int4 NOT NULL, 
  veicule_sign     varchar(8) NOT NULL, 
  PRIMARY KEY (booking_id));

COMMENT ON TABLE Reserva IS 'Tabela de gerenciamento de reserva';
COMMENT ON COLUMN Reserva.booking_id IS 'Id da reserva de vaga';
COMMENT ON COLUMN Reserva.booking_initial_date IS 'Inicio da data da reserva';
COMMENT ON COLUMN Reserva.booking final_datew IS 'Data de encerramento da reserva da vaga';
COMMENT ON COLUMN Reserva.booking_status IS 'Status da reserva';

CREATE TABLE Pagamento (
  payment_id       SERIAL NOT NULL, 
  payment_cost    numeric(5, 2) NOT NULL, 
  payment_date date NOT NULL, 
  payment_method    varchar(50) NOT NULL, 
  booking_id      int4 NOT NULL, 
  PRIMARY KEY (payment_id));

COMMENT ON TABLE Pagamento IS 'Tabela de gerenciamento de pagamento';
COMMENT ON COLUMN Pagamento.payment_id IS 'Id de pagamento da vaga';
COMMENT ON COLUMN Pagamento.payment_cost IS 'Valor do pagamento da vaga';
COMMENT ON COLUMN Pagamento.payment_date IS 'Data de pagamento';
COMMENT ON COLUMN Pagamento.payment_method IS 'Método de pagamento';

CREATE TABLE Historico_utilizacao (
  history_id         SERIAL NOT NULL, 
  history_entry_date date NOT NULL, 
  history_leave_date date NOT NULL, 
  spot_code        int4 NOT NULL, 
  veicule_sign      varchar(8) NOT NULL, 
  PRIMARY KEY (history_id));

COMMENT ON TABLE Historico_utilizacao IS 'Tabela de gerenciamento do histórico de utilização';
COMMENT ON COLUMN Historico_utilizacao.history_id IS 'Id do histórico';
COMMENT ON COLUMN Historico_utilizacao.history_entry_date IS 'Histórico de entradas da vaga';
COMMENT ON COLUMN Historico_utilizacao.history_leave_date IS 'HIstórico de saida da vaga';

ALTER TABLE Veiculo ADD CONSTRAINT FKVeiculo652748 FOREIGN KEY (client_cpf) REFERENCES Cliente (client_cpf);
ALTER TABLE Reserva ADD CONSTRAINT FKReserva520514 FOREIGN KEY (spot_code) REFERENCES Vaga (spot_code);
ALTER TABLE Reserva ADD CONSTRAINT FKReserva762773 FOREIGN KEY (veicule_sign) REFERENCES Veiculo (veicule_sign);
ALTER TABLE Pagamento ADD CONSTRAINT FKPagamento765087 FOREIGN KEY (booking_id) REFERENCES Reserva (booking_id);
ALTER TABLE Manutencao ADD CONSTRAINT FKManutencao178953 FOREIGN KEY (worker_cpf) REFERENCES Funcionario (worker_cpf);
ALTER TABLE Historico_utilizacao ADD CONSTRAINT FKHistorico_553925 FOREIGN KEY (spot_code) REFERENCES Vaga (spot_code);
ALTER TABLE Historico_utilizacao ADD CONSTRAINT FKHistorico_311666 FOREIGN KEY (veicule_sign) REFERENCES Veiculo (veicule_sign);
ALTER TABLE Endereco ADD CONSTRAINT FKEndereco841202 FOREIGN KEY (client_cpf) REFERENCES Cliente (client_cpf);
ALTER TABLE Vaga ADD CONSTRAINT FKVaga791300 FOREIGN KEY (fix_id) REFERENCES Manutencao (fix_id);
