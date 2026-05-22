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

    data_pagamento DATE NOT NULL,

    valor_total DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_folha_funcionario
        FOREIGN KEY (funcionario_id)
        REFERENCES funcionario(id)
);

