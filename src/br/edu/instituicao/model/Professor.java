package br.edu.instituicao.model;

import br.edu.instituicao.interfaces.Autenticavel;

/**
 * Representa um Professor da instituição.
 * Herda de Pessoa e implementa Autenticavel,
 * permitindo acesso ao menu administrativo via login.
 */
public class Professor extends Pessoa implements Autenticavel {

    private String siape;
    private String senha;
    private int horasAtividade;

    public Professor(String nome, String cpf, String email, String siape, String senha) {
        super(nome, cpf, email);
        this.siape = siape;
        this.senha = senha;
        this.horasAtividade = 40; // carga horária padrão
    }

    // ==================== Getters e Setters ====================

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getHorasAtividade() {
        return horasAtividade;
    }

    public void setHorasAtividade(int horasAtividade) {
        this.horasAtividade = horasAtividade;
    }

    // ==================== Comportamentos ====================

    /**
     * Autentica o professor comparando a senha informada.
     * Implementação do contrato da interface Autenticavel.
     */
    @Override
    public boolean login(String senha) {
        return this.senha.equals(senha);
    }

    /**
     * Retorna as horas de atividade. Pode ser sobrescrito por subclasses
     * para aplicar bônus (ex.: Coordenador).
     */
    public int calcularHorasComBonus() {
        return horasAtividade;
    }

    @Override
    public String toString() {
        return super.toString() + " | SIAPE: " + siape
                + " | Horas: " + calcularHorasComBonus() + "h";
    }
}
