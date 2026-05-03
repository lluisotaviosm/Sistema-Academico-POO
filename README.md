# Sistema Acadêmico Orientado a Objetos

Projeto desenvolvido em Java aplicando os quatro pilares da Programação Orientada a Objetos: **Abstração, Herança, Encapsulamento e Polimorfismo**, além do uso de **Interfaces** e **Estruturas Dinâmicas**.

---

## Como Rodar o Projeto

### Pré-requisitos
- Java JDK 17 ou superior instalado
- IntelliJ IDEA (recomendado) ou qualquer IDE Java

### Passos no IntelliJ IDEA

1. Abra o IntelliJ IDEA e clique em **File → Open**
2. Selecione a pasta `Sistema Acadêmico Orientado a Objeto`
3. Vá em **File → Project Structure → Modules → Sources**
4. Marque a pasta `src` como **Sources Root** (ela ficará azul)
5. Clique em **OK**
6. Navegue até `src/br/edu/instituicao/main/Main.java`
7. Clique com o botão direito → **Run 'Main.main()'**

### Compilar e Rodar pelo Terminal

```bash
# Entrar na pasta do projeto
cd "Sistema Acadêmico Orientado a Objeto"

# Compilar todos os arquivos
javac -d out -sourcepath src src/br/edu/instituicao/main/Main.java

# Executar
java -cp out br.edu.instituicao.main.Main
```

---

## Por que a classe `Pessoa` foi definida como abstrata?

A classe `Pessoa` foi definida como **abstrata** porque ela representa um conceito genérico e incompleto no domínio do sistema. Na realidade, nenhum indivíduo na instituição é simplesmente uma "Pessoa" — ele é necessariamente um Aluno, um Professor ou um Coordenador. Permitir a instanciação direta de `Pessoa` seria um erro conceitual: criaria objetos sem papel definido no sistema, sem matrícula, sem SIAPE, sem nenhum comportamento específico. Ao torná-la abstrata, garantimos que ela sirva apenas como **contrato e base comum**, forçando qualquer tipo concreto a herdar dela e adicionar seus próprios atributos e comportamentos. Isso é Abstração aplicada corretamente: modelar apenas o que é essencial no nível mais genérico, delegando os detalhes às subclasses.

---

## Exemplo de Saída do Console

```
╔══════════════════════════════════════════╗
║   SISTEMA ACADÊMICO ORIENTADO A OBJETOS  ║
║          Instituição de Ensino            ║
╚══════════════════════════════════════════╝

  [INFO] Carregando dados de exemplo...
  Nota 8.5 adicionada com sucesso para Ana Souza.
  Nota 7.0 adicionada com sucesso para Ana Souza.
  Nota 9.0 adicionada com sucesso para Ana Souza.
  Nota 5.0 adicionada com sucesso para Bruno Lima.
  Nota 4.5 adicionada com sucesso para Bruno Lima.
  Nota 10.0 adicionada com sucesso para Carla Mendes.
  Nota 9.5 adicionada com sucesso para Carla Mendes.

  ✔ Aluno cadastrado: Ana Souza | Matrícula: 2024001
  ✔ Aluno cadastrado: Bruno Lima | Matrícula: 2024002
  ✔ Aluno cadastrado: Carla Mendes | Matrícula: 2024003
  ✔ Professor cadastrado(a): Dr. Ricardo Alves | SIAPE: SIAPE001
  ✔ Coordenador cadastrado(a): Profa. Maria Dias | SIAPE: SIAPE002
  [INFO] Dados carregados. Sistema pronto!

┌──────────────────────────────────┐
│            MENU PRINCIPAL         │
├──────────────────────────────────┤
│  1. Cadastrar Aluno               │
│  2. Cadastrar Professor/Coord.    │
│  3. Lançar Notas de Aluno         │
│  4. Listar Comunidade Acadêmica   │
│  5. Exibir Estatísticas           │
│  6. Acesso Administrativo (Login) │
│  7. Sair                          │
└──────────────────────────────────┘
Escolha uma opção: 4

========================================
      COMUNIDADE ACADÊMICA CADASTRADA
========================================
  1. [Aluno] Ana Souza | CPF: 111.111.111-11 | Email: ana@email.com | Matrícula: 2024001 | Notas: [8.5, 7.0, 9.0] | Média: 8,17
  2. [Aluno] Bruno Lima | CPF: 222.222.222-22 | Email: bruno@email.com | Matrícula: 2024002 | Notas: [5.0, 4.5] | Média: 4,75
  3. [Aluno] Carla Mendes | CPF: 333.333.333-33 | Email: carla@email.com | Matrícula: 2024003 | Notas: [10.0, 9.5] | Média: 9,75
  4. [Professor] Dr. Ricardo Alves | CPF: 444.444.444-44 | Email: ricardo@edu.br | SIAPE: SIAPE001 | Horas: 40h
  5. [Coordenador] Profa. Maria Dias | CPF: 555.555.555-55 | Email: maria@edu.br | SIAPE: SIAPE002 | Horas: 60h | Departamento: Ciência da Computação | (Bônus: +20h incluídas)
  Total: 5 membro(s).
========================================

Escolha uma opção: 5

========================================
      RELATÓRIO DETALHADO POR ALUNO
========================================
  1. Média: 8,17 | Situação: ✔ APROVADO
  2. Média: 4,75 | Situação: ✘ REPROVADO
  3. Média: 9,75 | Situação: ✔ APROVADO
========================================

========================================
   RELATÓRIO ACADÊMICO - ESTATÍSTICAS
========================================
  Total de alunos avaliados : 3
  Média Geral da Instituição: 7,56
  Situação da Instituição   : ✔ APROVADO
========================================

Escolha uma opção: 6

--- ACESSO ADMINISTRATIVO ---
  SIAPE do Professor/Coordenador: SIAPE002
  Senha: coord456

  ✔ Login realizado com sucesso!
  Bem-vindo(a), Profa. Maria Dias (Coordenador)
  Horas de Atividade (com bônus): 60h

Escolha uma opção: 7

  Encerrando o sistema. Até logo!
```

---

## Estrutura do Projeto

```
src/
└── br/edu/instituicao/
    ├── interfaces/
    │   ├── Avaliavel.java       → contrato getMediaFinal()
    │   └── Autenticavel.java    → contrato login()
    ├── model/
    │   ├── Pessoa.java          → classe abstrata base
    │   ├── Aluno.java           → herda Pessoa, implementa Avaliavel
    │   ├── Professor.java       → herda Pessoa, implementa Autenticavel
    │   └── Coordenador.java     → herda Professor (bônus de horas)
    ├── service/
    │   ├── Secretaria.java      → gestão com ArrayList<Pessoa>
    │   └── RelatorioAcademico.java → opera sobre Avaliavel (polimorfismo)
    └── main/
        └── Main.java            → menu interativo e ponto de entrada
```

---

## Conceitos POO Demonstrados

| Conceito | Onde aparece |
|---|---|
| **Abstração** | `Pessoa` é abstrata — `new Pessoa()` é impossível |
| **Herança** | `Coordenador extends Professor extends Pessoa` |
| **Encapsulamento** | Todos os atributos `private` com getters/setters |
| **Polimorfismo** | `RelatorioAcademico` opera sobre `Avaliavel`; `calcularHorasComBonus()` sobrescrito |
| **Interfaces** | `Avaliavel` e `Autenticavel` como contratos de comportamento |
| **Estruturas Dinâmicas** | `ArrayList<Pessoa>` na `Secretaria` |
| **Bônus** | `Coordenador` adiciona +20h via override de `calcularHorasComBonus()` |
