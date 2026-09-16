# AC1 ATDD Grupo 7

Projeto AC1 de ATDD com Spring Boot, desenvolvido a partir do estudo de caso
**Gamificação para Engajamento de Educação Continuada**.

| | |
|---|---|
| **Integrantes** | Matheus de Alcantara, Henrique Bicudo, Rafael Silva |
| **Stack** | Java 17, Spring Boot 3.5.5, Spring Data JPA, H2, PostgreSQL, Vue.js 3 |
| **Testes** | JUnit 5 + JaCoCo |
| **Infra** | Docker Desktop, pgAdmin, GitHub Actions |

---

## 1. Descrição do estudo de caso

**GAMIFICAÇÃO PARA ENGAJAMENTO DE EDUCAÇÃO CONTINUADA**

Uma determinada plataforma vende cursos online e EAD no modelo de assinaturas. O aluno paga um
valor mensal e tem acesso a um conjunto de cursos para assinatura básica. A cada curso terminado
e com média acima de 7,0, o aluno tem direito a realização de mais 3 cursos. O aluno que escrever
mais tópicos no fórum e ajudar outros participantes com seus comentários, ganha um curso no final
do mês. Quando o aluno conquistar 12 cursos, seu plano de assinatura passa a ser "Premium" e ele
passa a receber voucher para participar de projetos reais, durante os cursos, e receber 3 moedas,
que podem ser convertidas em conhecimento (novos cursos), acumular ou receber por criptomoeda.

---

## 2. User Stories — uma por integrante

As três US estão versionadas na planilha [Tabela_US_BDD_TDD.xlsx](Tabela_US_BDD_TDD.xlsx), aba `pb`.

| # | Redigida por | User Story |
|---|---|---|
| US1 | **Matheus de Alcantara** | COMO Usuário/Aluno, QUERO visualizar quais cursos tenho acesso no meu plano atual, PARA acessá-los e realizá-los. |
| US2 | **Henrique Bicudo** | COMO Usuário/Aluno, QUERO visualizar as notas médias dos meus cursos concluídos, PARA verificar se tenho direito a mais 3 cursos gratuitos. |
| US3 | **Rafael Silva** | COMO Usuário/Aluno, QUERO visualizar a quantidade de cursos concluídos, PARA verificar meu progresso da melhoria gratuita para o plano Premium. |

### US escolhida para implementação

> **US2 — Henrique**
> *COMO Usuário/Aluno, QUERO visualizar as notas médias dos meus cursos concluídos,
> PARA verificar se tenho direito a mais 3 cursos gratuitos.*

Foi a escolhida porque é a que concentra a regra central do estudo de caso: **curso concluído com
nota maior ou igual a 7 dá direito a mais 3 cursos**. Os seis cenários BDD partem dessa regra,
quatro tratam de ganhar e guardar créditos e dois tratam de gastá-los ao desbloquear um curso.

---

## 3. Cenários BDD (fizemos dois por integrante)

| Cenário | Escrito por | BDD |
|---|---|---|
| **1** | **Matheus de Alcantara** | **Dado** um curso concluído<br>**E** a nota do usuário para aquele curso é >= 7<br>**Quando** este curso é finalizado<br>**Então** mais 3 créditos de curso gratuito devem ser acrescentados para o usuário |
| **2** | **Matheus de Alcantara** | **Dado** um curso concluído<br>**E** a nota do usuário para aquele curso é < 7<br>**Quando** este curso é finalizado<br>**Então** nenhum crédito de curso gratuito é adicionado para o usuário |
| **3** | **Rafael** | **Dado** um curso em andamento<br>**Quando** existe apenas 1 curso em andamento<br>**E** não existe nenhum curso finalizado pelo usuário<br>**Então** o usuário deve ter 0 créditos de curso gratuito |
| **4** | **Rafael** | **Dado** um curso em andamento<br>**E** a média parcial deste curso é >= 7<br>**Quando** o curso ainda não foi finalizado<br>**Então** nenhum crédito de curso gratuito é adicionado para o usuário |
| **5** | **Henrique** | **Dado** um curso não adquirido<br>**E** o usuário possui >= 1 crédito de curso gratuito<br>**Quando** o usuário solicitar o desbloqueio deste curso<br>**Então** este curso é liberado e adicionado à biblioteca de cursos do usuário<br>**E** um crédito de curso gratuito é consumido |
| **6** | **Henrique** | **Dado** um curso não adquirido<br>**E** o usuário possui < 1 crédito de curso gratuito<br>**Quando** o usuário solicitar o desbloqueio deste curso<br>**Então** este curso não é liberado nem adicionado à biblioteca de cursos do usuário<br>**E** um aviso de "créditos insuficientes" é exibido ao usuário |

Cada cenário virou um teste JUnit na pasta de domínio:

| Cenários | Classe de teste |
|---|---|
| 1 e 2 | [ConclusaoCursoTest.java](src/test/java/org/example/grupo_7_praticaatdd/domain/ConclusaoCursoTest.java) |
| 3 e 4 | [CursoEmAndamentoTest.java](src/test/java/org/example/grupo_7_praticaatdd/domain/CursoEmAndamentoTest.java) |
| 5 e 6 | [DesbloqueioCursoTest.java](src/test/java/org/example/grupo_7_praticaatdd/domain/DesbloqueioCursoTest.java) |

---

## 4. Ciclo TDD (RED -> GREEN -> BLUE)

O ciclo foi feito cenário a cenário, e cada passo tem commit próprio no histórico do Git.
Os prints estão em [printsTestes/](printsTestes/), separados por integrante.

### RED - testes escritos primeiro, falhando

| Integrante | Cenários | Evidência |
|---|---|---|
| Matheus de Alcantara | 1 e 2 | [IntelliJ](printsTestes/cenarios-01-02-alcantara/01-red-intellij.png) · [GitHub Actions](printsTestes/cenarios-01-02-alcantara/02-red-actions.png) |
| Rafael | 3 e 4 | [IntelliJ](printsTestes/cenarios-03-04-rafael/01-red-intellij.png.png) · [GitHub Actions](printsTestes/cenarios-03-04-rafael/02-red-actions.png.png) |
| Henrique | 5 e 6 | [IntelliJ](printsTestes/cenarios-05-06-henrique/01-red-intellij.png) · [GitHub Actions](printsTestes/cenarios-05-06-henrique/02-red-actions.png) |

### GREEN - implementação mínima até os testes passarem

| Integrante | Cenários | Evidência |
|---|---|---|
| Matheus de Alcantara | 1 e 2 | [IntelliJ](printsTestes/cenarios-01-02-alcantara/03-green-intellij.png) · [GitHub Actions](printsTestes/cenarios-01-02-alcantara/04-green-actions.png) |
| Rafael | 3 e 4 | [IntelliJ](printsTestes/cenarios-03-04-rafael/03-green-intellij.png.png) · [GitHub Actions](printsTestes/cenarios-03-04-rafael/04-green-actions.png.png) · [JaCoCo](printsTestes/cenarios-03-04-rafael/05-green-jacoco.png.png) |
| Henrique | 5 e 6 | [IntelliJ](printsTestes/cenarios-05-06-henrique/03-green-intellij.png) · [GitHub Actions](printsTestes/cenarios-05-06-henrique/04-green-actions.png) · [JaCoCo](printsTestes/cenarios-05-06-henrique/05-jacoco-green.png) |

Nesta fase a cobertura ainda tinha amarelo e vermelho, como mostram os prints do JaCoCo.

### BLUE - refatoração com os testes passando e cobertura em 100%

Refatorações feitas nesta fase:

- extração das constantes `NOTA_MINIMA_APROVACAO` e `CREDITOS_POR_CONCLUSAO`;
- centralização da mensagem `MENSAGEM_CREDITOS_INSUFICIENTES`;
- extração do método `estaEmAndamento()` na `Matricula`;
- encapsulamento de nome, e-mail, senha, título e descrição em **Value Objects**.

| Integrante | Cenários | Evidência |
|---|---|---|
| Matheus de Alcantara | 1 e 2 | [IntelliJ](printsTestes/cenarios-01-02-alcantara/05-blue-intellij.png) · [GitHub Actions](printsTestes/cenarios-01-02-alcantara/06-blue-actions.png) |
| Rafael Silva | 3 e 4 | [IntelliJ](printsTestes/cenarios-03-04-rafael/06-blue-intellij.png.png) · [JaCoCo](printsTestes/cenarios-03-04-rafael/07-blue-jacoco.png.png) |
| Henrique Bicudo | 5 e 6 | [IntelliJ](printsTestes/cenarios-05-06-henrique/06-blue-intellij.png) |

**Resultado final: 43 testes, 100% de cobertura, sem vermelho nem amarelo.**

![Cobertura JaCoCo em 100%](evidencias/jacoco-100-porcento.png)

![Cobertura do pacote domain](evidencias/jacoco-domain.png)

Para conferir localmente:

```bash
./mvnw clean test
```

> **Escopo da medição:** o TDD foi aplicado sobre o domínio, então o relatório mede o domínio.
> As camadas de infraestrutura (`controller`, `service`, `repository`, `dto`, `config` e a classe
> `Application`) estão declaradas em `<excludes>` no JaCoCo, porque são testadas manualmente pelo
> Swagger e pelo front-end, e não por testes unitários.

---

## 5. Arquitetura em camadas

| Camada | Pacote | Classes |
|---|---|---|
| Domain | [domain/](src/main/java/org/example/grupo_7_praticaatdd/domain/) | `Usuario`, `Assinatura`, `Curso`, `Matricula`, `PlanoAssinatura`, `StatusMatricula` |
| Value Objects | [domain/VO/](src/main/java/org/example/grupo_7_praticaatdd/domain/VO/) | `NomeUsuario`, `EmailUsuario`, `SenhaCriptografada`, `TituloCurso`, `DescricaoCurso` |
| Repository | [repository/](src/main/java/org/example/grupo_7_praticaatdd/repository/) | `UsuarioRepository`, `CursoRepository`, `MatriculaRepository` |
| DTO | [dto/](src/main/java/org/example/grupo_7_praticaatdd/dto/) | Request e Response de usuário, curso e matrícula |
| Service | [service/](src/main/java/org/example/grupo_7_praticaatdd/service/) | `UsuarioService`, `CursoService`, `MatriculaService` |
| Controller | [controller/](src/main/java/org/example/grupo_7_praticaatdd/controller/) | `UsuarioController`, `CursoController`, `MatriculaController`, `ApiExceptionHandler` |
| Config | [config/](src/main/java/org/example/grupo_7_praticaatdd/config/) | `OpenApiConfig`, `CriptografiaConfig` |

As entidades JPA ficam no próprio pacote `domain`, com `@Entity` e `@Embeddable`, seguindo o
modelo do projeto de referência passado em aula.

---

## 6. Endpoints e Swagger

Com a aplicação no ar: **http://localhost:8080/swagger-ui.html**

![Swagger UI](evidencias/swagger-ui.png)

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/api/usuarios` | Lista os usuários |
| `GET` | `/api/usuarios/{id}` | Busca usuário e o estado da assinatura |
| `POST` | `/api/usuarios` | Cria usuário com assinatura básica |
| `GET` | `/api/cursos` | Lista os cursos |
| `POST` | `/api/cursos` | Cria um curso |
| `POST` | `/api/matriculas` | Matricula em um curso (`bonus: true` consome um crédito) |
| `PUT` | `/api/matriculas/{id}/concluir` | Conclui a matrícula com a nota final |
| `GET` | `/api/matriculas/usuario/{usuarioId}` | Lista as matrículas de um usuário |
