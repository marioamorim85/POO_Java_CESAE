import { Pencil, Plus, Trash2, UsersRound } from 'lucide-react';
import { useEffect, useState } from 'react';
import Modal from '../components/Modal';
import { cursoService, turmaService, ucService } from '../services/api';

export default function Turmas() {
  const [turmas, setTurmas] = useState([]);
  const [cursos, setCursos] = useState([]);
  const [ucs, setUCs] = useState([]);
  const [loading, setLoading] = useState(true);
  const [modalOpen, setModalOpen] = useState(false);
  const [ucsModalOpen, setUcsModalOpen] = useState(false);
  const [editingTurma, setEditingTurma] = useState(null);
  const [selectedTurma, setSelectedTurma] = useState(null);
  const [formData, setFormData] = useState({ nome: '', capacidadeMaxima: '', cursoId: '' });

  useEffect(() => { loadData(); }, []);

  async function loadData() {
    try {
      const [turmasData, cursosData, ucsData] = await Promise.all([
        turmaService.listar(),
        cursoService.listar(),
        ucService.listar()
      ]);
      setTurmas(turmasData);
      setCursos(cursosData);
      setUCs(ucsData);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  }

  function openModal(turma = null) {
    if (turma) {
      setEditingTurma(turma);
      setFormData({ nome: turma.nome, capacidadeMaxima: turma.capacidadeMaxima, cursoId: turma.curso?.id || '' });
    } else {
      setEditingTurma(null);
      setFormData({ nome: '', capacidadeMaxima: '20', cursoId: '' });
    }
    setModalOpen(true);
  }

  function openUcsModal(turma) {
    setSelectedTurma(turma);
    setUcsModalOpen(true);
  }

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      const data = { nome: formData.nome, capacidadeMaxima: parseInt(formData.capacidadeMaxima) };
      let turmaId;
      if (editingTurma) {
        await turmaService.atualizar(editingTurma.id, data);
        turmaId = editingTurma.id;
      } else {
        const novaTurma = await turmaService.criar(data);
        turmaId = novaTurma.id;
      }
      if (formData.cursoId) {
        await cursoService.adicionarTurma(formData.cursoId, turmaId);
      }
      setModalOpen(false);
      loadData();
    } catch (err) {
      alert(err.message);
    }
  }

  async function handleAddUC(ucId) {
    try {
      await turmaService.adicionarUC(selectedTurma.id, ucId);
      loadData();
      const updatedTurma = await turmaService.buscar(selectedTurma.id);
      setSelectedTurma(updatedTurma);
    } catch (err) {
      alert(err.message);
    }
  }

  async function handleDelete(id) {
    if (confirm('Tem certeza que deseja remover esta turma?')) {
      try {
        await turmaService.remover(id);
        loadData();
      } catch (err) {
        alert(err.message);
      }
    }
  }

  if (loading) return <div className="loading"><div className="spinner"></div></div>;

  const turmaUcIds = selectedTurma?.unidadesCurriculares?.map(uc => uc.id) || [];
  const ucsDisponiveis = ucs.filter(uc => !turmaUcIds.includes(uc.id));

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">Turmas</h1>
        <button className="btn btn-primary" onClick={() => openModal()}>
          <Plus size={15} strokeWidth={2} /> Nova Turma
        </button>
      </div>

      <div className="card">
        <table className="data-table">
          <thead>
            <tr>
              <th>Nome</th>
              <th>Curso</th>
              <th>Alunos</th>
              <th>Ocupacao</th>
              <th>UCs</th>
              <th>Acoes</th>
            </tr>
          </thead>
          <tbody>
            {turmas.map((turma) => {
              const ocupacao = (turma.alunos?.length || 0) / turma.capacidadeMaxima * 100;
              return (
                <tr key={turma.id}>
                  <td>{turma.nome}</td>
                  <td>{turma.curso?.nome || '-'}</td>
                  <td>{turma.alunos?.length || 0}/{turma.capacidadeMaxima}</td>
                  <td>
                    <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                      <div style={{ flex: 1 }} className="progress-track">
                        <div
                          className="progress-bar"
                          style={{
                            width: `${ocupacao}%`,
                            background: ocupacao > 90 ? 'var(--danger)' : 'var(--accent)',
                          }}
                        ></div>
                      </div>
                      <span style={{ fontSize: '0.75rem', color: 'var(--text-muted)', minWidth: '2rem' }}>{ocupacao.toFixed(0)}%</span>
                    </div>
                  </td>
                  <td>
                    <span
                      className="badge badge-blue badge-clickable"
                      onClick={() => openUcsModal(turma)}
                    >
                      {turma.unidadesCurriculares?.length || 0} UCs
                    </span>
                  </td>
                  <td>
                    <div className="actions">
                      <button className="btn btn-secondary btn-sm" onClick={() => openModal(turma)} title="Editar">
                        <Pencil size={14} strokeWidth={1.75} />
                      </button>
                      <button className="btn btn-danger btn-sm" onClick={() => handleDelete(turma.id)} title="Remover">
                        <Trash2 size={14} strokeWidth={1.75} />
                      </button>
                    </div>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
        {turmas.length === 0 && (
          <div className="empty-state">
            <div className="empty-state-icon"><UsersRound size={24} strokeWidth={1.5} /></div>
            <p>Nenhuma turma encontrada</p>
          </div>
        )}
      </div>

      {/* Modal Criar/Editar Turma */}
      <Modal isOpen={modalOpen} onClose={() => setModalOpen(false)} title={editingTurma ? 'Editar Turma' : 'Nova Turma'}>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label className="form-label">Nome</label>
            <input className="form-input" value={formData.nome} onChange={(e) => setFormData({...formData, nome: e.target.value})} placeholder="Ex: TP-2025-A" required />
          </div>
          <div className="form-group">
            <label className="form-label">Capacidade Maxima</label>
            <input type="number" className="form-input" value={formData.capacidadeMaxima} onChange={(e) => setFormData({...formData, capacidadeMaxima: e.target.value})} required />
          </div>
          <div className="form-group">
            <label className="form-label">Curso</label>
            <select className="form-select" value={formData.cursoId} onChange={(e) => setFormData({...formData, cursoId: e.target.value})}>
              <option value="">Selecionar curso...</option>
              {cursos.map(c => <option key={c.id} value={c.id}>{c.nome} ({c.tipo})</option>)}
            </select>
          </div>
          <div className="modal-footer">
            <button type="button" className="btn btn-secondary" onClick={() => setModalOpen(false)}>Cancelar</button>
            <button type="submit" className="btn btn-primary">{editingTurma ? 'Guardar' : 'Criar'}</button>
          </div>
        </form>
      </Modal>

      {/* Modal Gerir UCs da Turma */}
      <Modal isOpen={ucsModalOpen} onClose={() => setUcsModalOpen(false)} title={`UCs da Turma ${selectedTurma?.nome || ''}`}>
        <div style={{ marginBottom: '1rem' }}>
          <h4 className="section-title">UCs Associadas</h4>
          {selectedTurma?.unidadesCurriculares?.length > 0 ? (
            <div style={{ display: 'flex', flexWrap: 'wrap', gap: '0.375rem' }}>
              {selectedTurma.unidadesCurriculares.map(uc => (
                <span key={uc.id} className="badge badge-success" style={{ padding: '0.375rem 0.625rem' }}>
                  {uc.codigo} - {uc.nome}
                </span>
              ))}
            </div>
          ) : (
            <p style={{ color: 'var(--text-muted)', fontSize: '0.875rem' }}>Nenhuma UC associada</p>
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

        <div className="modal-footer">
          <button className="btn btn-secondary" onClick={() => setUcsModalOpen(false)}>Fechar</button>
        </div>
      </Modal>
    </div>
  );
}
