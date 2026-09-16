# Educação Continuada Gamificada - Grupo 7

Projeto final de ATDD (BDD + TDD) em Spring Boot.

O ciclo foi conduzido de fora para dentro: primeiro as User Stories, depois os cenários BDD
como critérios de aceitação, depois o TDD (RED, GREEN, BLUE) sobre o pacote `domain` e, por
último, as camadas de aplicação (Entity, Repository, Service, DTO, Controller), o Swagger,
o front-end em Vue.js e a execução via Docker.

## Integrantes

| Integrante | Cenários BDD/TDD implementados |
|---|---|
| Matheus de Alcantara | Cenários 1 e 2 |
| Rafael Silva Fiamengue | Cenários 3 e 4 |
| Henrique Bicudo Paulino | Cenários 5 e 6 |

## Descrição do estudo de caso

**Gamificação para engajamento de educação continuada**

Uma determinada plataforma vende cursos online e EAD no modelo de assinaturas. O aluno paga um
valor mensal e tem acesso a um conjunto de cursos para assinatura básica. A cada curso terminado
e com média acima de 7,0, o aluno tem direito a realização de mais 3 cursos. O aluno que escrever
mais tópicos no fórum e ajudar outros participantes com seus comentários, ganha um curso no final
do mês. Quando o aluno conquistar 12 cursos, seu plano de assinatura passa a ser "Premium" e ele
passa a receber voucher para participar de projetos reais, durante os cursos, e receber 3 moedas,
que podem ser convertidas em conhecimento (novos cursos), acumular ou receber por criptomoeda.

## User Stories

Uma US por integrante, conforme a planilha `Tabela_US_BDD_TDD.xlsx` (aba `pb`).

| # | Redigida por | User Story |
|---|---|---|
| US2 | Matheus de Alcantara | **COMO** Usuário/Aluno **QUERO** visualizar quais cursos tenho acesso no meu plano atual **PARA** acessá-los e realizá-los |
| US3 | Henrique Bicudo Paulino | **COMO** Usuário/Aluno **QUERO** visualizar as notas médias dos meus cursos concluídos **PARA** verificar se tenho direito a mais 3 cursos gratuitos |
| US4 | Rafael Silva Fiamengue | **COMO** Usuário/Aluno **QUERO** visualizar a quantidade de cursos concluídos **PARA** verificar meu progresso da melhoria gratuita para o plano Premium |

> A US1 que aparece na planilha ("COMO Administrador de um Sistema de Gestão de Jogos...") é o
> exemplo-modelo passado em aula e não faz parte das US do grupo.

### US escolhida para implementação

**US3, redigida por Henrique Bicudo Paulino.**

> COMO Usuário/Aluno QUERO visualizar as notas médias dos meus cursos concluídos PARA verificar
> se tenho direito a mais 3 cursos gratuitos.

Motivo da escolha: é a US que concentra a regra central do estudo de caso, ou seja, a nota do
curso concluído define o direito aos créditos de curso gratuito. Os seis cenários BDD do grupo
derivam dessa regra: dois validam o crédito na conclusão, dois garantem que curso em andamento
não gera crédito, e dois cobrem o consumo do crédito no desbloqueio de um novo curso.

## BDD - cenários de aceitação

Todos os cenários estão na planilha `Tabela_US_BDD_TDD.xlsx` e foram implementados como testes
automatizados em `src/test/java/.../domain`.

| Cenário | Escrito por | Given / When / Then | Classe de teste |
|---|---|---|---|
| 1 | Matheus de Alcantara | **Dado** um curso concluído **E** a nota do usuário para aquele curso é >= 7 **Quando** este curso é finalizado **Então** mais 3 créditos de curso gratuito devem ser acrescentados para o usuário | [ConclusaoCursoTest.java](src/test/java/org/example/grupo_7_praticaatdd/domain/ConclusaoCursoTest.java) |
| 2 | Matheus de Alcantara | **Dado** um curso concluído **E** a nota do usuário para aquele curso é < 7 **Quando** este curso é finalizado **Então** nenhum crédito de curso gratuito é adicionado para o usuário | [ConclusaoCursoTest.java](src/test/java/org/example/grupo_7_praticaatdd/domain/ConclusaoCursoTest.java) |
| 3 | Rafael Silva Fiamengue | **Dado** um curso em andamento **Quando** existe apenas 1 curso em andamento **E** não existe nenhum curso finalizado pelo usuário **Então** o usuário deve ter 0 créditos de curso gratuito | [CursoEmAndamentoTest.java](src/test/java/org/example/grupo_7_praticaatdd/domain/CursoEmAndamentoTest.java) |
| 4 | Rafael Silva Fiamengue | **Dado** um curso em andamento **E** a média parcial deste curso é >= 7 **Quando** o curso ainda não foi finalizado **Então** nenhum crédito de curso gratuito é adicionado para o usuário | [CursoEmAndamentoTest.java](src/test/java/org/example/grupo_7_praticaatdd/domain/CursoEmAndamentoTest.java) |
| 5 | Henrique Bicudo Paulino | **Dado** um curso não adquirido **Quando** o usuário solicitar o desbloqueio deste curso **E** o usuário possui >= 1 crédito **Então** este curso é liberado e adicionado à biblioteca do usuário **E** um crédito é consumido | [DesbloqueioCursoTest.java](src/test/java/org/example/grupo_7_praticaatdd/domain/DesbloqueioCursoTest.java) |
| 6 | Henrique Bicudo Paulino | **Dado** um curso não adquirido **Quando** o usuário solicitar o desbloqueio deste curso **E** o usuário possui < 1 crédito **Então** este curso não é liberado **E** um aviso de "créditos insuficientes" é exibido | [DesbloqueioCursoTest.java](src/test/java/org/example/grupo_7_praticaatdd/domain/DesbloqueioCursoTest.java) |

## TDD - evidências do ciclo RED, GREEN e BLUE

O pacote de produção é `src/main/java/.../domain` e o pacote de testes é
`src/test/java/.../domain`, na mesma estrutura usada no exercício da Calculadora.

Cada integrante trabalhou em uma branch própria e os prints de cada fase estão versionados:

| Fase | O que foi feito | Prints |
|---|---|---|
| RED | Testes dos cenários escritos antes da implementação, falhando no IntelliJ e no GitHub Actions | `printsTestes/*/01-red-intellij.png` e `02-red-actions.png` |
| GREEN | Implementação mínima no domínio até os testes passarem, com o relatório de cobertura do JaCoCo | `printsTestes/*/03-green-intellij.png`, `04-green-actions.png` e `05-*jacoco*.png` |
| BLUE | Refatoração sem quebrar teste: extração de constantes (`NOTA_MINIMA_APROVACAO`, `CREDITOS_POR_CONCLUSAO`), centralização da mensagem de crédito insuficiente e extração do método `estaEmAndamento` | `printsTestes/*/06-blue-intellij.png` e `07-blue-jacoco.png` |

Pastas de prints por integrante:

- [printsTestes/cenarios-01-02-alcantara/](printsTestes/cenarios-01-02-alcantara/)
- [printsTestes/cenarios-03-04-rafael/](printsTestes/cenarios-03-04-rafael/)
- [printsTestes/cenarios-05-06-henrique/](printsTestes/cenarios-05-06-henrique/)

O workflow [.github/workflows/testes.yml](.github/workflows/testes.yml) roda os testes e publica
os relatórios do Surefire e do JaCoCo a cada push e pull request.

### Cobertura e complexidade ao final do BLUE

Escopo avaliado: o pacote `domain`, que é o código escrito pelos integrantes no ciclo TDD.

| Classe | Instruções | Branches |
|---|---|---|
| Assinatura | 48/48 | 2/2 |
| Curso | 9/9 | - |
| Matricula | 65/65 | 6/6 |
| StatusMatricula | 15/15 | - |
| Usuario | 65/65 | 2/2 |

Cobertura de 100% no pacote `domain`, sem vermelho e sem amarelo.
Print: [printsEvidencias/07-jacoco-blue-100.png](printsEvidencias/07-jacoco-blue-100.png)

A maior complexidade ciclomática entre os métodos escritos pelo grupo é **3**
(`Matricula.concluidoComAproveitamento`), dentro do limite de 7.

As camadas de aplicação (Controller, Service, Repository, DTO e Entity) ficam fora desse escopo
de cobertura, pois foram construídas depois do ciclo TDD, a partir do projeto de referência.

## Arquitetura

```
src/main/java/org/example/grupo_7_praticaatdd
├── domain          regras de negócio nascidas do TDD (POJOs, sem framework)
├── entity          entidades JPA que guardam o estado no banco
├── repository      Spring Data JPA
├── service         orquestra repositórios e delega as regras ao domain
├── dto             contratos de entrada e saída da API
├── controller      endpoints REST e tratamento de erro
└── config          configuração do OpenAPI/Swagger
```

O `domain` foi mantido puro de propósito: são as classes cobertas pelos testes do TDD. O
`MatriculaService` reconstrói os objetos de domínio para aplicar a regra e depois grava o
resultado na entidade JPA, de modo que a regra validada pelos testes é a mesma que roda na API.

### Endpoints

| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/usuarios` | Criar usuário |
| GET | `/api/usuarios` | Listar usuários com créditos e cursos concluídos |
| POST | `/api/cursos` | Criar curso |
| GET | `/api/cursos` | Listar cursos |
| POST | `/api/matriculas` | Matricular usuário em um curso do plano |
| POST | `/api/matriculas/bonus` | Desbloquear curso bônus consumindo um crédito (cenários 5 e 6) |
| PUT | `/api/matriculas/{id}/concluir` | Concluir matrícula com a nota final (cenários 1 e 2) |
| PUT | `/api/matriculas/{id}/nota-parcial` | Registrar nota parcial sem concluir (cenário 4) |
| GET | `/api/matriculas/usuario/{usuarioId}` | Listar matrículas de um usuário |

## Como executar

Pré-requisitos: JDK 17 ou superior, e Docker para o perfil PostgreSQL.

### Rodando os testes

```bash
./mvnw test
```

O relatório de cobertura é gerado em `target/site/jacoco/index.html`.

### Perfil H2 (banco em memória)

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=h2
```

- Aplicação e front-end: http://localhost:8080
- Swagger: http://localhost:8080/swagger-ui.html
- Console H2: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:gamificacaodb`, usuário `sa`, sem senha)

### Perfil PostgreSQL via Docker

```bash
docker compose up -d --build
```

Sobem três containers: a aplicação, o PostgreSQL e o pgAdmin.

- Aplicação e front-end: http://localhost:8080
- Swagger: http://localhost:8080/swagger-ui.html
- pgAdmin: http://localhost:5050 (`admin@admin.com` / `admin`)
- PostgreSQL: `localhost:5432`, banco `gamificacao_db`, usuário e senha `postgres`

Para derrubar tudo:

```bash
docker compose down -v
```

### Dependências do Spring Boot usadas

Spring Web (MVC), Spring Data JPA, Bean Validation, H2, PostgreSQL e springdoc-openapi (Swagger).
Cobertura de testes com JaCoCo.

## Front-end

Front-end em Vue.js 3 servido pela própria aplicação em `src/main/resources/static`. Permite criar
usuários e cursos, matricular, concluir com nota, registrar nota parcial, desbloquear curso bônus
e acompanhar os créditos do aluno.

## Evidências de execução

| Evidência | Print |
|---|---|
| Front-end em Vue.js consumindo a API | [printsEvidencias/01-front-vue.png](printsEvidencias/01-front-vue.png) |
| Swagger com os endpoints expostos | [printsEvidencias/02-swagger.png](printsEvidencias/02-swagger.png) |
| Banco H2 rodando, com os dados da regra de negócio | [printsEvidencias/03-h2-console.png](printsEvidencias/03-h2-console.png) |
| PostgreSQL rodando em container | [printsEvidencias/04-postgres-docker.png](printsEvidencias/04-postgres-docker.png) |
| pgAdmin administrando o banco via container | [printsEvidencias/05-pgadmin.png](printsEvidencias/05-pgadmin.png) |
| Aplicação rodando via Docker Compose | [printsEvidencias/06-docker-compose.png](printsEvidencias/06-docker-compose.png) |
| Cobertura de 100% no domínio ao final do BLUE | [printsEvidencias/07-jacoco-blue-100.png](printsEvidencias/07-jacoco-blue-100.png) |

## Planilha

A planilha com as User Stories, os cenários BDD e o primeiro passo do TDD está versionada em
[Tabela_US_BDD_TDD.xlsx](Tabela_US_BDD_TDD.xlsx).
