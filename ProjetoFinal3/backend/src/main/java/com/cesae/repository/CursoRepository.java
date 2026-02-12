package com.cesae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cesae.entity.Curso;
import com.cesae.entity.TipoCurso;

/**
 * Repository para operações de persistência de Cursos.
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByTipo(TipoCurso tipo);

    List<Curso> findByNomeContainingIgnoreCase(String nome);
}
