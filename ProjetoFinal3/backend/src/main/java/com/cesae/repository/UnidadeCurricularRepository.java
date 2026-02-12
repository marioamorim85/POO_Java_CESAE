package com.cesae.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cesae.entity.Professor;
import com.cesae.entity.UnidadeCurricular;

/**
 * Repository para operações de persistência de Unidades Curriculares.
 */
@Repository
public interface UnidadeCurricularRepository extends JpaRepository<UnidadeCurricular, Long> {

    Optional<UnidadeCurricular> findByCodigo(String codigo);

    List<UnidadeCurricular> findByProfessor(Professor professor);

    List<UnidadeCurricular> findByProfessorId(Long professorId);

    List<UnidadeCurricular> findByNomeContainingIgnoreCase(String nome);
}
