# Exceções em Java

## Objetivo
Aprender a tratar erros e situações excecionais em Java utilizando blocos `try-catch`, exceções personalizadas e boas práticas de tratamento de erros.

## Conceitos Abordados
- Bloco `try-catch-finally`
- Exceções verificadas (checked) vs não verificadas (unchecked)
- Lançamento de exceções com `throw`
- Criação de exceções personalizadas
- Propagação de exceções com `throws`
- Múltiplos blocos `catch`

## Enunciado do Exercício

### Sistema de Gestão de Conta Bancária com Exceções

Cria um sistema de gestão de conta bancária que utilize exceções para tratar situações de erro de forma elegante.

#### Parte 1: Exceções Personalizadas
Cria as seguintes exceções personalizadas:

1. **SaldoInsuficienteException** - Lançada quando se tenta levantar mais dinheiro do que o saldo disponível.
2. **ValorInvalidoException** - Lançada quando se introduz um valor negativo ou zero.
3. **ContaInativaException** - Lançada quando se tenta operar numa conta desativada.

#### Parte 2: Classe ContaBancaria
Cria uma classe `ContaBancaria` com:
- **Atributos**: titular, saldo, numeroConta, ativa
- **Métodos**:
  - `depositar(double valor)` - Deposita dinheiro na conta
  - `levantar(double valor)` - Levanta dinheiro da conta
  - `transferir(double valor, ContaBancaria destino)` - Transfere para outra conta
  - `consultarSaldo()` - Mostra o saldo atual
  - `desativarConta()` / `ativarConta()` - Altera o estado da conta

Todos os métodos devem lançar as exceções apropriadas quando necessário.

#### Parte 3: Classe Main
Na classe `Main`, demonstra:
1. Operações bem-sucedidas (depósito, levantamento, transferência)
2. Tratamento de `SaldoInsuficienteException`
3. Tratamento de `ValorInvalidoException`
4. Tratamento de `ContaInativaException`
5. Uso do bloco `finally` para operações de limpeza
6. Múltiplos blocos `catch`

## Estrutura de Ficheiros
```
Excecoes/
└── src/
    ├── ContaBancaria.java
    ├── SaldoInsuficienteException.java
    ├── ValorInvalidoException.java
    ├── ContaInativaException.java
    └── Main.java
```

## Exemplo de Saída Esperada
```
========== SISTEMA BANCÁRIO COM EXCEÇÕES ==========

1. OPERAÇÕES BEM-SUCEDIDAS
Conta criada: PT50-0001 - João Silva - Saldo: 1000.00€
Depósito de 500.00€ realizado com sucesso.
Saldo atual: 1500.00€
Levantamento de 200.00€ realizado com sucesso.
Saldo atual: 1300.00€

2. TRATAMENTO DE SALDO INSUFICIENTE
ERRO: Não é possível levantar 5000.00€. Saldo disponível: 1300.00€

3. TRATAMENTO DE VALOR INVÁLIDO
ERRO: O valor deve ser positivo. Valor introduzido: -100.00€
```

## Dicas
- Exceções personalizadas devem estender `Exception` (verificadas) ou `RuntimeException` (não verificadas)
- Usa mensagens de erro claras e informativas
- O bloco `finally` executa sempre, mesmo que ocorra uma exceção
- Podes capturar múltiplas exceções num único `catch` usando `|`

## Autor
Mário Amorim - CESAE Digital
