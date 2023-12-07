# Bem-vindo à Stoom Store API

## 👨‍💻 Tecnologias usadas

- [Java](https://www.java.com/pt_BR/download/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Postgres](https://www.postgresql.org/)
- [Docker](https://www.docker.com/products/docker-desktop)
- [Junit 5](https://junit.org/junit5/)
- [Lombok](https://projectlombok.org/)

## 🤔 Como funciona

Antes de rodar a API certifique-se de possuir instalado em sua máquina a versao 1.8 do Java (obrigatório), Docker (opcional)
e seguir as seguintes orientações:

1. Para criar um conteiner com a imagem do Postgres basta rodar o comando:
```
docker run --name stoom -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
```

2. Não se esqueça de criar o banco de dados dentro do Postgres, você pode usar o DBeaver para isso ou um SGBD de sua preferência
3. Certifique-se que todas as dependências do Maven foram importadas


### ℹ️ Informações adicionais

Na pasta raiz do projeto há uma collection do **Postman** com as requisições para os endpoints da API.


------------
por Daniel Marques [Contate-me pelo Linkedin!](https://www.linkedin.com/in/daniel-marques-aa99b316b/)

