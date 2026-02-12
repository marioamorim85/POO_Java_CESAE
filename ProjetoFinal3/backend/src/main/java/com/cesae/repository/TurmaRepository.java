package com.cesae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cesae.entity.Curso;
import com.cesae.entity.Turma;

/**
 * Repository para operações de persistência de Turmas.
 */
@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    List<Turma> findByCurso(Curso curso);

    List<Turma> findByCursoId(Long cursoId);

    List<Turma> findByNomeContainingIgnoreCase(String nome);
}
