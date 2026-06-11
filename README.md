# 💰 Payroll System

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Gradle](https://img.shields.io/badge/Gradle-9.x-blue)
![MariaDB](https://img.shields.io/badge/MariaDB-Database-brown)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED)

Sistema de gerenciamento de folha de pagamento desenvolvido como projeto prático para a disciplina de **Programação Orientada a Objetos (POO)** do curso de **Análise e Desenvolvimento de Sistemas da FATEC**.

O objetivo do projeto é fornecer uma aplicação desktop para cadastro e gerenciamento de funcionários, controle de jornadas de trabalho e geração de folhas de pagamento, aplicando conceitos de:

* Programação Orientada a Objetos (POO)
* Arquitetura BCE (Boundary-Control-Entity)
* Persistência de dados com padrão DAO
* Banco de dados relacional
* Containerização com Docker

---

---

## 🎥 Demonstração

📹 Vídeo completo:
https://youtube.com/...](https://youtu.be/SX1A_5rtlqQ)

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Descrição                              |
| ---------- | -------------------------------------- |
| Java 17+   | Linguagem principal do projeto         |
| Gradle 9.x | Gerenciamento de dependências e build  |
| MariaDB    | Banco de dados relacional              |
| Docker     | Ambiente de execução do banco de dados |
| JavaFX     | Interface gráfica desktop              |

---

## 🏗 Arquitetura do Sistema

O projeto segue a arquitetura **BCE (Boundary-Control-Entity)** para promover separação de responsabilidades e facilitar a manutenção do código.

Além disso, são utilizados **DTOs (Data Transfer Objects)** para transporte seguro de informações entre as camadas da aplicação.

### Estrutura Principal

```text
src/main/java
└── edu
    └── folhaPgto
        ├── boundary    # Interfaces gráficas (JavaFX)
        ├── control     # Regras de negócio e controle da aplicação
        ├── dao         # Persistência e acesso ao banco de dados
        ├── dto
        │   └── request # Objetos de transferência de dados
        └── entity      # Entidades do domínio
```

---

## 🐳 Executando o Banco de Dados com Docker

Para simplificar o ambiente de desenvolvimento, o projeto disponibiliza uma configuração Docker para o MariaDB.

Certifique-se de possuir o Docker instalado e execute:

```bash
docker compose up -d
```

O comando iniciará automaticamente uma instância do banco de dados com as configurações utilizadas pelo projeto.

---

## ▶️ Executando o Projeto

### Pré-requisitos

* Java 17 ou superior
* Docker instalado
* Git

### 1. Clonar o Repositório

```bash
git clone https://github.com/Zang23/payroll-system.git
cd Payroll-System
```

### 2. Iniciar o Banco de Dados

```bash
docker compose up -d
```

### 3. Executar a Aplicação

#### Windows

```bash
gradlew.bat run
```

#### Linux

```bash
./gradlew run
```

---

## 📚 Conceitos Aplicados

* Encapsulamento
* Herança
* Polimorfismo
* Abstração
* Injeção de Dependências
* DAO (Data Access Object)
* DTO (Data Transfer Object)
* Arquitetura BCE
* Persistência de Dados

---

## 📄 Licença

Este projeto foi desenvolvido exclusivamente para fins acadêmicos.

---

## 👨‍💻 Autor

**Thiago Araujo Souza Teles**

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos da FATEC.
