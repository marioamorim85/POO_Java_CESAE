package com.cesae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesae.entity.Curso;
import com.cesae.entity.TipoCurso;
import com.cesae.entity.Turma;
import com.cesae.entity.UnidadeCurricular;
import com.cesae.repository.CursoRepository;
import com.cesae.repository.TurmaRepository;
import com.cesae.repository.UnidadeCurricularRepository;

/**
 * Serviço para lógica de negócio de Cursos.
 */
@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private UnidadeCurricularRepository ucRepository;

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + id));
    }

    public Curso criar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso atualizar(Long id, Curso cursoAtualizado) {
        Curso curso = buscarPorId(id);
        curso.setNome(cursoAtualizado.getNome());
        curso.setTipo(cursoAtualizado.getTipo());
        curso.setDuracaoMeses(cursoAtualizado.getDuracaoMeses());
        return cursoRepository.save(curso);
    }

    public void remover(Long id) {
        Curso curso = buscarPorId(id);
        cursoRepository.delete(curso);
    }

    public Curso adicionarTurma(Long cursoId, Long turmaId) {
        Curso curso = buscarPorId(cursoId);
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        turma.setCurso(curso);
        turmaRepository.save(turma);
        return cursoRepository.findById(cursoId).get();
    }

    public Curso adicionarUC(Long cursoId, Long ucId) {
        Curso curso = buscarPorId(cursoId);
        UnidadeCurricular uc = ucRepository.findById(ucId)
                .orElseThrow(() -> new RuntimeException("UC não encontrada"));

        if (!curso.getUnidadesCurriculares().contains(uc)) {
            curso.getUnidadesCurriculares().add(uc);
            cursoRepository.save(curso);
        }
        return curso;
    }

    public List<Curso> listarPorTipo(TipoCurso tipo) {
        return cursoRepository.findByTipo(tipo);
    }
}
