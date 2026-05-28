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

    valor_total DOUBLE NOT NULL

);

