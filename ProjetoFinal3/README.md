# Projeto Final - Sistema de Gestão Académica CESAE Digital

Sistema de gestão académica desenvolvido em Java com Spring Boot (backend) e React (frontend), aplicando conceitos de Programação Orientada a Objetos.

## 📋 Descrição

Aplicação web fullstack que permite gerir cursos, turmas, unidades curriculares, alunos e professores do CESAE Digital.

## 🚀 Funcionalidades

- **Gestão de Cursos** - CRUD completo
- **Gestão de Turmas** - Criar, listar, adicionar alunos/UCs
- **Gestão de UCs** - Criar, associar professores
- **Gestão de Professores** - Registar, atribuir UCs (máx. 5)
- **Gestão de Alunos** - Inscrever, registar notas, alterar estado
- **Estatísticas** - Dashboard com relatórios

## 🛠️ Tecnologias

| Componente    | Tecnologia                                 |
| ------------- | ------------------------------------------ |
| Backend       | Java 17+, Spring Boot 3.2, Spring Data JPA |
| Frontend      | React 18, Vite, React Router               |
| Base de Dados | MySQL                                      |
| Build Tool    | Gradle                                     |

## 📁 Estrutura

```
ProjetoFinal3/
├── backend/                    # API REST Spring Boot
│   ├── src/main/java/com/cesae/
│   │   ├── config/             # Configurações (CORS, etc.)
│   │   ├── controller/         # REST Controllers
│   │   ├── entity/             # Entidades JPA
│   │   ├── repository/         # Repositórios Spring Data
│   │   ├── service/            # Lógica de negócio
│   │   └── CesaeApplication.java
│   ├── build.gradle
│   └── gradlew.bat
├── frontend/                   # Interface React
│   ├── src/
│   │   ├── components/         # Componentes reutilizáveis
│   │   ├── pages/              # Páginas da aplicação
│   │   └── App.jsx
│   ├── package.json
│   └── vite.config.js
└── README.md
```

## ⚙️ Pré-requisitos

- Java 17+
- Node.js 18+
- MySQL Server

## 🚀 Execução

### 1. Base de Dados

```sql
-- A base de dados será criada automaticamente
-- Configuração em: backend/src/main/resources/application.properties
-- Default: cesae_db, user: root, password: (vazio)
```

> **⚠️ Nota Importante:** As pastas `node_modules` (frontend) e `build` (backend) foram ignoradas no Git. É necessário seguir os passos abaixo para instalar as dependências e compilar o projeto.

### 2. Backend (Terminal 1)

```powershell
cd backend
.\gradlew.bat bootRun
```

API disponível em: **http://localhost:8080**

### 3. Frontend (Terminal 2)

```powershell
cd frontend
npm install
npm run dev
```

Aplicação disponível em: **http://localhost:5173**

## 🎓 Conceitos POO Aplicados

| Conceito         | Implementação                            |
| ---------------- | ---------------------------------------- |
| Classe Abstracta | `Pessoa`                                 |
| Herança          | `Aluno`, `Professor` extends `Pessoa`    |
| Encapsulamento   | Atributos `private`, getters/setters     |
| Polimorfismo     | Métodos sobrescritos nas subclasses      |
| Enums            | `EstadoAluno`, `TipoCurso`               |
| Validação        | Bean Validation, validações customizadas |

## 📊 Dados de Teste

O sistema carrega automaticamente:

- 4 Professores
- 5 Unidades Curriculares
- 3 Cursos
- 6 Turmas
- 10 Alunos

## 📡 API Endpoints

| Recurso      | Endpoint                               |
| ------------ | -------------------------------------- |
| Alunos       | `GET/POST/PUT/DELETE /api/alunos`      |
| Professores  | `GET/POST/PUT/DELETE /api/professores` |
| Cursos       | `GET/POST/PUT/DELETE /api/cursos`      |
| Turmas       | `GET/POST/PUT/DELETE /api/turmas`      |
| UCs          | `GET/POST/PUT/DELETE /api/ucs`         |
| Estatísticas | `GET /api/estatisticas/*`              |

## 👤 Autor

Mário Amorim - CESAE Digital
