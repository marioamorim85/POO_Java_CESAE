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

import com.cesae.entity.UnidadeCurricular;
import com.cesae.service.UnidadeCurricularService;

/**
 * Controller REST para gestão de Unidades Curriculares.
 */
@RestController
@RequestMapping("/api/ucs")

public class UnidadeCurricularController {

    @Autowired
    private UnidadeCurricularService ucService;

    @GetMapping
    public List<UnidadeCurricular> listar() {
        return ucService.listarTodas();
    }

    @GetMapping("/{id}")
    public UnidadeCurricular buscar(@PathVariable Long id) {
        return ucService.buscarPorId(id);
    }

    @PostMapping
    public UnidadeCurricular criar(@RequestBody UnidadeCurricular uc) {
        return ucService.criar(uc);
    }

    @PutMapping("/{id}")
    public UnidadeCurricular atualizar(@PathVariable Long id, @RequestBody UnidadeCurricular uc) {
        return ucService.atualizar(id, uc);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        ucService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{ucId}/professor/{professorId}")
    public UnidadeCurricular associarProfessor(@PathVariable Long ucId, @PathVariable Long professorId) {
        return ucService.associarProfessor(ucId, professorId);
    }

    @GetMapping("/professor/{professorId}")
    public List<UnidadeCurricular> listarPorProfessor(@PathVariable Long professorId) {
        return ucService.listarPorProfessor(professorId);
    }
}
