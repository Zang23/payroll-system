USE folha_pagamento;

CREATE TABLE funcionario (

    id INT PRIMARY KEY AUTO_INCREMENT,

    nome VARCHAR(100) NOT NULL,

    email VARCHAR(100) NOT NULL UNIQUE,

    senha VARCHAR(100) NOT NULL,

    telefone VARCHAR(100) NOT NULL,

    tipo VARCHAR(30) NOT NULL

);

CREATE TABLE folha_pagamento (

    id INT PRIMARY KEY AUTO_INCREMENT,

    funcionario_id INT NOT NULL,

    valor_hora DOUBLE NOT NULL,

    total_dias_trabalhados INT NOT NULL,

    data_inicial DATE NOT NULL,

    data_final DATE NOT NULL,

    valor_total DOUBLE NOT NULL DEFAULT 0,

    CONSTRAINT fk_folha_funcionario
        FOREIGN KEY (funcionario_id)
        REFERENCES funcionario(id)
        ON DELETE CASCADE

);

CREATE TABLE dia_trabalhado (

    id INT PRIMARY KEY AUTO_INCREMENT,

    folha_pagamento_id BIGINT NOT NULL,

    nome_projeto VARCHAR(100),

    data_servico DATE NOT NULL,

    hora_inicio TIME NOT NULL,

    hora_fim TIME NOT NULL,

    viagem BOOLEAN DEFAULT FALSE,

    valor_calculado DECIMAL(10,2),

    CONSTRAINT fk_dia_folha
        FOREIGN KEY (folha_pagamento_id)
        REFERENCES folha_pagamento(id)
        ON DELETE CASCADE

);

CREATE TABLE feriado (

    id INT PRIMARY KEY AUTO_INCREMENT,

    data_feriado DATE NOT NULL UNIQUE,

    descricao VARCHAR(100)

);