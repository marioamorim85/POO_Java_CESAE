package com.cesae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesae.entity.Professor;
import com.cesae.entity.UnidadeCurricular;
import com.cesae.repository.ProfessorRepository;
import com.cesae.repository.UnidadeCurricularRepository;

/**
 * Serviço para lógica de negócio de Professores.
 */
@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private UnidadeCurricularRepository ucRepository;

    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    public Professor buscarPorId(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com ID: " + id));
    }

    public Professor criar(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor atualizar(Long id, Professor professorAtualizado) {
        Professor professor = buscarPorId(id);
        professor.setNome(professorAtualizado.getNome());
        professor.setEmail(professorAtualizado.getEmail());
        professor.setTelefone(professorAtualizado.getTelefone());
        professor.setIdade(professorAtualizado.getIdade());
        professor.setEspecialidade(professorAtualizado.getEspecialidade());
        professor.setSalario(professorAtualizado.getSalario());
        return professorRepository.save(professor);
    }

    public void remover(Long id) {
        Professor professor = buscarPorId(id);
        professorRepository.delete(professor);
    }

    public Professor atribuirUC(Long professorId, Long ucId) {
        Professor professor = buscarPorId(professorId);
        UnidadeCurricular uc = ucRepository.findById(ucId)
                .orElseThrow(() -> new RuntimeException("UC não encontrada"));

        if (!professor.podeAdicionarUC()) {
            throw new RuntimeException("Professor já atingiu o limite máximo de " +
                    Professor.getMaxUcs() + " UCs");
        }

        uc.setProfessor(professor);
        ucRepository.save(uc);
        return professorRepository.findById(professorId).get();
    }

    public List<Professor> buscarPorEspecialidade(String especialidade) {
        return professorRepository.findByEspecialidadeContainingIgnoreCase(especialidade);
    }
}
