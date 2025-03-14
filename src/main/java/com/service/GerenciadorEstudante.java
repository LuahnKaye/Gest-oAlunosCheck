package main.java.com.service;

import main.java.com.model.Aluno;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar a lista de alunos.
 */
public class GerenciadorEstudante {

    /** Lista de alunos cadastrados. */
    private final List<Aluno> listaAlunos;

    /**
     * Construtor que inicializa a lista de alunos.
     */
    public GerenciadorEstudante() {
        listaAlunos = new ArrayList<>();
    }

    /**
     * Adiciona um aluno à lista.
     *
     * @param aluno o aluno a ser adicionado
     */
    public void adicionarAluno(final Aluno aluno) {
        listaAlunos.add(aluno);
    }

    /**
     * Retorna a lista de alunos cadastrados.
     *
     * @return a lista de alunos
     */
    public List<Aluno> getListaAlunos() {
        return listaAlunos;
    }
}
