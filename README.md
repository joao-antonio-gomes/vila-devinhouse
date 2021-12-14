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