package com.cesae.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesae.entity.Curso;
import com.cesae.entity.TipoCurso;
import com.cesae.service.CursoService;

/**
 * Controller REST para gestão de Cursos.
 */
@RestController
@RequestMapping("/api/cursos")

public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> listar() {
        return cursoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Curso buscar(@PathVariable Long id) {
        return cursoService.buscarPorId(id);
    }

    @PostMapping
    public Curso criar(@RequestBody Curso curso) {
        return cursoService.criar(curso);
    }

    @PutMapping("/{id}")
    public Curso atualizar(@PathVariable Long id, @RequestBody Curso curso) {
        return cursoService.atualizar(id, curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        cursoService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cursoId}/turmas/{turmaId}")
    public Curso adicionarTurma(@PathVariable Long cursoId, @PathVariable Long turmaId) {
        return cursoService.adicionarTurma(cursoId, turmaId);
    }

    @PostMapping("/{cursoId}/ucs/{ucId}")
    public Curso adicionarUC(@PathVariable Long cursoId, @PathVariable Long ucId) {
        return cursoService.adicionarUC(cursoId, ucId);
    }

    @GetMapping("/tipo/{tipo}")
    public List<Curso> listarPorTipo(@PathVariable TipoCurso tipo) {
        return cursoService.listarPorTipo(tipo);
    }
}
