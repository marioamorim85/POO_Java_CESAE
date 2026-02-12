package com.cesae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesae.entity.Aluno;
import com.cesae.entity.Turma;
import com.cesae.entity.UnidadeCurricular;
import com.cesae.repository.AlunoRepository;
import com.cesae.repository.TurmaRepository;
import com.cesae.repository.UnidadeCurricularRepository;

/**
 * Serviço para lógica de negócio de Turmas.
 */
@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private UnidadeCurricularRepository ucRepository;

    public List<Turma> listarTodas() {
        return turmaRepository.findAll();
    }

    public Turma buscarPorId(Long id) {
        return turmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada com ID: " + id));
    }

    public Turma criar(Turma turma) {
        return turmaRepository.save(turma);
    }

    public Turma atualizar(Long id, Turma turmaAtualizada) {
        Turma turma = buscarPorId(id);
        turma.setNome(turmaAtualizada.getNome());
        turma.setCapacidadeMaxima(turmaAtualizada.getCapacidadeMaxima());
        return turmaRepository.save(turma);
    }

    public void remover(Long id) {
        Turma turma = buscarPorId(id);
        turmaRepository.delete(turma);
    }

    public Turma inscreverAluno(Long turmaId, Long alunoId) {
        Turma turma = buscarPorId(turmaId);
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        if (!turma.temVagas()) {
            throw new RuntimeException("Turma sem vagas disponíveis");
        }

        aluno.setTurma(turma);
        alunoRepository.save(aluno);
        return turmaRepository.findById(turmaId).get();
    }

    public Turma adicionarUC(Long turmaId, Long ucId) {
        Turma turma = buscarPorId(turmaId);
        UnidadeCurricular uc = ucRepository.findById(ucId)
                .orElseThrow(() -> new RuntimeException("UC não encontrada"));

        if (!turma.getUnidadesCurriculares().contains(uc)) {
            turma.getUnidadesCurriculares().add(uc);
            turmaRepository.save(turma);
        }
        return turma;
    }

    public List<Turma> listarPorCurso(Long cursoId) {
        return turmaRepository.findByCursoId(cursoId);
    }

    public double calcularMediaTurma(Long id) {
        Turma turma = buscarPorId(id);
        return turma.calcularMediaTurma();
    }
}
