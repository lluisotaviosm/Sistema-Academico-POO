package br.edu.instituicao.service;

import br.edu.instituicao.interfaces.Avaliavel;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsável por gerar estatísticas acadêmicas da instituição.
 *
 * POLIMORFISMO EM AÇÃO: Esta classe não conhece "Aluno".
 * Ela conhece apenas objetos que implementam "Avaliavel".
 * Isso garante baixo acoplamento e alta extensibilidade.
 * Se amanhã criarmos "Monitor" ou "Estagiário" que implemente
 * Avaliavel, o RelatorioAcademico funcionará automaticamente.
 */
public class RelatorioAcademico {

    private List<Avaliavel> registros;

    public RelatorioAcademico() {
        this.registros = new ArrayList<>();
    }

    /**
     * Adiciona qualquer objeto Avaliavel ao relatório.
     * @param objeto qualquer instância que implemente Avaliavel
     */
    public void adicionarDados(Avaliavel objeto) {
        registros.add(objeto);
    }

    /**
     * Calcula e exibe a média geral de todos os avaliáveis registrados.
     */
    public void exibirMediaGeral() {
        System.out.println("\n========================================");
        System.out.println("   RELATÓRIO ACADÊMICO - ESTATÍSTICAS");
        System.out.println("========================================");

        if (registros.isEmpty()) {
            System.out.println("  Nenhum dado registrado para exibir.");
            System.out.println("========================================\n");
            return;
        }

        double somaMedias = 0;
        int count = 0;

        for (Avaliavel avaliavel : registros) {
            double media = avaliavel.getMediaFinal();
            somaMedias += media;
            count++;
        }

        double mediaGeral = somaMedias / count;

        System.out.println("  Total de alunos avaliados : " + count);
        System.out.printf("  Média Geral da Instituição: %.2f%n", mediaGeral);
        System.out.println("  Situação da Instituição   : " + classificarMedia(mediaGeral));
        System.out.println("========================================\n");
    }

    /**
     * Exibe o relatório individual de cada avaliável.
     */
    public void exibirRelatorioDetalhado() {
        System.out.println("\n========================================");
        System.out.println("      RELATÓRIO DETALHADO POR ALUNO");
        System.out.println("========================================");

        if (registros.isEmpty()) {
            System.out.println("  Nenhum dado registrado.");
            return;
        }

        for (int i = 0; i < registros.size(); i++) {
            Avaliavel av = registros.get(i);
            System.out.printf("  %d. Média: %.2f | Situação: %s%n",
                    i + 1, av.getMediaFinal(), classificarMedia(av.getMediaFinal()));
        }
        System.out.println("========================================\n");
    }

    private String classificarMedia(double media) {
        if (media >= 7.0) return "✔ APROVADO";
        if (media >= 5.0) return "⚠ RECUPERAÇÃO";
        return "✘ REPROVADO";
    }

    public int getTotalRegistros() {
        return registros.size();
    }
}
