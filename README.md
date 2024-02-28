## Requisitos
Este projeto tem como objetivo implementar um sistema simplificado de transferencia, focando no fluxo de transferência de dinheiro entre dois tipos de usuários: comuns (COMMON) e lojistas(MERCHANT).

### Cadastro de Usuários
- Nome Completo, CPF, e-mail e Senha são obrigatórios para ambos os tipos de usuários.
- CPF/CNPJ e e-mails devem ser únicos no sistema.
- Permite apenas um cadastro por CPF ou endereço de e-mail.

### Transferência de Dinheiro
- Usuários podem enviar dinheiro (efetuar transferência) para lojistas e entre usuários.
- Lojistas só recebem transferências, não enviam dinheiro para ninguém.
- Validação de saldo do usuário antes da transferência.

### Serviço Autorizador Externo
- Antes de finalizar a transferência, é necessário consultar um serviço autorizador externo.
- Use o [mock de serviço autorizador](https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc) para simular a autorização.
- A transferência deve ser tratada como uma transação, revertendo em caso de inconsistência.

### Notificação de Pagamento
- No recebimento de pagamento, o usuário ou lojista deve receber notificação.
- Use o [mock de serviço de notificação](https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6) para simular o envio.
- Considere a possibilidade de o serviço de notificação estar indisponível ou instável.


## Tecnologias Utilizadas

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Swagger](https://swagger.io/)
- [H2 Database](https://www.h2database.com/html/main.html)

## Instruções de Uso com Spring Boot

1. Clone o repositório.
2. Instale as dependências utilizando as ferramentas de build do Spring Boot.
3. Execute o servidor utilizando o comando `./mvnw spring-boot:run` ou `./gradlew bootRun`.
4. Acesse a API através do endpoint apropriado.

## Exemplos de Uso

### Cadastro de Usuários

- Usuários comuns
```http
POST /users
{
  "firstName": "Luiz",
  "lastName": "Sobrenome",
  "document": "012",
  "balance": 1000000,
  "email": "Luiz@Sobrenome.com",
  "password": "123",
  "userType": "common"
}
```
- Usuários lojista

```http
POST /users
{
"firstName": "Lojinha",
"lastName": "Do José",
"document": "011",
"balance": 1000000,
"email": "lojinha@dojose.com",
"password": "123",
"userType": "merchant"
}
```

### Transferência de Dinheiro

```http
POST /transations
{
  "value": 100,
  "senderId": 1,
  "receiverId": 2
}
```