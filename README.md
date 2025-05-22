🏍️ Mottu API – Sistema de Gerenciamento de Motos
📄 Descrição do Projeto
Este projeto é uma API REST desenvolvida em Java com Spring Boot, que oferece funcionalidades de gestão de motos, clientes e suas relações, permitindo controle, consulta e atualização dos dados. Foi criado como parte do Challenge da disciplina Java Advanced.

A API permite operações como:

Cadastro, edição, listagem e exclusão de motos e clientes.

Busca com filtros, paginação e ordenação.

Validação de dados na entrada.

Cache de consultas para melhorar a performance.

Tratamento de erros centralizado e boas práticas de design REST.

-----------------------------------------------------------------------

👨‍💻 Desenvolvedores:

Carlos Eduardo R C Pacheco – RM: 557323

João Pedro Amorim Brito Virgens – RM: 559213

Pedro Augusto Costa Ladeira – RM: 558514

-----------------------------------------------------------------------

🚀 Tecnologias Utilizadas
Java 17

Spring Boot 3.2.5

Spring Web

Spring Data JPA

Spring Validation

Spring Cache

Maven

Oracle Database

Hibernate

Lombok (opcional)

-----------------------------------------------------------------------

⚙️ Como Executar o Projeto
🔧 Pré-requisitos
Java JDK 17 instalado

Oracle Database rodando (local ou remoto)

Maven instalado

IDE de sua escolha (IntelliJ, Eclipse, VS Code)

-----------------------------------------------------------------------

🏗️ Passos para rodar:
1️⃣ Clone ou baixe o projeto
2️⃣ Configure o Banco de Dados no arquivo src/main/resources/application.properties.
3️⃣ Compile e execute o projeto.
4️⃣ Acesse a API na URL padrão
    http://localhost:8080

-----------------------------------------------------------------------
    
🔗 Endpoints Principais
/api/motos – CRUD de motos

/api/clientes – CRUD de clientes

Funcionalidades como paginação, ordenação e filtros estão disponíveis via parâmetros na URL.

✅ Funcionalidades Implementadas
✅ CRUD completo para Moto e Cliente
✅ Relacionamento entre entidades
✅ Busca com parâmetros
✅ Paginação e ordenação
✅ Validação de campos (Bean Validation)
✅ Tratamento global de erros
✅ Uso de DTOs para entrada e saída de dados
✅ Cache para otimização de consultas
