
# 🎬 Movies API

Este projeto é uma **API REST** feita em **Spring Boot**, que importa uma lista de filmes a partir de um arquivo CSV interno (`Movielist.csv`), armazena em banco de dados H2 em memória e expõe endpoints para consultas

---

## 📂 **Funcionalidades**

✅ Carrega filmes de um arquivo CSV interno no banco H2 na inicialização  
✅ Carrega filmes de um arquivo CSV externo que deve ter colunas separadas por ponto e virgula com a seguinte estrutura: year;title;studios;producers;winner (coluna winner dever ser preenchida com yes)  
✅ Relaciona **Movies**, **Studios** e **Producers**    
✅ Permite CRUD de movies, studios e producers  
✅ Permite filtros por **Studio** ou **Producer**  
✅ Calcula estatísticas de produtor com maior intervalo entre prêmios  
✅ Documentação interativa com Swagger UI  

---

## ⚙️ **Tecnologias**

- Java 24
- Spring Boot 3+
- Spring Data JPA
- Banco de dados H2 em memória
- Springdoc OpenAPI (Swagger UI)
- Maven

---

## 📁 **Configuração**

### 📌 **Banco de dados**

Config padrão `H2`:
```properties
spring.datasource.url=jdbc:h2:mem:movies;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=movies
spring.datasource.password=movies
spring.jpa.hibernate.ddl-auto=validate
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

A tabela é criada via script `schema.sql`.  
Os dados são carregados via `DataLoader`.

---

## 🚀 **Como rodar**

**Clone o projeto**
```bash
git clone git@github.com:phelipewinter/movies.git
cd movies
```

**Compile**
```bash
mvn clean install
```

**Execute**
```bash
mvn spring-boot:run
```

ou gere um `JAR`:
```bash
mvn clean package
```

execute com arquivo interno:
```bash
java -jar target/movies.jar
```

execute com arquivo externo Windows:
```bash
 java -D"data.loader.csv-file"="C:\Users\user\Movielist.csv" -jar target/movies.jar
```
execute com arquivo externo Linux:
```bash
 java -D"data.loader.csv-file"="/home/user/Movielist.csv" -jar target/movies.jar
```

---

### 📌 **Swagger**

- Acesso à documentação em:
  ```
  http://localhost:8080/swagger-ui/index.html
  ```

---

## ✨ **Autor**

Phelipe Winter — `@phelipewinter`
