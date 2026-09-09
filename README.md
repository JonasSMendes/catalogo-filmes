# 🎬 Catálogo de Filmes

Aplicação web para catalogação de filmes, desenvolvida como projeto acadêmico em **Java**, utilizando **Servlets**, **JSP**, **JDBC** e **MySQL**, seguindo a arquitetura **MVC**.

Permite cadastrar, listar, visualizar, editar, excluir e buscar filmes por título ou diretor, com persistência de dados em banco relacional.

## 📋 Funcionalidades

- ✅ Cadastro de novos filmes (título, diretor, ano, gênero, sinopse)
- ✅ Listagem de todos os filmes catalogados
- ✅ Visualização dos detalhes de um filme específico
- ✅ Edição das informações de um filme
- ✅ Exclusão de um filme do catálogo
- ✅ Busca por título ou diretor (parcial, case-insensitive)
- ✅ Persistência de dados em banco de dados MySQL

## 🛠️ Tecnologias utilizadas

- **Java 17**
- **Jakarta Servlets** — camada de controle
- **JSP + JSTL + Expression Language** — camada de apresentação
- **JDBC** — acesso ao banco de dados
- **MySQL** — banco de dados relacional
- **Apache Maven** — gerenciamento de dependências e build
- **Eclipse Jetty** — servidor de aplicação embutido (via plugin Maven)

## 🏗️ Arquitetura

O projeto segue o padrão **MVC**, organizado em pacotes:

```
src/main/java/br/edu/catalogofilmes/
 ├── model/     → Filme.java (entidade de domínio)
 ├── dao/       → FilmeDAO.java, ConexaoBD.java (acesso a dados)
 └── servlet/   → controladores (Cadastrar, Listar, Editar, Excluir, Detalhe, Buscar)

src/main/webapp/
 ├── listar.jsp
 ├── form.jsp
 ├── detalhe.jsp
 ├── cabecalho.jsp / rodape.jsp
 └── WEB-INF/web.xml
```

## 🚀 Como rodar o projeto

### Pré-requisitos

- JDK 17 ou superior
- MySQL instalado e em execução
- IntelliJ IDEA (ou outra IDE com suporte a Maven)

### Passo 1 — Clonar o repositório

```bash
git clone https://github.com/SEU_USUARIO/catalogo-filmes.git
cd catalogo-filmes
```

### Passo 2 — Criar o banco de dados

Execute o script abaixo no seu cliente MySQL:

```sql
CREATE DATABASE IF NOT EXISTS catalogo_filmes
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

USE catalogo_filmes;

CREATE TABLE filmes (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    titulo        VARCHAR(150)  NOT NULL,
    diretor       VARCHAR(120)  NOT NULL,
    ano           INT           NOT NULL,
    genero        VARCHAR(60)   NOT NULL,
    sinopse       TEXT,
    data_cadastro TIMESTAMP     DEFAULT CURRENT_TIMESTAMP
);
```

### Passo 3 — Configurar as credenciais do banco

Copie o arquivo de exemplo e preencha com suas credenciais reais:

```bash
cp src/main/resources/db.properties.example src/main/resources/db.properties
```

Edite `src/main/resources/db.properties`:

```properties
db.url=jdbc:mysql://localhost:3306/catalogo_filmes?useTimezone=true&serverTimezone=UTC
db.usuario=SEU_USUARIO_MYSQL
db.senha=SUA_SENHA_MYSQL
```

> ⚠️ Esse arquivo não é versionado (está no `.gitignore`) por conter dados sensíveis.

### Passo 4 — Rodar a aplicação

No IntelliJ, abra a aba **Maven** (lateral direita) e execute, na ordem:

1. `Lifecycle > clean`
2. `Lifecycle > package`
3. `Plugins > jetty > jetty:run`

Ou, via terminal (se tiver o Maven instalado globalmente):

```bash
mvn clean package
mvn jetty:run
```

### Passo 5 — Acessar

Abra o navegador em:

```
http://localhost:8080/catalogo-filmes/
```

## 📖 Manual de uso

| Ação | Como fazer |
|---|---|
| Ver todos os filmes | Clique em **"Listar Todos"** no menu |
| Cadastrar um filme | Clique em **"Cadastrar Novo Filme"**, preencha os campos obrigatórios (título, diretor, ano) e clique em **Salvar** |
| Ver detalhes | Na listagem, clique em **"Ver"** ao lado do filme desejado |
| Editar um filme | Na listagem, clique em **"Editar"**; altere os campos e clique em **Salvar** |
| Excluir um filme | Na listagem, clique em **"Excluir"** e confirme a ação |
| Buscar um filme | Digite parte do título ou do nome do diretor no campo de busca e clique em **Buscar** |

## 🔒 Segurança

- Proteção contra **SQL Injection** via `PreparedStatement`
- Proteção contra **XSS** via `<c:out>` (JSTL) na exibição de dados
- Credenciais do banco de dados fora do código-fonte versionado
- Validação server-side de campos obrigatórios
- Padrão **Post-Redirect-Get** para evitar reenvio duplicado de formulários

## 📄 Licença

Projeto acadêmico, desenvolvido para fins educacionais.

## 👤 Autor

Jonas Mendes
