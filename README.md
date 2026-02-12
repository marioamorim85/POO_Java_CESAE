# Exercícios de Programação Orientada a Objetos (POO) em Java

Este repositório contém uma coleção de exercícios e projetos desenvolvidos no âmbito da Unidade de Formação de Curta Duração (UFCD) de **Programação Orientada a Objetos em Java**, integrada no curso de **Front-end Developer**.

---

## 🏛️ Contexto da Formação

- **Entidade:** [CESAE Digital](https://www.cesaedigital.pt/)
- **Localização:** São João da Madeira
- **Curso:** Front-end Developer
- **Duração:** 50 Horas
- **Formador:** Mário Amorim

---

## 🎯 Objetivos

O principal objetivo deste repositório é consolidar os conceitos fundamentais do paradigma de orientação a objetos utilizando a linguagem Java, tais como:

- [x] Conceitos de Classes e Objetos
- [x] Encapsulamento (Getters e Setters)
- [x] Herança e Polimorfismo
- [x] Abstração e Interfaces
- [x] Tratamento de Exceções
- [x] Coleções (ArrayList, HashMap, etc.)
- [x] Leitura e Escrita de Ficheiros

---

## 📂 Estrutura do Repositório

O repositório está organizado por aulas, fichas de trabalho e exercícios temáticos:

### 📚 Aulas

| Pasta    | Descrição                               |
| -------- | --------------------------------------- |
| `Aula1/` | Introdução ao Java e conceitos iniciais |
| `Aula2/` | Arrays e conversão de tipos             |
| `Aula3/` | Estruturas de controlo e menus          |

### 📝 Fichas de Trabalho

| Pasta   | Descrição                                                              |
| ------- | ---------------------------------------------------------------------- |
| `PL01/` | 12 exercícios práticos de POO (Aluno, Gato, Triângulo, Cilindro, etc.) |

### 🧩 Exercícios de POO

| Pasta        | Conceito              | Descrição                                       |
| ------------ | --------------------- | ----------------------------------------------- |
| `ArrayList/` | **Coleções**          | Gestão de carros com ArrayList                  |
| `Heranca/`   | **Herança**           | Sistema de Funcionários (Gerente, Vendedor)     |
| `Abstract/`  | **Classes Abstratas** | Sistema de Veículos (Carro, Bicicleta) - Básico |
| `Abstract2/` | **Classes Abstratas** | Sistema de Funcionários com Salários - Avançado |

### 🌟 Extras (Conteúdo Adicional)

Exercícios extra para aprofundamento de conhecimentos (não abordados em aula), organizados na pasta `Extra/`:

| Pasta               | Conceito                      | Descrição                                           |
| ------------------- | ----------------------------- | --------------------------------------------------- |
| `Extra/Excecoes/`   | **Gestão de Exceções**        | Sistema bancário com exceções personalizadas        |
| `Extra/Ficheiros/`  | **Leitura/Escrita Ficheiros** | Sistema de alunos com persistência em ficheiros CSV |
| `Extra/Interfaces/` | **Interfaces**                | Sistema de pagamentos com múltiplas interfaces      |

### 🎓 Projetos Finais

O repositório contém três versões evolutivas do Projeto Final, demonstrando diferentes abordagens e tecnologias:

| Pasta            | Versão | Descrição                                                                                                                                            | Tecnologias       |
| :--------------- | :----: | :--------------------------------------------------------------------------------------------------------------------------------------------------- | :---------------- |
| `ProjetoFinal/`  | **v1** | **Backend Java Console**<br>Implementação base do sistema de gestão académica, focada na lógica de negócio e POO. Interação via terminal.            | Java (Console)    |
| `ProjetoFinal2/` | **v2** | **Backend Java + Swing GUI**<br>Evolução da v1 com interface gráfica desktop nativa. Utiliza a biblioteca Swing para janelas, tabelas e formulários. | Java, Swing       |
| `ProjetoFinal3/` | **v3** | **Backend Java + Frontend React**<br>Versão Full-stack moderna. Backend em Java (expondo dados/lógica) e Frontend em React interativo.               | Java, React, Vite |

> **Nota:** A pasta `Projeto/` contém o template inicial fornecido aos alunos.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java (JDK 17+)
- **IDE:** VS Code com Extension Pack for Java
- **Sistema Operativo:** Windows / macOS / Linux

---

## 🚀 Como Executar

1. Clone o repositório:

   ```bash
   git clone https://github.com/marioamorim85/POO_Java_CESAE.git
   ```

2. Abra a pasta do exercício desejado no VS Code.

3. Compile e execute:

   ```bash
   # Compilar
   javac -d bin src/*.java

   # Executar
   java -cp bin Main
   ```

   Ou utilize o botão **Run** do VS Code.

---

## 📖 Conceitos Abordados

### Conceitos Fundamentais

- Classes e Objetos
- Atributos e Métodos
- Construtores e Sobrecarga
- Encapsulamento (`private`, getters/setters)
- Variáveis `static`

### Conceitos Intermédios

- Herança (`extends`)
- Polimorfismo e Override
- Classes Abstratas (`abstract`)
- Enumerações (`enum`)

### Conceitos Avançados (Extras)

- Interfaces (`interface`, `implements`)
- Tratamento de Exceções (`try-catch`, exceções personalizadas)
- Leitura e Escrita de Ficheiros (I/O)
- Coleções (`ArrayList`)

---

## 📝 Licença

Este projeto serve propósitos educativos no contexto da formação da CESAE Digital.

---

_Documentação elaborada pelo formador Mário Amorim._
