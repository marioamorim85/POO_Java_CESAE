package com.cesae.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesae.entity.Aluno;
import com.cesae.entity.EstadoAluno;
import com.cesae.entity.Professor;
import com.cesae.entity.TipoCurso;
import com.cesae.repository.AlunoRepository;
import com.cesae.repository.CursoRepository;
import com.cesae.repository.ProfessorRepository;
import com.cesae.repository.TurmaRepository;
import com.cesae.repository.UnidadeCurricularRepository;

/**
 * Controller REST para estatísticas e relatórios.
 */
@RestController
@RequestMapping("/api/estatisticas")

public class EstatisticasController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private UnidadeCurricularRepository ucRepository;

    @GetMapping("/totais")
    public Map<String, Long> getTotais() {
        Map<String, Long> totais = new HashMap<>();
        totais.put("alunos", alunoRepository.count());
        totais.put("professores", professorRepository.count());
        totais.put("cursos", cursoRepository.count());
        totais.put("turmas", turmaRepository.count());
        totais.put("ucs", ucRepository.count());
        return totais;
    }

    @GetMapping("/alunos-por-estado")
    public Map<String, Long> getAlunosPorEstado() {
        Map<String, Long> resultado = new HashMap<>();
        for (EstadoAluno estado : EstadoAluno.values()) {
            long count = alunoRepository.findByEstado(estado).size();
            resultado.put(estado.name(), count);
        }
        return resultado;
    }

    @GetMapping("/cursos-por-tipo")
    public Map<String, Long> getCursosPorTipo() {
        Map<String, Long> resultado = new HashMap<>();
        for (TipoCurso tipo : TipoCurso.values()) {
            long count = cursoRepository.findByTipo(tipo).size();
            resultado.put(tipo.name(), count);
        }
        return resultado;
    }

    @GetMapping("/media-geral")
    public Map<String, Object> getMediaGeral() {
        List<Aluno> alunos = alunoRepository.findAll();
        double soma = 0;
        int count = 0;

        for (Aluno aluno : alunos) {
            double media = aluno.calcularMedia();
            if (media > 0) {
                soma += media;
                count++;
            }
        }

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("mediaGeral", count > 0 ? soma / count : 0.0);
        resultado.put("alunosComNotas", count);
        resultado.put("totalAlunos", alunos.size());
        return resultado;
    }

    @GetMapping("/ocupacao-turmas")
    public List<Map<String, Object>> getOcupacaoTurmas() {
        return turmaRepository.findAll().stream().map(turma -> {
            Map<String, Object> info = new HashMap<>();
            info.put("id", turma.getId());
            info.put("nome", turma.getNome());
            info.put("alunos", turma.getNumeroAlunos());
            info.put("capacidade", turma.getCapacidadeMaxima());
            info.put("ocupacao", turma.getTaxaOcupacao());
            return info;
        }).toList();
    }

    @GetMapping("/professores-ucs")
    public List<Map<String, Object>> getProfessoresUCs() {
        return professorRepository.findAll().stream().map(prof -> {
            Map<String, Object> info = new HashMap<>();
            info.put("id", prof.getId());
            info.put("nome", prof.getNome());
            info.put("especialidade", prof.getEspecialidade());
            info.put("numeroUCs", prof.getNumeroUCs());
            info.put("maxUCs", Professor.getMaxUcs());
            return info;
        }).toList();
    }
}
