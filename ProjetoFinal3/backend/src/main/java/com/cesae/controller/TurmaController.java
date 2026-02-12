package com.cesae.controller;

import java.util.List;
import java.util.Map;

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

import com.cesae.entity.Turma;
import com.cesae.service.TurmaService;

/**
 * Controller REST para gestão de Turmas.
 */
@RestController
@RequestMapping("/api/turmas")

public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public List<Turma> listar() {
        return turmaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Turma buscar(@PathVariable Long id) {
        return turmaService.buscarPorId(id);
    }

    @PostMapping
    public Turma criar(@RequestBody Turma turma) {
        return turmaService.criar(turma);
    }

    @PutMapping("/{id}")
    public Turma atualizar(@PathVariable Long id, @RequestBody Turma turma) {
        return turmaService.atualizar(id, turma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        turmaService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{turmaId}/alunos/{alunoId}")
    public Turma inscreverAluno(@PathVariable Long turmaId, @PathVariable Long alunoId) {
        return turmaService.inscreverAluno(turmaId, alunoId);
    }

    @PostMapping("/{turmaId}/ucs/{ucId}")
    public Turma adicionarUC(@PathVariable Long turmaId, @PathVariable Long ucId) {
        return turmaService.adicionarUC(turmaId, ucId);
    }

    @GetMapping("/curso/{cursoId}")
    public List<Turma> listarPorCurso(@PathVariable Long cursoId) {
        return turmaService.listarPorCurso(cursoId);
    }

    @GetMapping("/{id}/media")
    public ResponseEntity<Map<String, Double>> calcularMedia(@PathVariable Long id) {
        double media = turmaService.calcularMediaTurma(id);
        return ResponseEntity.ok(Map.of("media", media));
    }
}
