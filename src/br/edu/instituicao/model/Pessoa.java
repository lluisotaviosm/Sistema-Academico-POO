package br.edu.instituicao.model;

/**
 * Classe abstrata que representa qualquer indivíduo da instituição.
 * Não pode ser instanciada diretamente — garante a abstração do sistema.
 * Todo membro da comunidade acadêmica (aluno, professor, etc.)
 * obrigatoriamente herda desta classe.
 */
public abstract class Pessoa {

    private String nome;
    private String cpf;
    private String email;

    public Pessoa(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    // ==================== Getters e Setters ====================

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // ==================== Método sobrescrito ====================

    @Override
    public String toString() {
        return "[" + getClass().getSimpleName() + "] " + nome + " | CPF: " + cpf + " | Email: " + email;
    }
}
