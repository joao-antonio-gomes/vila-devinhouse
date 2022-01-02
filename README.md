# Village API
### Modelagem conceitual banco de dados:
- Villagers
  - Id, int, PK
  - First Name, text, 255
  - Surname, text, 255
  - CPF, text, 14
  - Birth Date, date
  - Rent, decimal, 10,2
  - Password, text, 255
  - Role, text, 255
  - Email, text, 255
    

- Financial Report
  - Id, int, PK
  - Date, date
  - Remaining budget, decimal, 10,2
  - Total expenses, decimal, 10,2
  - Total budget, decimal, 10,2
  - Most expensive villager, int, FK to Villagers Id


### Gerando banco de dados:
Na pasta raiz do projeto execute as querys do arquivo `data.sql`

### Autenticando na API:
Acesse o endereço: http://localhost:8080/login
Envie um POST, com raw JSON com os dados abaixo para logar como admin:
```
{
    "email": "admin@email.com",
    "password": "admin"
}
```

### Configurando recuperação de email:
No arquivo `application.properties` defina as seguintes configurações:
- `default.sender` um email válido que será utilizado como remetente.

No arquivo `application-dev.properties` defina as seguintes configurações:
- `spring.mail.host` com o host do e-mail do remetente;
- `spring.mail.username` com o endereço de e-mail remetente;
- `spring.mail.password` com a senha do e-mail remetente. 


### Rotas
- `/residents`: grupo de rotas referente aos residentes.
  - `/list` **GET**: se usado sem request param retorna todos residentes. Parâmetros disponíveis:
    - `month`: mês do nascimento do residente (inteiro).
    - `age`: residentes com idade menor que a indicada (inteiro).
    - `name`: primeiro nome do residente (string).
  - `/list/{id}` **GET**: busca informações de um residente pelo id do banco de dados.
  - `/{id}` **DELETE**: (delete) remove um residente pelo id do banco de dados.
  - `/create` **POST**: cria um residente, enviar o body com os dados abaixo em JSON:
    - `firstName`: primeiro nome do residente (string).
    - `surname`: sobrenome do residente (string).
    - `cpf`: cpf do residente, deve ser válido, com ou sem pontuação (string).
    - `birthDate`: data de nascimento do residente (string yyyy-mm-dd).
    - `rent`: valor do aluguel do residente (decimal, 10,2).
    - `password`: senha do residente (string, senha com no mínimo 8 caracteres, deve ter letra minuscula, letra maiscula, numeral e caracter especial).
    - `email`: email do residente (string, deve ser válido para caso de recuperação de senha).

- `/financialReport`: grupo de rota referente aos relatórios financeiros.
  - `/` **GET**: retorna o relatório financeiro da vila.  

- `/auth`: grupo de rotas referente a autenticação.
  - `/forgot` **POST**: envia um email para recuperação de senha, enviar o body com os dados abaixo em JSON:
    - `email`: email do usuário (string).
  - `/refresh_token` **POST**: atualiza o token de autenticação.


- `/login` **POST**: autentica um usuário, enviar o body com os dados abaixo em JSON:
    - `email`: email do usuário (string).
    - `password`: senha do usuário (string).