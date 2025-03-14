package main.java.com;

import main.java.com.model.Aluno;
import main.java.com.service.GerenciadorEstudante;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Classe principal que executa o sistema de gerenciamento de
 * estudantes.
 */
public final class App {

    /** Opção para adicionar aluno. */
    private static final int OPCAO_ADICIONAR_ALUNO = 1;

    /** Opção para listar alunos. */
    private static final int OPCAO_LISTAR_ALUNOS = 2;

    /** Opção para sair do sistema. */
    private static final int OPCAO_SAIR = 3;

    /** Valor mínimo permitido para notas. */
    private static final double NOTA_MINIMA = 0.0;

    /** Valor máximo permitido para notas. */
    private static final double NOTA_MAXIMA = 10.0;

    /** Logger para a classe App. */
    private static final Logger LOGGER =
            Logger.getLogger(App.class.getName());

    /**
     * Construtor privado para evitar instância.
     */
    private App() {
        // Construtor privado para classes utilitárias.
    }

    /**
     * Método principal que inicia o sistema.
     *
     * @param args os argumentos de linha de comando
     */
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final GerenciadorEstudante gerenciador = new GerenciadorEstudante();
        boolean executar = true;

        while (executar) {
            exibirMenu();
            final int opcao = lerOpcao(scanner);
            switch (opcao) {
                case OPCAO_ADICIONAR_ALUNO:
                    adicionarAluno(scanner, gerenciador);
                    break;
                case OPCAO_LISTAR_ALUNOS:
                    listarAlunos(gerenciador);
                    break;
                case OPCAO_SAIR:
                    executar = false;
                    LOGGER.info("Encerrando o sistema. Até logo!");
                    break;
                default:
                    LOGGER.info("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    /**
     * Exibe o menu de opções do sistema.
     */
    private static void exibirMenu() {
        LOGGER.info("\n--- Sistema de Gerenciamento de Estudantes ---");
        LOGGER.info("1. Adicionar Aluno");
        LOGGER.info("2. Listar Alunos");
        LOGGER.info("3. Sair");
        LOGGER.info("Escolha uma opção: ");
    }

    /**
     * Lê a opção escolhida pelo usuário.
     *
     * @param scanner o objeto Scanner para leitura de dados
     * @return a opção escolhida
     */
    private static int lerOpcao(final Scanner scanner) {
        int opcao;
        try {
            opcao = Integer.parseInt(scanner.nextLine());
        } catch (final NumberFormatException exception) {
            opcao = -1;
        }
        return opcao;
    }

    /**
     * Adiciona um novo aluno ao sistema, lendo os dados do usuário.
     *
     * @param scanner o objeto Scanner para leitura de dados
     * @param gerenciador o objeto GerenciadorEstudantes para gerenciar alunos
     */
    private static void adicionarAluno(final Scanner scanner,
                                       final GerenciadorEstudante gerenciador) {
        LOGGER.info("Digite o nome do aluno: ");
        final String nome = scanner.nextLine();
        final double notaAP1 = lerNota(scanner, "Digite a nota da AP1: ");
        final double notaAP2 = lerNota(scanner, "Digite a nota da AP2: ");
        final Aluno aluno = new Aluno(nome, notaAP1, notaAP2);

        if (!aluno.isAprovado()) {
            LOGGER.info("Aluno não aprovado na primeira tentativa.");
            final double notaSubstitutiva = lerNota(scanner,
                    "Digite a nota da avaliação substitutiva (AS): ");
            aluno.aplicarSubstitutiva(notaSubstitutiva);
        }
        gerenciador.adicionarAluno(aluno);
        LOGGER.info("Aluno adicionado com sucesso!");
    }

    /**
     * Lê uma nota válida do usuário, garantindo que esteja entre 0 e 10.
     *
     * @param scanner o objeto Scanner para leitura de dados
     * @param mensagem a mensagem a ser exibida para o usuário
     * @return a nota informada
     */
    private static double lerNota(final Scanner scanner,
                                  final String mensagem) {
        double nota = -1;
        while (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            LOGGER.info(mensagem);
            try {
                nota = Double.parseDouble(scanner.nextLine());
                if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
                    LOGGER.info("Nota inválida. Deve ser entre "
                            +
                            NOTA_MINIMA + " e " + NOTA_MAXIMA + ".");
                }
            } catch (final NumberFormatException exception) {
                LOGGER.info("Entrada inválida. Por favor, digite um número.");
            }
        }
        return nota;
    }

    /**
     * Lista os alunos cadastrados e exibe suas informações.
     *
     * @param gerenciador o objeto GerenciadorEstudantes que contém os alunos
     */
    private static void listarAlunos(final GerenciadorEstudante gerenciador) {
        if (gerenciador.getListaAlunos().isEmpty()) {
            LOGGER.info("Nenhum aluno cadastrado.");
        } else {
            LOGGER.info("\n--- Lista de Alunos ---");
            for (final Aluno aluno : gerenciador.getListaAlunos()) {
                LOGGER.info(aluno.toString());
                LOGGER.info("---------------------------------");
            }
        }
    }
}
