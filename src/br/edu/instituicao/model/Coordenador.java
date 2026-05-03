package br.edu.instituicao.model;

/**
 * Representa um Coordenador da instituição.
 * Herda de Professor, aproveitando todos os seus atributos e métodos,
 * e adiciona o comportamento de bônus nas horas de atividade (Desafio Bônus).
 *
 * Demonstra: Herança (extends Professor) e Polimorfismo (sobrescreve calcularHorasComBonus).
 */
public class Coordenador extends Professor {

    private static final int BONUS_HORAS = 20; // horas extras administrativas
    private String departamento;

    public Coordenador(String nome, String cpf, String email, String siape, String senha, String departamento) {
        super(nome, cpf, email, siape, senha);
        this.departamento = departamento;
    }

    // ==================== Getters e Setters ====================

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    // ==================== Comportamentos ====================

    /**
     * BÔNUS (Desafio Opcional): O Coordenador recebe 20h a mais
     * do que um Professor comum pela função administrativa.
     * Polimorfismo: mesma assinatura, comportamento diferente.
     */
    @Override
    public int calcularHorasComBonus() {
        return super.getHorasAtividade() + BONUS_HORAS;
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Departamento: " + departamento
                + " | (Bônus: +" + BONUS_HORAS + "h incluídas)";
    }
}
