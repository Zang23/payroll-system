USE folha_pagamento;

CREATE TABLE funcionario (

    id INT PRIMARY KEY AUTO_INCREMENT,

    nome VARCHAR(100) NOT NULL,

    email VARCHAR(100) NOT NULL UNIQUE,

    senha VARCHAR(100) NOT NULL,

    telefone VARCHAR(100) NOT NULL,

    tipo VARCHAR(30) NOT NULL

);

