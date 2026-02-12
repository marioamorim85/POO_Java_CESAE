const API_BASE = '/api';

// Helper para fazer requests
async function request(endpoint, options = {}) {
  const config = {
    headers: {
      'Content-Type': 'application/json',
    },
    ...options,
  };

  if (options.body) {
    config.body = JSON.stringify(options.body);
  }

  const response = await fetch(`${API_BASE}${endpoint}`, config);
  
  if (!response.ok) {
    const error = await response.json().catch(() => ({ erro: 'Erro desconhecido' }));
    throw new Error(error.erro || 'Erro na requisição');
  }
  
  if (response.status === 204) {
    return null;
  }
  
  return response.json();
}

// ========== ALUNOS ==========
export const alunoService = {
  listar: () => request('/alunos'),
  buscar: (id) => request(`/alunos/${id}`),
  criar: (aluno) => request('/alunos', { method: 'POST', body: aluno }),
  atualizar: (id, aluno) => request(`/alunos/${id}`, { method: 'PUT', body: aluno }),
  remover: (id) => request(`/alunos/${id}`, { method: 'DELETE' }),
  adicionarNota: (id, nota) => request(`/alunos/${id}/notas`, { method: 'POST', body: { nota } }),
  alterarEstado: (id, estado) => request(`/alunos/${id}/estado`, { method: 'PATCH', body: { estado } }),
  inscreverTurma: (alunoId, turmaId) => request(`/alunos/${alunoId}/turma/${turmaId}`, { method: 'POST' }),
};

// ========== PROFESSORES ==========
export const professorService = {
  listar: () => request('/professores'),
  buscar: (id) => request(`/professores/${id}`),
  criar: (professor) => request('/professores', { method: 'POST', body: professor }),
  atualizar: (id, professor) => request(`/professores/${id}`, { method: 'PUT', body: professor }),
  remover: (id) => request(`/professores/${id}`, { method: 'DELETE' }),
};

// ========== CURSOS ==========
export const cursoService = {
  listar: () => request('/cursos'),
  buscar: (id) => request(`/cursos/${id}`),
  criar: (curso) => request('/cursos', { method: 'POST', body: curso }),
  atualizar: (id, curso) => request(`/cursos/${id}`, { method: 'PUT', body: curso }),
  remover: (id) => request(`/cursos/${id}`, { method: 'DELETE' }),
  adicionarTurma: (cursoId, turmaId) => request(`/cursos/${cursoId}/turmas/${turmaId}`, { method: 'POST' }),
  adicionarUC: (cursoId, ucId) => request(`/cursos/${cursoId}/ucs/${ucId}`, { method: 'POST' }),
};

// ========== TURMAS ==========
export const turmaService = {
  listar: () => request('/turmas'),
  buscar: (id) => request(`/turmas/${id}`),
  criar: (turma) => request('/turmas', { method: 'POST', body: turma }),
  atualizar: (id, turma) => request(`/turmas/${id}`, { method: 'PUT', body: turma }),
  remover: (id) => request(`/turmas/${id}`, { method: 'DELETE' }),
  inscreverAluno: (turmaId, alunoId) => request(`/turmas/${turmaId}/alunos/${alunoId}`, { method: 'POST' }),
  adicionarUC: (turmaId, ucId) => request(`/turmas/${turmaId}/ucs/${ucId}`, { method: 'POST' }),
};

// ========== UCs ==========
export const ucService = {
  listar: () => request('/ucs'),
  buscar: (id) => request(`/ucs/${id}`),
  criar: (uc) => request('/ucs', { method: 'POST', body: uc }),
  atualizar: (id, uc) => request(`/ucs/${id}`, { method: 'PUT', body: uc }),
  remover: (id) => request(`/ucs/${id}`, { method: 'DELETE' }),
  associarProfessor: (ucId, professorId) => request(`/ucs/${ucId}/professor/${professorId}`, { method: 'POST' }),
};

// ========== ESTATÍSTICAS ==========
export const estatisticasService = {
  getTotais: () => request('/estatisticas/totais'),
  getAlunosPorEstado: () => request('/estatisticas/alunos-por-estado'),
  getCursosPorTipo: () => request('/estatisticas/cursos-por-tipo'),
  getMediaGeral: () => request('/estatisticas/media-geral'),
  getOcupacaoTurmas: () => request('/estatisticas/ocupacao-turmas'),
  getProfessoresUCs: () => request('/estatisticas/professores-ucs'),
};
