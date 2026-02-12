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

import com.cesae.entity.Professor;
import com.cesae.service.ProfessorService;

/**
 * Controller REST para gestão de Professores.
 */
@RestController
@RequestMapping("/api/professores")

public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<Professor> listar() {
        return professorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Professor buscar(@PathVariable Long id) {
        return professorService.buscarPorId(id);
    }

    @PostMapping
    public Professor criar(@RequestBody Professor professor) {
        return professorService.criar(professor);
    }

    @PutMapping("/{id}")
    public Professor atualizar(@PathVariable Long id, @RequestBody Professor professor) {
        return professorService.atualizar(id, professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        professorService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{professorId}/ucs/{ucId}")
    public Professor atribuirUC(@PathVariable Long professorId, @PathVariable Long ucId) {
        return professorService.atribuirUC(professorId, ucId);
    }

    @GetMapping("/especialidade/{especialidade}")
    public List<Professor> listarPorEspecialidade(@PathVariable String especialidade) {
        return professorService.buscarPorEspecialidade(especialidade);
    }
}
