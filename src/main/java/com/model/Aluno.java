package main.java.com.model;

/**
 * Classe que representa um aluno da disciplina Engenharia de Software.
 */
public class Aluno {

    /** Nota mínima para aprovação. */
    private static final double NOTA_APROVACAO = 7.0;

    /** Nome do aluno. */
    private String nome;

    /** Nota da avaliação parcial 1 (AP1). */
    private double notaAP1;

    /** Nota da avaliação parcial 2 (AP2). */
    private double notaAP2;

    /**
     * Construtor da classe Aluno.
     *
     * @param nomeAluno o nome do aluno
     * @param notaAp1 a nota da avaliação parcial 1 (AP1)
     * @param notaAp2 a nota da avaliação parcial 2 (AP2)
     */
    public Aluno(final String nomeAluno, final double notaAp1,
                 final double notaAp2) {
        this.nome = nomeAluno;
        this.notaAP1 = notaAp1;
        this.notaAP2 = notaAp2;
    }

    /**
     * Calcula a média do aluno utilizando as duas notas.
     *
     * @return a média calculada
     */
    public double calcularMedia() {
        return (notaAP1 + notaAP2) / 2;
    }

    /**
     * Aplica a nota da avaliação substitutiva, substituindo a menor nota.
     *
     * @param notaSubstitutiva a nota da avaliação substitutiva
     */
    public void aplicarSubstitutiva(final double notaSubstitutiva) {
        if (notaAP1 <= notaAP2) {
            notaAP1 = notaSubstitutiva;
        } else {
            notaAP2 = notaSubstitutiva;
        }
    }

    /**
     * Verifica se o aluno está aprovado.
     *
     * @return {@code true} se a média for maior ou igual a 7.0;
     *         caso contrário, {@code false}
     */
    public boolean isAprovado() {
        return calcularMedia() >= NOTA_APROVACAO;
    }

    /**
     * Retorna o nome do aluno.
     *
     * @return o nome do aluno
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna uma representação em String dos dados do aluno.
     *
     * @return informações do aluno formatadas
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Nome: ").append(nome).append("\n")
                .append("Nota AP1: ").append(notaAP1).append("\n")
                .append("Nota AP2: ").append(notaAP2).append("\n")
                .append("Média: ")
                .append(String.format("%.2f", calcularMedia()))
                .append("\n")
                .append("Status: ")
                .append(isAprovado() ? "Aprovado" : "Reprovado");
        return builder.toString();
    }
}
