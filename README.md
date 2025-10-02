# 🏍️ Mottu API – Sistema de Gerenciamento de Motos

## 📄 Descrição do Projeto

A **Mottu API** é uma aplicação REST desenvolvida em Java com Spring Boot, criada como parte do Challenge da disciplina Java Advanced. O sistema permite a gestão de motos, clientes e suas relações, oferecendo controle, consulta e atualização dos dados.

Principais operações:
- Cadastro, edição, listagem e exclusão de motos e clientes
- Busca com filtros, paginação e ordenação
- Validação de dados na entrada
- Cache de consultas para performance
- Tratamento de erros centralizado e boas práticas REST

---

## 👨‍💻 Desenvolvedores
- Carlos Eduardo R C Pacheco – RM: 557323
- João Pedro Amorim Brito Virgens – RM: 559213
- Pedro Augusto Costa Ladeira – RM: 558514

---

## 🚀 Tecnologias Utilizadas
- Java 17
- Spring Boot 3.2.5
- Spring Web
- Spring Data JPA
- Spring Validation
- Spring Cache
- Maven
- Oracle Database
- Hibernate
- Lombok (opcional)

---

## ⚙️ Pré-requisitos
- Java JDK 17 instalado
- Oracle Database rodando (local ou remoto)
- Maven instalado
- IDE de sua escolha (IntelliJ, Eclipse, VS Code)

---

## 🏗️ Instalação e Execução

### 1️⃣ Clone o repositório
```bash
git clone https://github.com/cadupacheco/Sprint-Java.git
cd Sprint-Java
```

### 2️⃣ Configure o Banco de Dados
Edite o arquivo `src/main/resources/application.properties` com as credenciais do seu Oracle Database:
```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
```

### 3️⃣ Compile e execute o projeto
```bash
mvn clean install
mvn spring-boot:run
```

### 4️⃣ Acesse a API
A aplicação estará disponível em:
[http://localhost:8080](http://localhost:8080)

---

## 🔗 Endpoints Principais
- `GET /api/motos` – Listar motos
- `POST /api/motos` – Cadastrar moto
- `PUT /api/motos/{id}` – Atualizar moto
- `DELETE /api/motos/{id}` – Remover moto
- `GET /api/clientes` – Listar clientes
- `POST /api/clientes` – Cadastrar cliente
- `PUT /api/clientes/{id}` – Atualizar cliente
- `DELETE /api/clientes/{id}` – Remover cliente

> **Obs:** Funcionalidades como paginação, ordenação e filtros estão disponíveis via parâmetros na URL.

---

## ✅ Funcionalidades Implementadas
- CRUD completo para Moto e Cliente
- Relacionamento entre entidades
- Busca com parâmetros
- Paginação e ordenação
- Validação de campos (Bean Validation)
- Tratamento global de erros
- Uso de DTOs para entrada e saída de dados
- Cache para otimização de consultas

---

## 🛠️ Testes
Para rodar os testes automatizados:
```bash
mvn test
```

---

## 📫 Contato
Dúvidas ou sugestões? Entre em contato com um dos desenvolvedores ou abra uma issue no repositório.

---

## 📝 Licença
Este projeto é apenas para fins acadêmicos e não possui licença comercial.
