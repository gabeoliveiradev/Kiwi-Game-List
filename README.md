# Kiwi Game List

![Project Status](https://img.shields.io/badge/STATUS-EM_DESENVOLVIMENTO-yellow)
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-green)
![React](https://img.shields.io/badge/React-18-blue)
![Postgres](https://img.shields.io/badge/PostgreSQL-16-blue)

> Um sistema web estilo Kanban para organizar, rastrear e classificar sua coleção de jogos.

## Sobre o Projeto

O **Kiwi Game List** é uma aplicação Fullstack desenvolvida para resolver o problema de "backlog de jogos". Ele permite que o usuário pesquise jogos reais (integrado via API externa), adicione-os à sua biblioteca pessoal e gerencie seu progresso através de um quadro interativo.

O objetivo principal deste projeto é demonstrar a construção de uma arquitetura robusta utilizando **Java (Spring Boot)** no backend e **React** no frontend, com foco em boas práticas de REST API, autenticação e gerenciamento de estado.

## Funcionalidades (Planejadas)

- [ ] **Autenticação:** Login e Cadastro de usuários com JWT (JSON Web Token).
- [ ] **Busca de Jogos:** Integração com a API da **RAWG** para buscar dados de jogos reais (Capa, Título, Plataformas).
- [ ] **Gerenciamento de Backlog:** Adicionar jogos a listas personalizadas.
- [ ] **Kanban Board:** Interface Drag-and-Drop para mover jogos entre status:
  - *Planejando jogar*
  - *Jogando*
  - *Zerado*
  - *Abandonado*
- [ ] **Avaliação:** Sistema de notas e comentários pessoais para cada jogo.

## Tecnologias Utilizadas

### Backend
- **Java 17**
- **Spring Boot 3** (Web, Security, Data JPA)
- **PostgreSQL** (Banco de dados relacional)
- **Lombok** (Produtividade)
- **Maven** (Gerenciamento de dependências)

### Frontend
- **React.js** (com Vite)
- **TypeScript / JavaScript**
- **TailwindCSS** (Estilização)
- **Axios** (Requisições HTTP)
- **Hello-Pangea/DnD** (Drag and Drop)

### Serviços Externos
- **RAWG Video Games Database API** (Fonte de dados dos jogos)