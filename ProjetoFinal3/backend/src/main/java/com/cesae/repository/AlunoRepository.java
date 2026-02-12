package com.cesae.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cesae.entity.Aluno;
import com.cesae.entity.EstadoAluno;
import com.cesae.entity.Turma;

/**
 * Repository para operações de persistência de Alunos.
 */
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByNumeroAluno(int numeroAluno);

    List<Aluno> findByTurma(Turma turma);

    List<Aluno> findByTurmaId(Long turmaId);

    List<Aluno> findByEstado(EstadoAluno estado);

    List<Aluno> findByNomeContainingIgnoreCase(String nome);
}
