# Village API
### Modelagem conceitual banco de dados:
```
• Villagers
    • Id, int, PK
    • First Name, text, 255
    • Surname, text, 255
    • CPF, text, 14
    • Birth Date, date
    • Rent, decimal, 10,2
    • Password, text, 255
    • Role, text, 255
    • Email, text, 255
    
• Financial Report
    • Id, int, PK
    • Date, date
    • Remaining budget, decimal, 10,2
    • Total expenses, decimal, 10,2
    • Total budget, decimal, 10,2
    • Most expensive villager, int, FK to Villagers Id
``` 

### Gerando banco de dados:
```
Na pasta raiz do projeto execute as querys do arquivo data.sql
```

### Autenticando na API:
```
Acesse o endereço: http://localhost:8080/login
Envie um POST, com raw JSON com os dados abaixo para logar como admin:

{
    "email": "admin@email.com",
    "password": "admin"
}
```