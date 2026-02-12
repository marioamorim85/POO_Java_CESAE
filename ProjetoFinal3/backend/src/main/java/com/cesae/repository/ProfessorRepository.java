package com.cesae.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cesae.entity.Professor;

/**
 * Repository para operações de persistência de Professores.
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findByNumeroProfessor(int numeroProfessor);

    List<Professor> findByEspecialidadeContainingIgnoreCase(String especialidade);

    List<Professor> findByNomeContainingIgnoreCase(String nome);
}
