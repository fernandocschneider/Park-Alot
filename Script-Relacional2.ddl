CREATE TABLE Veiculo (
veic_placa varchar(8) NOT NULL,
veic_marca varchar(16) NOT NULL,
veic_model varchar(16) NOT NULL,
veic_cor varchar(16),
veic_ano varchar(4),
clie_cpf varchar(11) NOT NULL,
PRIMARY KEY (veic_placa));

COMMENT ON TABLE Veiculo IS 'Tabela de cadastro dos veículos';
COMMENT ON COLUMN Veiculo.veic_placa IS 'Placa dos veículos cadastrados';
COMMENT ON COLUMN Veiculo.veic_marca IS 'Marca dos veículos cadastrados';
COMMENT ON COLUMN Veiculo.veic_model IS 'Modelo do veículo cadastrado';
COMMENT ON COLUMN Veiculo.veic_cor IS 'Cor do veículo cadastrado';
COMMENT ON COLUMN Veiculo.veic_ano IS 'Ano do modelo do carro';

CREATE TABLE Cliente (
clie_cpf varchar(11) NOT NULL,
clie_nome varchar(40) NOT NULL,
clie_tele varchar(11) NOT NULL,
clie_email varchar(30),
clie_ender varchar(50) NOT NULL,
PRIMARY KEY (clie_cpf));

COMMENT ON TABLE Cliente IS 'Tabela de Gerenciamento de Clientes';
COMMENT ON COLUMN Cliente.clie_cpf IS 'Cpf do Cliente';
COMMENT ON COLUMN Cliente.clie_nome IS 'Nome do Cliente';
COMMENT ON COLUMN Cliente.clie_tele IS 'Número de telefone do cliente';
COMMENT ON COLUMN Cliente.clie_email IS 'Email do Cliente';
COMMENT ON COLUMN Cliente.clie_ender IS 'Endereço do cliente';

CREATE TABLE Vaga (
vaga_cod varchar(11) NOT NULL,
vaga_dispon char(1) NOT NULL CHECK(vaga_dispon in ('D' , 'I')),
vaga_temp time(7) NOT NULL, vaga_local varchar(50),
manut_id varchar(11) NOT NULL,
PRIMARY KEY (vaga_cod));

COMMENT ON TABLE Vaga IS 'Tabela de Gereciamento das vagas';
COMMENT ON COLUMN Vaga.vaga_cod IS 'Código da vaga';
COMMENT ON COLUMN Vaga.vaga_dispon IS 'Disponibilidade da vaga

D - Disponivel
I - Indisponível';

COMMENT ON COLUMN Vaga.vaga_temp IS 'Tempo de alocação da vaga';
COMMENT ON COLUMN Vaga.vaga_local IS 'Localização das vagas';

CREATE TABLE Reserva (
reser_id varchar(9) NOT NULL,
reser_data_ini date NOT NULL,
reser_data_fim date NOT NULL,
reser_stat varchar(20) NOT NULL,
vaga_cod varchar(11) NOT NULL,
veic_placa varchar(8) NOT NULL,
PRIMARY KEY (reser_id));

COMMENT ON TABLE Reserva IS 'Tabela de gerenciamento de reserva';
COMMENT ON COLUMN Reserva.reser_id IS 'Id da reserva de vaga';
COMMENT ON COLUMN Reserva.reser_data_ini IS 'Inicio da data da reserva';
COMMENT ON COLUMN Reserva.reser_data_fim IS 'Data de encerramento da reserva da vaga';
COMMENT ON COLUMN Reserva.reser_stat IS 'Status da reserva';

CREATE TABLE Pagamento (
paga_id varchar(9) NOT NULL,
paga_valor numeric(5, 2) NOT NULL,
paga_data_pag date NOT NULL,
paga_metod varchar(50) NOT NULL,
reser_id varchar(9) NOT NULL,
PRIMARY KEY (paga_id));

COMMENT ON TABLE Pagamento IS 'Tabela de gerenciamento de pagamento';
COMMENT ON COLUMN Pagamento.paga_id IS 'Id de pagamento da vaga';
COMMENT ON COLUMN Pagamento.paga_valor IS 'Valor do pagamento da vaga';
COMMENT ON COLUMN Pagamento.paga_data_pag IS 'Data de pagamento';
COMMENT ON COLUMN Pagamento.paga_metod IS 'Método de pagamento';

CREATE TABLE Funcionario (
func_cpf varchar(11) NOT NULL,
func_nome varchar(40) NOT NULL,
func_tele varchar(11) NOT NULL,
func_email varchar(30),
func_cargo varchar(30) NOT NULL,
func_salar numeric(10, 2) NOT NULL,
func_data_adm date NOT NULL,
PRIMARY KEY (func_cpf));

COMMENT ON TABLE Funcionario IS 'Tabela de gerenciamento de funcionários';
COMMENT ON COLUMN Funcionario.func_cpf IS 'Cpf do funcionário';
COMMENT ON COLUMN Funcionario.func_nome IS 'Nome do funcionário';
COMMENT ON COLUMN Funcionario.func_tele IS 'Telefone do funcionário';
COMMENT ON COLUMN Funcionario.func_email IS 'Email do funcionário';
COMMENT ON COLUMN Funcionario.func_cargo IS 'Cargo do funcionário';
COMMENT ON COLUMN Funcionario.func_salar IS 'Salário do funcionário';
COMMENT ON COLUMN Funcionario.func_data_adm IS 'Data de admissão do funcionário';

CREATE TABLE Manutencao (
manut_id varchar(11) NOT NULL,
manut_desc varchar(50) NOT NULL,
manut_data date,
manut_custo numeric(6, 2) NOT NULL,
func_cpf varchar(11) NOT NULL,
PRIMARY KEY (manut_id));

COMMENT ON TABLE Manutencao IS 'Tabela de gerenciamento da manutenção';
COMMENT ON COLUMN Manutencao.manut_id IS 'Id da manutenção da vaga';
COMMENT ON COLUMN Manutencao.manut_desc IS 'Descrição da manutenção da vaga';
COMMENT ON COLUMN Manutencao.manut_data IS 'Data da manutenção';
COMMENT ON COLUMN Manutencao.manut_custo IS 'Custo da manutenção da vaga';

CREATE TABLE Historico_Utilizacao (
hist_id varchar(11) NOT NULL,
hist_data_entra date NOT NULL,
hist_data_saida date NOT NULL,
vaga_cod varchar(11) NOT NULL,
veic_placa varchar(8) NOT NULL,
PRIMARY KEY (hist_id));

COMMENT ON TABLE Historico_Utilizacao IS 'Tabela de gerenciamento do histórico de utilização';
COMMENT ON COLUMN Historico_Utilizacao.hist_id IS 'Id do histórico';
COMMENT ON COLUMN Historico_Utilizacao.hist_data_entra IS 'Histórico de entradas da vaga';
COMMENT ON COLUMN Historico_Utilizacao.hist_data_saida IS 'HIstórico de saida da vaga';

CREATE TABLE Manutencao_Vaga (manut_id varchar(11) NOT NULL, vaga_cod varchar(11) NOT NULL, PRIMARY KEY (manut_id, vaga_cod));
ALTER TABLE Veiculo ADD CONSTRAINT FKVeiculo652748 FOREIGN KEY (clie_cpf) REFERENCES Cliente (clie_cpf);
ALTER TABLE Reserva ADD CONSTRAINT FKReserva520514 FOREIGN KEY (vaga_cod) REFERENCES Vaga (vaga_cod);
ALTER TABLE Reserva ADD CONSTRAINT FKReserva762773 FOREIGN KEY (veic_placa) REFERENCES Veiculo (veic_placa);
ALTER TABLE Pagamento ADD CONSTRAINT FKPagamento765087 FOREIGN KEY (reser_id) REFERENCES Reserva (reser_id);
ALTER TABLE Manutencao ADD CONSTRAINT FKManutencao178953 FOREIGN KEY (func_cpf) REFERENCES Funcionario (func_cpf);
ALTER TABLE Historico_Utilizacao ADD CONSTRAINT FKHistorico_950807 FOREIGN KEY (vaga_cod) REFERENCES Vaga (vaga_cod);
ALTER TABLE Historico_Utilizacao ADD CONSTRAINT FKHistorico_193067 FOREIGN KEY (veic_placa) REFERENCES Veiculo (veic_placa);
ALTER TABLE Manutencao_Vaga ADD CONSTRAINT FKManutencao996824 FOREIGN KEY (manut_id) REFERENCES Manutencao (manut_id);
ALTER TABLE Manutencao_Vaga ADD CONSTRAINT FKManutencao583198 FOREIGN KEY (vaga_cod) REFERENCES Vaga (vaga_cod);
