# Twitter Simplicado
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://camo.githubusercontent.com/544022edf8369d944e68802fc043b0268484709e334d23db2882590aeae296cb/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f706f7374677265732d2532333331363139322e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d706f737467726573716c266c6f676f436f6c6f723d7768697465)
![JWT](https://camo.githubusercontent.com/6eff46a364eba690cb91a9f40084d97f96bf95699f3cb7722125dc1dc324fde1/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a57542d626c61636b3f7374796c653d666f722d7468652d6261646765266c6f676f3d4a534f4e253230776562253230746f6b656e73)

Esse projeto é um Twitter Simplicado no qual os usuários podem se cadastrar, realizar login, fazer publicações e visualizar o feed de publicações.
Além disso, ele conta com um sistema de autenticação e autorização com token JWT e um controle de acesso granular através de ROLES (perfis de acesso).

Esse projeto foi construído utilizando Java, Java Spring, PostgresSQL como banco de dados, Spring Security and JWT para controle de autenticação e autorização.

## Tecnologis utilizadas

- **Java 17**: Linguagem de programação principal.
- **Spring Boot**: Framework para a criação de aplicações Java com configuração mínima.
- **Spring Security**: Para autenticação e autorização via JWT tokens.
- **Postgres**: Banco de dados em memória para ambiente de desenvolvimento.

## Instalação
1. Clone o repositório:
```bash
git clone https://github.com/mclara831/twitter-simplificado.git
```
2. Instale as dependências do Maven.

## Utilização
1. Inicie a aplicação.
2. As API's podem ser acessadas em http://localhost:8080

## API's endpoints

```bash
POST [PUBLIC] - /auth/register - registra um novo usuário.

POST [PUBLIC] - /auth/login - realiza login do usuário.

GET [PRIVATE] - /tweets/feed - recupera todas as publicações feita por diferentes usuário.

GET [PRIVATE] - /tweets/newTweet - registra um novo tweet.

DELETE [PRIVATE] - /tweets/delete/{tweetId} - deleta um tweet por meio do seu id.

 GET [ADMIN] - /users - recupera a lista de todos os usuários cadastrados.
```

## Postman
Os testes da API foram realizados utilizando o Postman.

Baixe e instale o Postman a partir do [site oficial](https://www.postman.com/).
