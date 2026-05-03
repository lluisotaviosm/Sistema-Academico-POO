package br.edu.instituicao.main;

import br.edu.instituicao.interfaces.Autenticavel;
import br.edu.instituicao.model.Aluno;
import br.edu.instituicao.model.Coordenador;
import br.edu.instituicao.model.Pessoa;
import br.edu.instituicao.model.Professor;
import br.edu.instituicao.service.RelatorioAcademico;
import br.edu.instituicao.service.Secretaria;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Ponto de entrada do Sistema Acadêmico Orientado a Objetos.
 * Apresenta um menu interativo via console com todas as funcionalidades.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Secretaria secretaria = new Secretaria();
    private static final RelatorioAcademico relatorio = new RelatorioAcademico();

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   SISTEMA ACADÊMICO ORIENTADO A OBJETOS  ║");
        System.out.println("║          Instituição de Ensino            ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // Pré-carrega dados de exemplo para demonstração
        preencherDadosExemplo();

        int opcao = -1;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> cadastrarProfessorOuCoordenador();
                case 3 -> lancarNotas();
                case 4 -> secretaria.listarTodos();
                case 5 -> exibirEstatisticas();
                case 6 -> acessoAdministrativo();
                case 7 -> System.out.println("\n  Encerrando o sistema. Até logo!\n");
                default -> System.out.println("\n  [AVISO] Opção inválida. Tente novamente.\n");
            }
        } while (opcao != 7);

        scanner.close();
    }

    // ==================== MENU ====================

    private static void exibirMenu() {
        System.out.println("\n┌──────────────────────────────────┐");
        System.out.println("│            MENU PRINCIPAL         │");
        System.out.println("├──────────────────────────────────┤");
        System.out.println("│  1. Cadastrar Aluno               │");
        System.out.println("│  2. Cadastrar Professor/Coord.    │");
        System.out.println("│  3. Lançar Notas de Aluno         │");
        System.out.println("│  4. Listar Comunidade Acadêmica   │");
        System.out.println("│  5. Exibir Estatísticas           │");
        System.out.println("│  6. Acesso Administrativo (Login) │");
        System.out.println("│  7. Sair                          │");
        System.out.println("└──────────────────────────────────┘");
    }

    // ==================== OPÇÃO 1: Cadastrar Aluno ====================

    private static void cadastrarAluno() {
        System.out.println("\n--- CADASTRO DE ALUNO ---");
        System.out.print("  Nome      : ");
        String nome = scanner.nextLine().trim();
        System.out.print("  CPF       : ");
        String cpf = scanner.nextLine().trim();
        System.out.print("  Email     : ");
        String email = scanner.nextLine().trim();
        System.out.print("  Matrícula : ");
        String matricula = scanner.nextLine().trim();

        Aluno aluno = new Aluno(nome, cpf, email, matricula);
        secretaria.cadastrarAluno(aluno);
        // Sincroniza com o relatório
        relatorio.adicionarDados(aluno);
    }

    // ==================== OPÇÃO 2: Cadastrar Professor/Coordenador ====================

    private static void cadastrarProfessorOuCoordenador() {
        System.out.println("\n--- CADASTRO DE PROFESSOR/COORDENADOR ---");
        System.out.println("  Tipo de cadastro:");
        System.out.println("  1. Professor");
        System.out.println("  2. Coordenador");
        int tipo = lerInteiro("  Escolha: ");

        System.out.print("  Nome   : ");
        String nome = scanner.nextLine().trim();
        System.out.print("  CPF    : ");
        String cpf = scanner.nextLine().trim();
        System.out.print("  Email  : ");
        String email = scanner.nextLine().trim();
        System.out.print("  SIAPE  : ");
        String siape = scanner.nextLine().trim();
        System.out.print("  Senha  : ");
        String senha = scanner.nextLine().trim();

        if (tipo == 2) {
            System.out.print("  Departamento: ");
            String depto = scanner.nextLine().trim();
            Coordenador coord = new Coordenador(nome, cpf, email, siape, senha, depto);
            secretaria.cadastrarProfessor(coord);
        } else {
            Professor prof = new Professor(nome, cpf, email, siape, senha);
            secretaria.cadastrarProfessor(prof);
        }
    }

    // ==================== OPÇÃO 3: Lançar Notas ====================

    private static void lancarNotas() {
        System.out.println("\n--- LANÇAMENTO DE NOTAS ---");
        System.out.print("  Informe a matrícula ou nome do aluno: ");
        String termo = scanner.nextLine().trim();

        Aluno aluno = secretaria.buscarAluno(termo);

        if (aluno == null) {
            System.out.println("  [AVISO] Aluno não encontrado com o termo: \"" + termo + "\"");
            return;
        }

        System.out.println("  Aluno encontrado: " + aluno.getNome() + " | Matrícula: " + aluno.getMatricula());
        System.out.println("  Notas atuais: " + aluno.getNotas());

        int quantNotas = lerInteiro("  Quantas notas deseja lançar? ");
        for (int i = 1; i <= quantNotas; i++) {
            double nota = lerDouble("  Nota " + i + " (0 a 10): ");
            aluno.adicionarNota(nota);
        }
        System.out.printf("  Média atual de %s: %.2f%n", aluno.getNome(), aluno.getMediaFinal());
    }

    // ==================== OPÇÃO 5: Estatísticas ====================

    private static void exibirEstatisticas() {
        // Sincroniza todos os alunos atuais com o relatório
        RelatorioAcademico relatorioAtual = new RelatorioAcademico();
        List<Aluno> alunos = secretaria.getAlunos();
        for (Aluno a : alunos) {
            relatorioAtual.adicionarDados(a);
        }

        if (alunos.isEmpty()) {
            System.out.println("\n  [AVISO] Não há alunos cadastrados para gerar estatísticas.");
            return;
        }

        relatorioAtual.exibirRelatorioDetalhado();
        relatorioAtual.exibirMediaGeral();
    }

    // ==================== OPÇÃO 6: Acesso Administrativo ====================

    private static void acessoAdministrativo() {
        System.out.println("\n--- ACESSO ADMINISTRATIVO ---");
        System.out.print("  SIAPE do Professor/Coordenador: ");
        String siape = scanner.nextLine().trim();
        System.out.print("  Senha: ");
        String senha = scanner.nextLine().trim();

        // Busca por SIAPE na lista de membros
        Professor encontrado = null;
        for (Pessoa p : secretaria.getMembros()) {
            if (p instanceof Professor prof) {
                if (prof.getSiape().equalsIgnoreCase(siape)) {
                    encontrado = prof;
                    break;
                }
            }
        }

        if (encontrado == null) {
            System.out.println("  [ERRO] SIAPE não encontrado no sistema.");
            return;
        }

        // Polimorfismo: trata como Autenticavel
        Autenticavel autenticavel = encontrado;
        if (autenticavel.login(senha)) {
            System.out.println("\n  ✔ Login realizado com sucesso!");
            System.out.println("  Bem-vindo(a), " + encontrado.getNome()
                    + " (" + encontrado.getClass().getSimpleName() + ")");
            System.out.println("  Horas de Atividade (com bônus): " + encontrado.calcularHorasComBonus() + "h");
        } else {
            System.out.println("  [ERRO] Senha incorreta. Acesso negado.");
        }
    }

    // ==================== DADOS DE EXEMPLO ====================

    private static void preencherDadosExemplo() {
        System.out.println("\n  [INFO] Carregando dados de exemplo...");

        Aluno a1 = new Aluno("Ana Souza", "111.111.111-11", "ana@email.com", "2024001");
        a1.adicionarNota(8.5);
        a1.adicionarNota(7.0);
        a1.adicionarNota(9.0);

        Aluno a2 = new Aluno("Bruno Lima", "222.222.222-22", "bruno@email.com", "2024002");
        a2.adicionarNota(5.0);
        a2.adicionarNota(4.5);

        Aluno a3 = new Aluno("Carla Mendes", "333.333.333-33", "carla@email.com", "2024003");
        a3.adicionarNota(10.0);
        a3.adicionarNota(9.5);

        Professor prof = new Professor("Dr. Ricardo Alves", "444.444.444-44", "ricardo@edu.br", "SIAPE001", "prof123");
        Coordenador coord = new Coordenador("Profa. Maria Dias", "555.555.555-55", "maria@edu.br", "SIAPE002", "coord456", "Ciência da Computação");

        secretaria.cadastrarAluno(a1);
        secretaria.cadastrarAluno(a2);
        secretaria.cadastrarAluno(a3);
        secretaria.cadastrarProfessor(prof);
        secretaria.cadastrarProfessor(coord);

        System.out.println("  [INFO] Dados carregados. Sistema pronto!\n");
    }

    // ==================== UTILITÁRIOS ====================

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = scanner.nextInt();
                scanner.nextLine(); // consome o \n
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("  [ERRO] Entrada inválida. Digite um número inteiro.");
                scanner.nextLine();
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("  [ERRO] Entrada inválida. Digite um número (ex: 7.5).");
                scanner.nextLine();
            }
        }
    }
}
