package com.cesae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesae.entity.Aluno;
import com.cesae.entity.EstadoAluno;
import com.cesae.entity.Turma;
import com.cesae.repository.AlunoRepository;
import com.cesae.repository.TurmaRepository;

/**
 * Serviço para lógica de negócio de Alunos.
 */
@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + id));
    }

    public Aluno criar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno atualizar(Long id, Aluno alunoAtualizado) {
        Aluno aluno = buscarPorId(id);
        aluno.setNome(alunoAtualizado.getNome());
        aluno.setEmail(alunoAtualizado.getEmail());
        aluno.setTelefone(alunoAtualizado.getTelefone());
        aluno.setIdade(alunoAtualizado.getIdade());
        aluno.setEstado(alunoAtualizado.getEstado());
        return alunoRepository.save(aluno);
    }

    public void remover(Long id) {
        Aluno aluno = buscarPorId(id);
        alunoRepository.delete(aluno);
    }

    public Aluno adicionarNota(Long id, double nota) {
        if (nota < 0 || nota > 20) {
            throw new RuntimeException("Nota deve estar entre 0 e 20");
        }
        Aluno aluno = buscarPorId(id);
        aluno.adicionarNota(nota);
        return alunoRepository.save(aluno);
    }

    public Aluno alterarEstado(Long id, EstadoAluno estado) {
        Aluno aluno = buscarPorId(id);
        aluno.setEstado(estado);
        return alunoRepository.save(aluno);
    }

    public Aluno inscreverEmTurma(Long alunoId, Long turmaId) {
        Aluno aluno = buscarPorId(alunoId);
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        if (!turma.temVagas()) {
            throw new RuntimeException("Turma sem vagas disponíveis");
        }

        aluno.setTurma(turma);
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarPorTurma(Long turmaId) {
        return alunoRepository.findByTurmaId(turmaId);
    }

    public List<Aluno> listarPorEstado(EstadoAluno estado) {
        return alunoRepository.findByEstado(estado);
    }

    public double calcularMedia(Long id) {
        Aluno aluno = buscarPorId(id);
        return aluno.calcularMedia();
    }
}
