📚 GoDev Portal de Talentos

🚀 Sobre o projeto

O GoDev Portal de Talentos é uma aplicação desenvolvida em Java + Spring Boot com integração ao PostgreSQL.
O objetivo é oferecer uma plataforma para gestores organizarem turmas e acompanharem os desenvolvedores (GoDevs), centralizando informações como perfis, dossiês e vínculos com turmas específicas.

De forma simples: é um sistema que conecta Turmas e GoDevs, permitindo criar, atualizar, listar e gerenciar essas entidades de forma prática.

🛠️ Tecnologias utilizadas

Java 17

Spring Boot

Spring Data JPA

Hibernate

PostgreSQL

Lombok

JWT (para autenticação e segurança)

📂 Estrutura principal

User → representa os usuários do sistema (login/autenticação).

Turma → organiza grupos de desenvolvedores, com nome, descrição, ano e status ativo/inativo.

GoDev → perfil completo do desenvolvedor, incluindo nome, email, foto e dossiê, vinculado a uma turma.

🔗 Endpoints principais

Turmas
GET /turmas → lista todas as turmas

GET /turmas/{id} → busca turma por ID

POST /turmas → cria nova turma

PUT /turmas/{id} → atualiza turma

DELETE /turmas/{id} → inativa turma

GoDevs
GET /godevs → lista todos os GoDevs

GET /godevs/{id} → busca GoDev por ID

POST /godevs → cria novo GoDev

PUT /godevs/{id} → atualiza GoDev

DELETE /godevs/{id} → remove GoDev

🎯 Objetivo
O projeto foi desenvolvido como parte de um desafio acadêmico/profissional, com foco em:

Estruturação de entidades e relacionamentos (Turma ↔ GoDev).

Implementação de boas práticas REST.

Uso de autenticação JWT para segurança.

Evolução futura: melhorias em validação, feedback de erros e interface.
