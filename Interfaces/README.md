# Interfaces em Java

## Objetivo
Compreender o conceito de interfaces em Java, como defini-las e implementá-las, e perceber as diferenças entre interfaces e classes abstratas.

## Conceitos Abordados
- Definição de interfaces com `interface`
- Implementação de interfaces com `implements`
- Métodos abstratos em interfaces
- Métodos default (Java 8+)
- Constantes em interfaces
- Implementação de múltiplas interfaces
- Polimorfismo através de interfaces

## Enunciado do Exercício

### Sistema de Pagamentos com Múltiplas Interfaces

Cria um sistema de pagamentos que demonstre o uso de interfaces para definir contratos e comportamentos.

#### Parte 1: Definir as Interfaces

1. **Pagavel** - Interface base para qualquer método de pagamento
   - `boolean processarPagamento(double valor)` - Processa um pagamento
   - `double consultarSaldo()` - Consulta o saldo disponível
   - `String getTipoPagamento()` - Retorna o tipo de pagamento

2. **Transferivel** - Interface para métodos que suportam transferências
   - `boolean transferir(double valor, Transferivel destino)` - Transfere para outro destino
   - `String getIdentificador()` - Retorna o identificador único

3. **Recarregavel** - Interface para métodos que podem ser recarregados
   - `void recarregar(double valor)` - Adiciona saldo
   - `double getLimiteRecarga()` - Retorna o limite máximo de recarga

#### Parte 2: Implementar as Classes

1. **CartaoCredito** - Implementa `Pagavel`
   - Atributos: numero, titular, limiteCredito, saldoUtilizado
   - Pagamentos descontam do limite disponível

2. **ContaBancaria** - Implementa `Pagavel` e `Transferivel`
   - Atributos: iban, titular, saldo
   - Suporta pagamentos e transferências

3. **CartaoPrePago** - Implementa `Pagavel`, `Transferivel` e `Recarregavel`
   - Atributos: numero, titular, saldo, limiteRecarga
   - Suporta todas as operações

#### Parte 3: Classe Main
Na classe `Main`, demonstra:
1. Criação de diferentes métodos de pagamento
2. Processamento de pagamentos usando polimorfismo
3. Transferências entre contas
4. Recarga de cartões pré-pagos
5. Uso de ArrayList com tipo de interface

## Estrutura de Ficheiros
```
Interfaces/
└── src/
    ├── Pagavel.java
    ├── Transferivel.java
    ├── Recarregavel.java
    ├── CartaoCredito.java
    ├── ContaBancaria.java
    ├── CartaoPrePago.java
    └── Main.java
```

## Exemplo de Saída Esperada
```
========== SISTEMA DE PAGAMENTOS ==========

1. CRIAR MÉTODOS DE PAGAMENTO
Cartão de Crédito criado: **** **** **** 1234 - Limite: 2000.00€
Conta Bancária criada: PT50 0001 - Saldo: 5000.00€
Cartão Pré-Pago criado: **** 5678 - Saldo: 100.00€

2. PROCESSAR PAGAMENTOS (POLIMORFISMO)
A processar pagamento de 150.00€ com Cartão de Crédito... OK
A processar pagamento de 500.00€ com Conta Bancária... OK
A processar pagamento de 50.00€ com Cartão Pré-Pago... OK

3. TRANSFERÊNCIAS
Transferência de 200.00€ da Conta para o Cartão Pré-Pago... OK
```

## Diferenças: Interface vs Classe Abstrata

| Característica | Interface | Classe Abstrata |
|----------------|-----------|-----------------|
| Herança múltipla | Sim (implements) | Não (extends) |
| Métodos | Abstratos + default | Abstratos + concretos |
| Atributos | Apenas constantes | Qualquer tipo |
| Construtores | Não | Sim |
| Modificadores | public (implícito) | Qualquer |

## Dicas
- Uma classe pode implementar várias interfaces
- Interfaces definem "o que" fazer, não "como" fazer
- Usa interfaces para definir contratos entre classes
- Métodos default permitem adicionar funcionalidade sem quebrar implementações existentes

## Autor
Mário Amorim - CESAE Digital
