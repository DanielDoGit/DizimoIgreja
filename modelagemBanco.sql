CREATE TABLE Cidade (
  cidadeId INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  cidadeNomeCidade VARCHAR(255)  NULL  ,
  cidadeUf CHAR(2)  NULL    ,
PRIMARY KEY(cidadeId));


CREATE TABLE Permissoes (
  permiId INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  permiNome VARCHAR(255)  NULL    ,
PRIMARY KEY(permiId)  ,
UNIQUE INDEX PermissoesNome_UN(permiNome));


CREATE TABLE CategoriaFuncionario (
  catFuncId INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  catFuncNome VARCHAR(255)  NULL    ,
PRIMARY KEY(catFuncId)  ,
UNIQUE INDEX nomeCatFunc_UN(catFuncNome));


CREATE TABLE Fornecedor (
  forId INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Cidade_cidadeId INTEGER UNSIGNED  NOT NULL  ,
  cnpj INTEGER UNSIGNED  NOT NULL  ,
  forNome VARCHAR(255)  NULL  ,
  forCelular INTEGER UNSIGNED  NULL  ,
  forTelefone INTEGER UNSIGNED  NULL  ,
  forRazaoSocial VARCHAR(255)  NULL  ,
  forEndereco VARCHAR(255)  NULL  ,
  forNEndereco INTEGER UNSIGNED  NULL    ,
PRIMARY KEY(forId)  ,
UNIQUE INDEX Nome_Razao_UN(forNome, forRazaoSocial, cnpj)  ,
INDEX idCidade_Fornecedor_FK(Cidade_cidadeId),
  FOREIGN KEY(Cidade_cidadeId)
    REFERENCES Cidade(cidadeId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);


CREATE TABLE Comunidade (
  comuId INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Cidade_cidadeId INTEGER UNSIGNED  NOT NULL  ,
  comuFantasia VARCHAR(255)  NULL  ,
  comuRazaoSocial VARCHAR(255)  NULL  ,
  comuCnpj INTEGER UNSIGNED  NULL  ,
  comuEndereco VARCHAR(255)  NULL  ,
  comuNumeroEndereco VARCHAR(255)  NULL  ,
  comuBairro VARCHAR(20)  NULL    ,
PRIMARY KEY(comuId)  ,
UNIQUE INDEX Comunidade_Unique(comuFantasia, comuRazaoSocial, comuCnpj)  ,
INDEX Comunidade_FK_cidade(Cidade_cidadeId),
  FOREIGN KEY(Cidade_cidadeId)
    REFERENCES Cidade(cidadeId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);


CREATE TABLE Coletor (
  colId INTEGER UNSIGNED  NOT NULL  ,
  Comunidade_comuId INTEGER UNSIGNED  NOT NULL  ,
  Cidade_cidadeId INTEGER UNSIGNED  NOT NULL  ,
  Coletor_colId INTEGER UNSIGNED  NOT NULL  ,
  colCpf INTEGER UNSIGNED  NOT NULL  ,
  colNome VARCHAR(255)  NULL  ,
  colDataNascimento DATE  NULL  ,
  colCelular INTEGER UNSIGNED  NULL  ,
  colTelefone INTEGER UNSIGNED  NULL  ,
  colEndereco VARCHAR(255)  NULL  ,
  colNEndereco INTEGER UNSIGNED  NULL  ,
  colObservacoes VARCHAR(255)  NULL  ,
  colRg INTEGER UNSIGNED  NULL    ,
PRIMARY KEY(colId)  ,
INDEX idCidade_FK_coletor(Cidade_cidadeId)  ,
UNIQUE INDEX coletor_UN(colCpf, colNome, colRg)  ,
INDEX Comunidade_FK_coletor(Comunidade_comuId),
  FOREIGN KEY(Cidade_cidadeId)
    REFERENCES Cidade(cidadeId)
      ON DELETE CASCADE
      ON UPDATE NO ACTION,
  FOREIGN KEY(Coletor_colId)
    REFERENCES Coletor(colId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Comunidade_comuId)
    REFERENCES Comunidade(comuId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);


CREATE TABLE Dizimista (
  dizId INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Cidade_cidadeId INTEGER UNSIGNED  NOT NULL  ,
  Coletor_colId INTEGER UNSIGNED  NOT NULL  ,
  dizCpf INTEGER UNSIGNED  NOT NULL  ,
  dizNome VARCHAR(255)  NULL  ,
  dizDataNascimento DATE  NULL  ,
  dizCelular INTEGER UNSIGNED  NULL  ,
  dizTelefone INTEGER UNSIGNED  NULL  ,
  dizEndereco VARCHAR(255)  NULL  ,
  dizNEndereco INTEGER UNSIGNED  NULL  ,
  dizObservacoes VARCHAR(255)  NULL  ,
  dizDataCadastro DATE  NULL    ,
PRIMARY KEY(dizId)  ,
INDEX idCidade_FK(Cidade_cidadeId)  ,
UNIQUE INDEX NomeCpf_UN(dizNome, dizCpf, dizDataCadastro)  ,
INDEX Dizimista_FKIndex2(Coletor_colId),
  FOREIGN KEY(Cidade_cidadeId)
    REFERENCES Cidade(cidadeId)
      ON DELETE CASCADE
      ON UPDATE NO ACTION,
  FOREIGN KEY(Coletor_colId)
    REFERENCES Coletor(colId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);


CREATE TABLE Funcionario (
  funcId INTEGER UNSIGNED  NOT NULL  ,
  CategoriaFuncionario_catFuncId INTEGER UNSIGNED  NOT NULL  ,
  Cidade_cidadeId INTEGER UNSIGNED  NOT NULL  ,
  funcRg INTEGER UNSIGNED  NULL  ,
  funcCpf INTEGER UNSIGNED  NOT NULL  ,
  funcNome VARCHAR(255)  NULL  ,
  funcDataNascimento DATE  NULL  ,
  funcCelular INTEGER UNSIGNED  NULL  ,
  funcTelefone INTEGER UNSIGNED  NULL  ,
  funcEndereco VARCHAR(255)  NULL  ,
  funcNumeroEndereco INTEGER UNSIGNED  NULL  ,
  funcObservacoes VARCHAR(255)  NULL    ,
PRIMARY KEY(funcId)  ,
INDEX idCidade_FK(Cidade_cidadeId)  ,
INDEX IdCategoraFuncionario_FK(CategoriaFuncionario_catFuncId)  ,
UNIQUE INDEX cpfRgFuncionario_UN(funcCpf, funcRg, funcNome),
  FOREIGN KEY(Cidade_cidadeId)
    REFERENCES Cidade(cidadeId)
      ON DELETE CASCADE
      ON UPDATE NO ACTION,
  FOREIGN KEY(CategoriaFuncionario_catFuncId)
    REFERENCES CategoriaFuncionario(catFuncId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION)
AUTO_INCREMENT = 1;


CREATE TABLE ContasAPagar (
  contPagId INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Fornecedor_forId INTEGER UNSIGNED  NOT NULL  ,
  contPagDescricao VARCHAR(255)  NULL  ,
  contPagFormatoPagamento VARCHAR(255)  NULL  ,
  contPagDataPagamento DATETIME  NULL    ,
PRIMARY KEY(contPagId)  ,
INDEX ContasAPagar_FK(Fornecedor_forId),
  FOREIGN KEY(Fornecedor_forId)
    REFERENCES Fornecedor(forId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);


CREATE TABLE RecebimentoDizimo (
  recDizimoId INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Dizimista_dizId INTEGER UNSIGNED  NOT NULL  ,
  recDizimoValorRecebimento REAL  NULL  ,
  recDizimoDataRecebimento DATETIME  NULL    ,
PRIMARY KEY(recDizimoId)  ,
INDEX dataRecebimento_UN(recDizimoDataRecebimento)  ,
INDEX RecebimentoDizimo_FK(Dizimista_dizId),
  FOREIGN KEY(Dizimista_dizId)
    REFERENCES Dizimista(dizId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);


CREATE TABLE PermissaoFuncionario (
  Permissoes_permiId INTEGER UNSIGNED  NOT NULL  ,
  Funcionario_funcId INTEGER UNSIGNED  NOT NULL    ,
PRIMARY KEY(Permissoes_permiId, Funcionario_funcId)  ,
INDEX Funcionario_has_Permissoes_FKIndex1(Funcionario_funcId)  ,
INDEX Funcionario_has_Permissoes_FKIndex2(Permissoes_permiId),
  FOREIGN KEY(Funcionario_funcId)
    REFERENCES Funcionario(funcId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Permissoes_permiId)
    REFERENCES Permissoes(permiId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);


CREATE TABLE Missa (
  missaId INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Funcionario_funcId INTEGER UNSIGNED  NOT NULL  ,
  Comunidade_comuId INTEGER UNSIGNED  NOT NULL  ,
  missaDataHorario DATETIME  NULL    ,
PRIMARY KEY(missaId)  ,
INDEX Missa_FKIndex1(Comunidade_comuId)  ,
INDEX Missa_FKIndex2(Funcionario_funcId),
  FOREIGN KEY(Comunidade_comuId)
    REFERENCES Comunidade(comuId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Funcionario_funcId)
    REFERENCES Funcionario(funcId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);


CREATE TABLE RecebimentoMissa (
  recebMissaId INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Missa_missaId INTEGER UNSIGNED  NOT NULL  ,
  recebMissaValorRecebido FLOAT  NULL  ,
  recebMissaDataRecebimento DATE  NULL    ,
PRIMARY KEY(recebMissaId)  ,
INDEX RecebimentoMissa_FKIndex1(Missa_missaId),
  FOREIGN KEY(Missa_missaId)
    REFERENCES Missa(missaId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);


CREATE TABLE Caixa (
  caixaid INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  RecebimentoMissa_recebMissaId INTEGER UNSIGNED  NOT NULL  ,
  ContasAPagar_contPagId INTEGER UNSIGNED  NOT NULL  ,
  RecebimentoDizimo_recDizimoId INTEGER UNSIGNED  NOT NULL  ,
  caixaDescricaoorigemPagamento VARCHAR(255)  NULL  ,
  caixavalor DOUBLE  NULL  ,
  caixadatarecebimento DATETIME  NULL    ,
PRIMARY KEY(caixaid)  ,
INDEX Caixa_FKIndex1(RecebimentoDizimo_recDizimoId)  ,
INDEX Caixa_FKIndex2(ContasAPagar_contPagId)  ,
INDEX Caixa_FKIndex3(RecebimentoMissa_recebMissaId),
  FOREIGN KEY(RecebimentoDizimo_recDizimoId)
    REFERENCES RecebimentoDizimo(recDizimoId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(ContasAPagar_contPagId)
    REFERENCES ContasAPagar(contPagId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(RecebimentoMissa_recebMissaId)
    REFERENCES RecebimentoMissa(recebMissaId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



