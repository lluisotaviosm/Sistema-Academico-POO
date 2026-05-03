package br.edu.instituicao.model;

import br.edu.instituicao.interfaces.Avaliavel;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um Aluno da instituição.
 * Herda de Pessoa e implementa Avaliavel,
 * pois alunos possuem notas e média calculável.
 */
public class Aluno extends Pessoa implements Avaliavel {

    private String matricula;
    private List<Double> notas;

    public Aluno(String nome, String cpf, String email, String matricula) {
        super(nome, cpf, email);
        this.matricula = matricula;
        this.notas = new ArrayList<>();
    }

    // ==================== Getters e Setters ====================

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Double> getNotas() {
        return notas;
    }

    // ==================== Comportamentos ====================

    public void adicionarNota(double nota) {
        if (nota < 0 || nota > 10) {
            System.out.println("  [ERRO] Nota inválida! Deve estar entre 0 e 10.");
            return;
        }
        notas.add(nota);
        System.out.println("  Nota " + nota + " adicionada com sucesso para " + getNome() + ".");
    }

    /**
     * Calcula a média aritmética de todas as notas do aluno.
     * Implementação do contrato da interface Avaliavel.
     * @return média final ou 0.0 se não houver notas
     */
    @Override
    public double getMediaFinal() {
        if (notas.isEmpty()) {
            return 0.0;
        }
        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }
        return soma / notas.size();
    }

    @Override
    public String toString() {
        return super.toString() + " | Matrícula: " + matricula
                + " | Notas: " + notas
                + " | Média: " + String.format("%.2f", getMediaFinal());
    }
}
