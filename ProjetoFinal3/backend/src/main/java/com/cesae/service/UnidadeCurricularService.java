package com.cesae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesae.entity.Professor;
import com.cesae.entity.UnidadeCurricular;
import com.cesae.repository.ProfessorRepository;
import com.cesae.repository.UnidadeCurricularRepository;

/**
 * Serviço para lógica de negócio de Unidades Curriculares.
 */
@Service
public class UnidadeCurricularService {

    @Autowired
    private UnidadeCurricularRepository ucRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public List<UnidadeCurricular> listarTodas() {
        return ucRepository.findAll();
    }

    public UnidadeCurricular buscarPorId(Long id) {
        return ucRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UC não encontrada com ID: " + id));
    }

    public UnidadeCurricular criar(UnidadeCurricular uc) {
        if (ucRepository.findByCodigo(uc.getCodigo()).isPresent()) {
            throw new RuntimeException("Já existe uma UC com o código: " + uc.getCodigo());
        }
        return ucRepository.save(uc);
    }

    public UnidadeCurricular atualizar(Long id, UnidadeCurricular ucAtualizada) {
        UnidadeCurricular uc = buscarPorId(id);
        uc.setNome(ucAtualizada.getNome());
        uc.setCodigo(ucAtualizada.getCodigo());
        uc.setCargaHoraria(ucAtualizada.getCargaHoraria());
        return ucRepository.save(uc);
    }

    public void remover(Long id) {
        UnidadeCurricular uc = buscarPorId(id);
        ucRepository.delete(uc);
    }

    public UnidadeCurricular associarProfessor(Long ucId, Long professorId) {
        UnidadeCurricular uc = buscarPorId(ucId);
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        if (!professor.podeAdicionarUC()) {
            throw new RuntimeException("Professor já atingiu o limite máximo de UCs");
        }

        uc.setProfessor(professor);
        return ucRepository.save(uc);
    }

    public List<UnidadeCurricular> listarPorProfessor(Long professorId) {
        return ucRepository.findByProfessorId(professorId);
    }
}
