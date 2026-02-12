package com.cesae.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesae.entity.Aluno;
import com.cesae.entity.EstadoAluno;
import com.cesae.service.AlunoService;

/**
 * Controller REST para gestão de Alunos.
 */
@RestController
@RequestMapping("/api/alunos")

public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<Aluno> listar() {
        return alunoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Aluno buscar(@PathVariable Long id) {
        return alunoService.buscarPorId(id);
    }

    @PostMapping
    public Aluno criar(@RequestBody Aluno aluno) {
        return alunoService.criar(aluno);
    }

    @PutMapping("/{id}")
    public Aluno atualizar(@PathVariable Long id, @RequestBody Aluno aluno) {
        return alunoService.atualizar(id, aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        alunoService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/notas")
    public Aluno adicionarNota(@PathVariable Long id, @RequestBody Map<String, Double> body) {
        Double nota = body.get("nota");
        return alunoService.adicionarNota(id, nota);
    }

    @PatchMapping("/{id}/estado")
    public Aluno alterarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        EstadoAluno estado = EstadoAluno.valueOf(body.get("estado"));
        return alunoService.alterarEstado(id, estado);
    }

    @PostMapping("/{alunoId}/turma/{turmaId}")
    public Aluno inscreverEmTurma(@PathVariable Long alunoId, @PathVariable Long turmaId) {
        return alunoService.inscreverEmTurma(alunoId, turmaId);
    }

    @GetMapping("/turma/{turmaId}")
    public List<Aluno> listarPorTurma(@PathVariable Long turmaId) {
        return alunoService.listarPorTurma(turmaId);
    }

    @GetMapping("/estado/{estado}")
    public List<Aluno> listarPorEstado(@PathVariable EstadoAluno estado) {
        return alunoService.listarPorEstado(estado);
    }

    @GetMapping("/{id}/media")
    public ResponseEntity<Map<String, Double>> calcularMedia(@PathVariable Long id) {
        double media = alunoService.calcularMedia(id);
        return ResponseEntity.ok(Map.of("media", media));
    }
}
