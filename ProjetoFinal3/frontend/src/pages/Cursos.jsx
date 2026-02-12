import { BookOpen, Pencil, Plus, Trash2 } from 'lucide-react';
import { useEffect, useState } from 'react';
import Modal from '../components/Modal';
import { cursoService, ucService } from '../services/api';

const tipoOptions = ['FORMACAO', 'POS_GRADUACAO', 'WORKSHOP'];

export default function Cursos() {
  const [cursos, setCursos] = useState([]);
  const [ucs, setUCs] = useState([]);
  const [loading, setLoading] = useState(true);
  const [modalOpen, setModalOpen] = useState(false);
  const [detailsModalOpen, setDetailsModalOpen] = useState(false);
  const [editingCurso, setEditingCurso] = useState(null);
  const [selectedCurso, setSelectedCurso] = useState(null);
  const [formData, setFormData] = useState({ nome: '', tipo: 'FORMACAO', duracaoMeses: '', ucsSelecionadas: [] });

  useEffect(() => { loadData(); }, []);

  async function loadData() {
    try {
      const [cursosData, ucsData] = await Promise.all([cursoService.listar(), ucService.listar()]);
      setCursos(cursosData);
      setUCs(ucsData);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  }

  function openModal(curso = null) {
    if (curso) {
      setEditingCurso(curso);
      setFormData({
        nome: curso.nome,
        tipo: curso.tipo,
        duracaoMeses: curso.duracaoMeses,
        ucsSelecionadas: curso.unidadesCurriculares?.map(uc => uc.id) || []
      });
    } else {
      setEditingCurso(null);
      setFormData({ nome: '', tipo: 'FORMACAO', duracaoMeses: '', ucsSelecionadas: [] });
    }
    setModalOpen(true);
  }

  function openDetailsModal(curso, type) {
    setSelectedCurso({ ...curso, viewType: type });
    setDetailsModalOpen(true);
  }

  function toggleUC(ucId) {
    const id = parseInt(ucId);
    setFormData(prev => ({
      ...prev,
      ucsSelecionadas: prev.ucsSelecionadas.includes(id)
        ? prev.ucsSelecionadas.filter(u => u !== id)
        : [...prev.ucsSelecionadas, id]
    }));
  }

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      const data = { nome: formData.nome, tipo: formData.tipo, duracaoMeses: parseInt(formData.duracaoMeses) };
      let cursoId;
      if (editingCurso) {
        await cursoService.atualizar(editingCurso.id, data);
        cursoId = editingCurso.id;
      } else {
        const novoCurso = await cursoService.criar(data);
        cursoId = novoCurso.id;
      }
      const ucsAtuais = editingCurso?.unidadesCurriculares?.map(uc => uc.id) || [];
      const novasUCs = formData.ucsSelecionadas.filter(id => !ucsAtuais.includes(id));
      for (const ucId of novasUCs) {
        await cursoService.adicionarUC(cursoId, ucId);
      }
      setModalOpen(false);
      loadData();
    } catch (err) {
      alert(err.message);
    }
  }

  async function handleAddUC(ucId) {
    try {
      await cursoService.adicionarUC(selectedCurso.id, ucId);
      loadData();
      const updatedCurso = await cursoService.buscar(selectedCurso.id);
      setSelectedCurso({ ...updatedCurso, viewType: 'ucs' });
    } catch (err) {
      alert(err.message);
    }
  }

  async function handleDelete(id) {
    if (confirm('Tem certeza que deseja remover este curso?')) {
      try {
        await cursoService.remover(id);
        loadData();
      } catch (err) {
        alert(err.message);
      }
    }
  }

  if (loading) return <div className="loading"><div className="spinner"></div></div>;

  const cursoUcIds = selectedCurso?.unidadesCurriculares?.map(uc => uc.id) || [];
  const ucsDisponiveis = ucs.filter(uc => !cursoUcIds.includes(uc.id));

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">Cursos</h1>
        <button className="btn btn-primary" onClick={() => openModal()}>
          <Plus size={15} strokeWidth={2} /> Novo Curso
        </button>
      </div>

      <div className="card">
        <table className="data-table">
          <thead>
            <tr>
              <th>Nome</th>
              <th>Tipo</th>
              <th>Duracao</th>
              <th>Turmas</th>
              <th>UCs</th>
              <th>Acoes</th>
            </tr>
          </thead>
          <tbody>
            {cursos.map((curso) => (
              <tr key={curso.id}>
                <td>{curso.nome}</td>
                <td><span className="badge badge-info">{curso.tipo}</span></td>
                <td>{curso.duracaoMeses} meses</td>
                <td>
                  <span
                    className="badge badge-success badge-clickable"
                    onClick={() => openDetailsModal(curso, 'turmas')}
                  >
                    {curso.turmas?.length || 0} Turmas
                  </span>
                </td>
                <td>
                  <span
                    className="badge badge-blue badge-clickable"
                    onClick={() => openDetailsModal(curso, 'ucs')}
                  >
                    {curso.unidadesCurriculares?.length || 0} UCs
                  </span>
                </td>
                <td>
                  <div className="actions">
                    <button className="btn btn-secondary btn-sm" onClick={() => openModal(curso)} title="Editar">
                      <Pencil size={14} strokeWidth={1.75} />
                    </button>
                    <button className="btn btn-danger btn-sm" onClick={() => handleDelete(curso.id)} title="Remover">
                      <Trash2 size={14} strokeWidth={1.75} />
                    </button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        {cursos.length === 0 && (
          <div className="empty-state">
            <div className="empty-state-icon"><BookOpen size={24} strokeWidth={1.5} /></div>
            <p>Nenhum curso encontrado</p>
          </div>
        )}
      </div>

      {/* Modal Criar/Editar Curso */}
      <Modal isOpen={modalOpen} onClose={() => setModalOpen(false)} title={editingCurso ? 'Editar Curso' : 'Novo Curso'}>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label className="form-label">Nome</label>
            <input className="form-input" value={formData.nome} onChange={(e) => setFormData({...formData, nome: e.target.value})} required />
          </div>
          <div className="form-group">
            <label className="form-label">Tipo</label>
            <select className="form-select" value={formData.tipo} onChange={(e) => setFormData({...formData, tipo: e.target.value})}>
              {tipoOptions.map(t => <option key={t} value={t}>{t}</option>)}
            </select>
          </div>
          <div className="form-group">
            <label className="form-label">Duracao (meses)</label>
            <input type="number" className="form-input" value={formData.duracaoMeses} onChange={(e) => setFormData({...formData, duracaoMeses: e.target.value})} required />
          </div>
          <div className="form-group">
            <label className="form-label">Unidades Curriculares</label>
            <div className="list-container" style={{ maxHeight: '150px' }}>
              {ucs.length === 0 ? (
                <p style={{ color: 'var(--text-muted)', fontSize: '0.875rem', padding: '0.5rem' }}>Nenhuma UC disponivel</p>
              ) : (
                ucs.map(uc => (
                  <label key={uc.id} style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', padding: '0.375rem 0.5rem', cursor: 'pointer', fontSize: '0.875rem' }}>
                    <input
                      type="checkbox"
                      checked={formData.ucsSelecionadas.includes(uc.id)}
                      onChange={() => toggleUC(uc.id)}
                      style={{ cursor: 'pointer' }}
                    />
                    {uc.codigo} - {uc.nome}
                  </label>
                ))
              )}
            </div>
          </div>
          <div className="modal-footer">
            <button type="button" className="btn btn-secondary" onClick={() => setModalOpen(false)}>Cancelar</button>
            <button type="submit" className="btn btn-primary">{editingCurso ? 'Guardar' : 'Criar'}</button>
          </div>
        </form>
      </Modal>

      {/* Modal Ver Detalhes (UCs ou Turmas) */}
      <Modal isOpen={detailsModalOpen} onClose={() => setDetailsModalOpen(false)} title={`${selectedCurso?.viewType === 'turmas' ? 'Turmas' : 'UCs'} do Curso ${selectedCurso?.nome || ''}`}>
        {selectedCurso?.viewType === 'turmas' ? (
          <div>
            <h4 className="section-title">Turmas Associadas</h4>
            {selectedCurso?.turmas?.length > 0 ? (
              <div style={{ display: 'flex', flexDirection: 'column', gap: '0.375rem' }}>
                {selectedCurso.turmas.map(turma => (
                  <span key={turma.id} className="badge badge-success" style={{ padding: '0.375rem 0.625rem' }}>
                    {turma.nome} - {turma.alunos?.length || 0}/{turma.capacidadeMaxima} alunos
                  </span>
                ))}
              </div>
            ) : (
              <p style={{ color: 'var(--text-muted)', fontSize: '0.875rem' }}>Nenhuma turma associada a este curso</p>
            )}
          </div>
        ) : (
          <div>
            <div style={{ marginBottom: '1rem' }}>
              <h4 className="section-title">UCs do Plano Curricular</h4>
              {selectedCurso?.unidadesCurriculares?.length > 0 ? (
                <div style={{ display: 'flex', flexWrap: 'wrap', gap: '0.375rem' }}>
                  {selectedCurso.unidadesCurriculares.map(uc => (
                    <span key={uc.id} className="badge badge-success" style={{ padding: '0.375rem 0.625rem' }}>
                      {uc.codigo} - {uc.nome}
                    </span>
                  ))}
                </div>
              ) : (
                <p style={{ color: 'var(--text-muted)', fontSize: '0.875rem' }}>Nenhuma UC no plano curricular</p>
              )}
            </div>

            <div>
              <h4 className="section-title">Adicionar UC</h4>
              {ucsDisponiveis.length > 0 ? (
                <div className="list-container">
                  {ucsDisponiveis.map(uc => (
                    <div key={uc.id} className="list-item">
                      <span>{uc.codigo} - {uc.nome}</span>
                      <button className="btn btn-primary btn-sm" onClick={() => handleAddUC(uc.id)}>
                        Adicionar
                      </button>
                    </div>
                  ))}
                </div>
              ) : (
                <p style={{ color: 'var(--text-muted)', fontSize: '0.875rem' }}>Todas as UCs ja estao associadas</p>
              )}
            </div>
          </div>
        )}

        <div className="modal-footer">
          <button className="btn btn-secondary" onClick={() => setDetailsModalOpen(false)}>Fechar</button>
        </div>
      </Modal>
    </div>
  );
}
