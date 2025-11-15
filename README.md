# 🚗 API de Gerenciamento de Estacionamento

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring)
![Maven](https://img.shields.io/badge/Maven-3.6%2B-C71A36?style=for-the-badge&logo=apachemaven)

> Uma API REST robusta e flexível para gerenciamento completo de estacionamentos. O sistema permite ao usuário modelar 100% das regras de negócio, desde a definição de vagas e preços até o controle de fluxo de veículos.

<br>

---

## 🧩 Funcionalidades Principais

Este projeto oferece total controle sobre a operação do estacionamento:

* **Modelagem Flexível:** Defina o número exato de vagas (podendo aumentar ou diminuir dinamicamente).
* **Gestão de Veículos:** Registre veículos de forma persistente.
* **Categorias Personalizadas:** Crie categorias de veículos (ex: Carro, Moto, Van).
* **Precificação Dinâmica:** Defina preços específicos por categoria.
* **Controle de Fluxo:** Registre entradas e saídas de veículos com precisão.
* **Monitoramento:** Verifique em tempo real quais veículos estão estacionados.
* **Visão Geral:** Obtenha listas de vagas livres e ocupadas instantaneamente.
* **Documentação Completa:** Projeto 100% documentado com Swagger (OpenAPI 3).
* **Testes Facilitados:** Uma coleção Postman está inclusa no repositório.

## 🛠️ Tecnologias Utilizadas

O projeto foi construído com ferramentas modernas e robustas do ecossistema Java:

* **Java 17+**
* **Spring Boot 3 / Spring Web 6**
* **Spring Data JPA** (para persistência de dados)
* **Maven** (gerenciador de dependências)
* **H2 Database** (banco de dados em memória, padrão)
* **Springdoc-openapi** (para geração automática da UI do Swagger)

## 🏛️ Arquitetura

A API segue uma arquitetura em camadas (n-tier) clássica, promovendo a separação de responsabilidades, alta coesão e baixo acoplamento:

* **Controller:** Camada responsável por expor os endpoints REST, receber requisições HTTP e retornar respostas (DTOs).
* **Service:** Camada onde reside a lógica de negócio principal do sistema.
* **Repository:** Camada de acesso a dados, utilizando Spring Data JPA para abstrair as operações com o banco de dados.
* **Model:** Entidades JPA que mapeiam o modelo de domínio para o banco de dados.
* **DTO (Data Transfer Object):** Objetos usados para transportar dados entre as camadas, especialmente na comunicação cliente-servidor.

## 🚀 Como Executar

Siga os passos abaixo para executar o projeto localmente.

### Pré-requisitos

* **JDK 17+**
* **Maven 3.6+**
* **Ambiente Linux** (utilizado durante o desenvolvimento)

### Build e Execução

1.  **Clone o repositório:**
    ```bash
    git clone <https://github.com/BrunoVerly/estacionamento-faseh.git>
    cd <estacionamento-faseh>
    ```

2.  **Compile e empacote o projeto com Maven:**
    ```bash
    mvn clean package
    ```

3.  **Execute a aplicação:**
    ```bash
    java -jar target/<nome-do-jar>.jar
    ```

A API estará disponível em `http://localhost:8080`.

---

## 📚 Documentação (Swagger)

A documentação completa da API (OpenAPI 3) é gerada automaticamente pelo Springdoc e pode ser acessada nos seguintes endpoints após a execução:

* **Swagger UI (Interface gráfica):** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
* **JSON (Definição OpenAPI):**
    ```bash
    GET /v3/api-docs
    ```

## 📬 Coleção Postman

Para facilitar os testes, uma coleção Postman está incluída no projeto.

* **Localização:**
    ```
    src/main/resources/postman/Estacionamento Faseh.postman_collection.json
    ```

* **Como Testar:**
    1.  Importe o arquivo `Estacionamento Faseh.postman_collection.json` no seu Postman.
    2.  Configure a variável de ambiente `baseUrl` para `http://localhost:8080`.
    3.  Execute as requisições.

## 🗺️ Principais Endpoints

Visão geral dos recursos disponíveis na API.

### Veículo
* `GET /veiculo` - Lista todos os veículos.
* `GET /veiculo/{id}` - Busca veículo por ID.
* `POST /veiculo` - Registra um novo veículo.
* `PUT /veiculo` - Atualiza um veículo.
* `DELETE /veiculo/{id}` - Deleta um veículo.
* `GET /veiculo/estacionados` - Lista apenas veículos atualmente estacionados.

### Vaga
* `GET /vaga` - Lista todas as vagas.
* `GET /vaga/ocupada` - Lista vagas ocupadas.
* `GET /vaga/livre` - Lista vagas livres.
* CRUD completo (POST, PUT, DELETE) também disponível.

### Tabela (Categoria-Preço)
* CRUD completo (GET, POST, PUT, DELETE) para gerenciar categorias e preços.

### Estacionamento (Fluxo)
* `POST /estacionamento` - Registra a **entrada** de um veículo.
* `PUT /estacionamento` - Registra a **saída** de um veículo e calcula o valor a ser pago.

## 📄 DTOs Importantes

Exemplos de DTOs utilizados para a comunicação com a API:

* `EstacionamentoCreateDTO`
* `EstacionamentoSaidaDTO`
* `VagaOcupadaDTO`
* `VagaLivreDTO`
* `VeiculosEstacionadosDTO`

## 📁 Estrutura do Projeto

A estrutura de diretórios principal segue as convenções do Spring Boot:

```shell
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── com.example.demo
│   │   │           ├── controller/     # Controladores REST
│   │   │           ├── service/        # Lógica de negócio
│   │   │           ├── repository/     # Repositórios JPA
│   │   │           ├── dto/            # Data Transfer Objects
│   │   │           ├── model/          # Entidades de domínio
│   │   │           ├── exceptions/     # Handlers de exceção
│   │   │           │   └── ExceptionCustomizada.java
│   │   │           ├── config/
│   │   │           │   └── DataInitializer.java  # Carga inicial de dados
│   │   │           └── Application.java
│   │   └── resources
│   │       ├── postman/
│   │       │   └── Estacionamento Faseh.postman_collection.json
│   │       └── application.properties
```


## ⚠️ Tratamento de Erros

A API utiliza um handler global (`exceptions/ExceptionCustomizada.java`) para capturar e retornar exceções formatadas em JSON. Os principais tipos de exceções são:

* **`BadRequestException` → 400 Bad Request:** Usada para erros de validação ou regras de negócio (ex: veículo já estacionado, vaga não existe).
* **`InternalErrorException` → 500 Internal Server Error:** Usada para erros inesperados no servidor.