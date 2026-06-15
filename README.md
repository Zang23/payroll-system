# Sistema de Folha de Pagamento (Payroll System)

## Nome do Projeto

Sistema de Folha de Pagamento (Payroll System)

## Nome Completo dos Integrantes

* Thiago Araujo Souza Teles

## Tema Escolhido

Folha de Pagamento

## Descrição do Problema Resolvido

O projeto tem como objetivo auxiliar no gerenciamento de folhas de pagamento de funcionários. A ideia surgiu a partir de uma situação observada no ambiente de trabalho do autor, onde o processo de elaboração das folhas de pagamento demanda muitas horas de trabalho operacional e preenchimento manual de informações.

Para solucionar esse problema, foi desenvolvido um software desktop capaz de centralizar o gerenciamento dos funcionários, registrar jornadas de trabalho e automatizar o cálculo das folhas de pagamento, reduzindo o tempo gasto em tarefas repetitivas e minimizando possíveis erros humanos.

## Lista de Entidades Implementadas

* Funcionário
* Folha de Pagamento
* Dia Trabalhado
* Feriado

## Instruções para Execução

### Pré-requisitos

* Java 17 ou superior
* Docker instalado e em execução
* Git

### 1. Clonar o Repositório

```bash
git clone https://github.com/Zang23/payroll-system.git
cd payroll-system
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

## Divisão de Responsabilidades por Integrante

Como o projeto foi desenvolvido individualmente, todas as etapas foram realizadas por:

**Thiago Araujo Souza Teles**

Responsável por:

* Levantamento e definição dos requisitos;
* Modelagem das entidades e do banco de dados;
* Desenvolvimento da interface gráfica;
* Implementação das regras de negócio;
* Implementação da camada de persistência (DAO);
* Integração com banco de dados MariaDB;
* Configuração do ambiente Docker;
* Testes e correções;
* Documentação do projeto.

## Link para o Vídeo de Demonstração

Link do vídeo no YouTube:

**https://www.youtube.com/watch?v=SX1A_5rtlqQ**
