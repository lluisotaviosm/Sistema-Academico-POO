package br.edu.instituicao.service;

import br.edu.instituicao.model.Aluno;
import br.edu.instituicao.model.Pessoa;
import br.edu.instituicao.model.Professor;

import java.util.ArrayList;
import java.util.List;

/**
 * Cérebro do sistema — gerencia todos os cadastros da instituição.
 * Utiliza ArrayList<Pessoa> para armazenar qualquer membro
 * da comunidade acadêmica de forma polimórfica.
 */
public class Secretaria {

    private List<Pessoa> membros;

    public Secretaria() {
        this.membros = new ArrayList<>();
    }

    // ==================== Cadastros ====================

    /**
     * Cadastra um novo aluno no sistema.
     */
    public void cadastrarAluno(Aluno aluno) {
        membros.add(aluno);
        System.out.println("\n  ✔ Aluno cadastrado: " + aluno.getNome()
                + " | Matrícula: " + aluno.getMatricula());
    }

    /**
     * Cadastra um novo professor (ou coordenador) no sistema.
     * Recebe Professor pois Coordenador é subclasse — polimorfismo.
     */
    public void cadastrarProfessor(Professor professor) {
        membros.add(professor);
        System.out.println("\n  ✔ " + professor.getClass().getSimpleName()
                + " cadastrado(a): " + professor.getNome()
                + " | SIAPE: " + professor.getSiape());
    }

    // ==================== Listagem ====================

    /**
     * Lista todos os membros cadastrados.
     */
    public void listarTodos() {
        System.out.println("\n========================================");
        System.out.println("      COMUNIDADE ACADÊMICA CADASTRADA");
        System.out.println("========================================");

        if (membros.isEmpty()) {
            System.out.println("  Nenhum membro cadastrado ainda.");
        } else {
            for (int i = 0; i < membros.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + membros.get(i));
            }
        }
        System.out.println("  Total: " + membros.size() + " membro(s).");
        System.out.println("========================================\n");
    }

    // ==================== Busca ====================

    /**
     * Localiza um aluno por matrícula ou nome (busca parcial, case-insensitive).
     * @param termo matrícula ou parte do nome
     * @return o Aluno encontrado, ou null se não encontrado
     */
    public Aluno buscarAluno(String termo) {
        for (Pessoa p : membros) {
            if (p instanceof Aluno) {
                Aluno a = (Aluno) p;
                if (a.getMatricula().equalsIgnoreCase(termo)
                        || a.getNome().toLowerCase().contains(termo.toLowerCase())) {
                    return a;
                }
            }
        }
        return null;
    }

    /**
     * Retorna todos os alunos cadastrados (para o relatório).
     */
    public List<Aluno> getAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        for (Pessoa p : membros) {
            if (p instanceof Aluno) {
                alunos.add((Aluno) p);
            }
        }
        return alunos;
    }

    public List<Pessoa> getMembros() {
        return membros;
    }
}
