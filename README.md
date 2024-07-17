## Literalura

Literalura é uma aplicação de linha de comando que permite buscar e explorar livros clássicos disponíveis na API Gutendex. Você pode pesquisar livros por título, idioma e autor, além de ver os livros mais baixados. Os dados dos livros e autores são armazenados em um banco de dados PostgreSQL para facilitar consultas futuras.

### Funcionalidades

* **Buscar livro por título:** Pesquisa livros na API Gutendex e salva os resultados no banco de dados.
* **Buscar livros por idioma:** Lista os idiomas disponíveis no banco de dados e permite buscar livros por um idioma específico.
* **Buscar todos os livros:** Lista todos os livros cadastrados no banco de dados.
* **Buscar autores:** Busca autores por nome ou por ano em que estavam vivos.
* **Buscar autores vivos em um determinado ano:** Busca autores que estavam vivos em um ano específico.
* **Top 10 livros mais baixados:** Exibe os 10 livros mais baixados da API Gutendex.

### Pré-requisitos

* Java JDK (versão 17 ou superior)
* Maven ou Gradle
* PostgreSQL (ou outro banco de dados compatível com JPA)

### Como executar

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/mrpauloricardo/literalura.git
   ```

2. **Configure o banco de dados:**

    * Crie um banco de dados PostgreSQL.
    * Atualize as configurações de conexão com o banco de dados no arquivo `application.properties` (ou `application.yml`).

3. **Execute a aplicação:**

   ```bash
   ./mvnw spring-boot:run  # Se estiver usando Maven
   ./gradlew bootRun       # Se estiver usando Gradle
   ```

4. **Utilize o menu interativo:**

   Siga as instruções no menu para realizar as buscas desejadas.

### Tecnologias utilizadas

* **Spring Boot:** Framework para desenvolvimento de aplicações Java.
* **Spring Data JPA:** Simplifica o acesso a dados em bancos de dados relacionais.
* **PostgreSQL:** Banco de dados relacional de código aberto.
* **Jackson:** Biblioteca para manipulação de JSON.

### Contribuindo

Sinta-se à vontade para contribuir com o projeto! Você pode abrir issues para reportar bugs ou sugerir melhorias, ou enviar pull requests com suas próprias implementações.

---