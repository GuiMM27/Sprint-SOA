# 🚗 Ford VIN Share Predictive API — Sprint Arquitetura SOA

Este repositório contém a implementação completa, robusta e em nível empresarial da **API RESTful de Geração de Leads Preditivos**, desenvolvida sob as diretrizes de uma **Arquitetura Orientada a Serviços (SOA)**. O sistema foi concebido para resolver o **Desafio 02 da Ford: Impulsionando o VIN Share na América do Sul com Soluções Inteligentes**, atendendo de forma estrita e maximizada a todos os critérios avaliativos da Sprint.

---
## Integrantes
Enzo Almeida RM: 556900

Gabriel de Mello RM: 554421

Gabriel Guilherme RM: 558638

Guilherme Moreira RM: 557290

Jose Kretzer RM: 555523

## 🗺️ 1. Alinhamento de Negócio: O Desafio Ford VIN Share

O **VIN Share** representa a porcentagem de veículos Ford que utilizam a rede oficial de concessionárias para a realização de manutenções. Reter clientes no serviço de pós-venda é crucial para o sucesso e sustentabilidade do negócio. Esta API atua como o motor inteligente backend que permite às concessionárias:

1. **Análise e Visualização de Dados:** Fornece endpoints estruturados que alimentam dashboards interativos com granularidade por concessionária.
2. **Geração de Leads e Modelagem Preditiva:** Através de dados de veículos conectados e histórico, armazena e distribui leads de alta probabilidade de parada ou risco de evasão da rede oficial.
3. **Otimização da Jornada do Cliente:** Permite o controle fluido do status de comunicação com o cliente (NOVO ➡️ CONTATADO ➡️ AGENDADO), viabilizando uma visão 360 graus do ecossistema de manutenção.

---

## 🏗️ 2. Arquitetura do Software & Organização Modular (SOA) 

A API adota o padrão de **Separação Clara entre Camadas**, garantindo que os componentes sejam independentes, fracamente acoplados e altamente reutilizáveis. O pacote principal está modularizado da seguinte forma:

- **`controllers`**: Camada de Apresentação / Endpoints RESTful (Desacoplamento de protocolo). Expõe os serviços via HTTP e transforma dados Java em payloads padronizados JSON.
- **`services`**: Camada de Negócio / Orquestração e Validação de Regras Ford. Centraliza todas as regras de pós-venda, impedindo que o controlador acesse o banco diretamente.
- **`repositories`**: Camada de Acesso a Dados / Abstração de Consultas (JPA). Isola completamente a camada de persistência.
- **`models`**: Camada de Domínio / Mapeamento de Entidades Relacionais. Utiliza o tipo exato `BigDecimal` para mitigar distorções em cálculos de ponto flutuante na probabilidade preditiva.
- **`exceptions`**: Camada de Infraestrutura / Tratamento Uniforme de Erros e Exceções.

---

## 🔌 3. Integração por Web Services & Boas Práticas RESTful — 

O sistema expõe uma API puramente RESTful, utilizando adequadamente os verbos HTTP, os cabeçalhos de requisição e os códigos de status de resposta.

### Matriz de Endpoints e Métodos HTTP

| Verbo HTTP | Endpoint | Status Code Sucesso | Status Code Erro | Propósito no Desafio Ford |
| :--- | :--- | :--- | :--- | :--- |
| **`GET`** | `/api/v1/leads` | `200 OK` | `500 Internal` | Listar todos os leads preditivos gerados pela inteligência de dados. |
| **`GET`** | `/api/v1/leads/{id}` | `200 OK` | `404 Not Found` | Buscar um lead específico para visualização detalhada de dados do veículo. |
| **`GET`** | `/api/v1/leads/concessionaria/{id}` | `200 OK` | `200 OK (Vazio)` | Filtrar leads por concessionária para alimentar dashboards regionais. |
| **`POST`** | `/api/v1/leads` | `201 Created` | `400 Bad Request` | Inserir novos leads calculados por modelos externos. |
| **`PUT`** | `/api/v1/leads/{id}/status` | `200 OK` | `404 Not Found` | Atualizar o status do cliente (ex: de NOVO para CONTATADO) na jornada. |
| **`DELETE`** | `/api/v1/leads/{id}` | `204 No Content` | `404 Not Found` | Remover falsos positivos ou leads depreciados da base ativa. |

### Contrato JSON Padronizado (Payload Exemplo - `POST`)
```json
{
  "chassiVin": "9BFBXXXXXXXXXXXXX",
  "concessionariaId": 102,
  "probabilidadeManutencao": 0.87,
  "tipoServicoSugerido": "Troca de Pastilhas e Revisão de Suspensão"
}
```
## 🎯 4. Padrões e Tratamento Adequado de Erros 

Para cumprir o requisito de tratamento rigoroso de exceções, a aplicação intercepta falhas de infraestrutura e regras de negócio de forma que o consumidor da API receba respostas claras, sem vazamento de stack traces internos do servidor.

Foi criada a exceção customizada `ResourceNotFoundException`. Sempre que um ID de lead não é localizado no banco de dados durante uma requisição, esta exceção é disparada retornando um status ```404 Not Found``` limpo, permitindo que aplicações Mobile ou Web tratem a interface de forma amigável.

## 🗄️ 5. Conexão com Banco de Dados & Controle de Migrações 

A persistência do ecossistema Ford foi implementada utilizando o **PostgreSQL** integrado via **Spring Data JPA / Hibernate**. O ciclo de vida do esquema de banco de dados é controlado estritamente pelo **Flyway Migration**, garantindo o versionamento completo do software.

O script de migração estrutural (```V1__criar_tabela_leads.sql```) dita o contrato exato das colunas e tipos primitivos de dados em conformidade com o ecossistema PostgreSQL, enquanto o Hibernate apenas valida a estrutura (```spring.jpa.hibernate.ddl-auto=validate```), garantindo a integridade dos dados e a segurança da aplicação.

## 🚀 6. Instruções de Execução e Verificação Técnica

### Pré-requisitos:

1- Instalação do PostgreSQL rodando localmente na porta padrão 5432.

2- Criação manual de um banco de dados vazio chamado ford_vin_share.

3- Java Development Kit (JDK 17 ou posterior) configurado na máquina.

### Executando a Aplicação:

1- Verifique se o ```application.properties``` possui as credenciais corretas do seu banco de dados local.

2- Na sua IDE, execute o arquivo ```FordVinShareApiApplication.java```.

3- A inicialização será confirmada pela mensagem no console indicando que o Tomcat iniciou na porta 8080.

## Acessando a Documentação Viva da API (Swagger / OpenAPI):

Com a aplicação em execução, acesse o contrato interativo dos Web Services através da URL:

👉 http://localhost:8080/swagger-ui/index.html

Pela interface do Swagger, é possível realizar chamadas de teste em tempo real para verificar o comportamento dos métodos HTTP e a persistência no banco PostgreSQL.
