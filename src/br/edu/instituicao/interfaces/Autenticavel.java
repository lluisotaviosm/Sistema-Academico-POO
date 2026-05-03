package br.edu.instituicao.interfaces;

/**
 * Interface que define o contrato de autenticação.
 * Classes que implementam esta interface permitem
 * acesso a áreas restritas do sistema via login com senha.
 */
public interface Autenticavel {
    boolean login(String senha);
}
