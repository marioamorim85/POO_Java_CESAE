import { useEffect, useState } from 'react';
import { Plus, Pencil, Trash2, Users } from 'lucide-react';
import Modal from '../components/Modal';
import { alunoService, turmaService } from '../services/api';

const estadoOptions = ['ATIVO', 'SUSPENSO', 'CONCLUIDO', 'DESISTENTE'];

export default function Alunos() {
  const [alunos, setAlunos] = useState([]);
  const [turmas, setTurmas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [modalOpen, setModalOpen] = useState(false);
  const [editingAluno, setEditingAluno] = useState(null);
  const [notaModalOpen, setNotaModalOpen] = useState(false);
  const [turmaModalOpen, setTurmaModalOpen] = useState(false);
  const [selectedAluno, setSelectedAluno] = useState(null);
  const [novaNota, setNovaNota] = useState('');
  const [formData, setFormData] = useState({ nome: '', email: '', telefone: '', idade: '', turmaId: '', estado: 'ATIVO' });

  useEffect(() => { loadData(); }, []);

  async function loadData() {
    try {
      const [alunosData, turmasData] = await Promise.all([
        alunoService.listar(),
        turmaService.listar()
      ]);
      setAlunos(alunosData);
      setTurmas(turmasData);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  }

  function openModal(aluno = null) {
    if (aluno) {
      setEditingAluno(aluno);
      setFormData({
        nome: aluno.nome,
        email: aluno.email,
        telefone: aluno.telefone || '',
        idade: aluno.idade,
        turmaId: aluno.turma?.id || '',
        estado: aluno.estado
      });
    } else {
      setEditingAluno(null);
      setFormData({ nome: '', email: '', telefone: '', idade: '', turmaId: '', estado: 'ATIVO' });
    }
    setModalOpen(true);
  }

  function openTurmaModal(aluno) {
    setSelectedAluno(aluno);
    setTurmaModalOpen(true);
  }

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      const data = { ...formData, idade: parseInt(formData.idade) };
      let alunoId;
      if (editingAluno) {
        await alunoService.atualizar(editingAluno.id, data);
        alunoId = editingAluno.id;
        if (formData.turmaId && formData.turmaId !== editingAluno.turma?.id) {
          await alunoService.inscreverTurma(alunoId, formData.turmaId);
        }
      } else {
        const novoAluno = await alunoService.criar(data);
        if (formData.turmaId) {
          await alunoService.inscreverTurma(novoAluno.id, formData.turmaId);
        }
      }
      setModalOpen(false);
      loadData();
    } catch (err) {
      alert(err.message);
    }
  }

  async function handleMudarTurma(turmaId) {
    try {
      await alunoService.inscreverTurma(selectedAluno.id, turmaId);
      setTurmaModalOpen(false);
      loadData();
    } catch (err) {
      alert(err.message);
    }
  }

  async function handleDelete(id) {
    if (confirm('Tem certeza que deseja remover este aluno?')) {
      try {
        await alunoService.remover(id);
        loadData();
      } catch (err) {
        alert(err.message);
      }
    }
  }

  async function handleAddNota() {
    if (!novaNota || isNaN(novaNota)) return;
    try {
      await alunoService.adicionarNota(selectedAluno.id, parseFloat(novaNota));
      setNotaModalOpen(false);
      setNovaNota('');
      loadData();
    } catch (err) {
      alert(err.message);
    }
  }

  async function handleEstadoChange(id, estado) {
    try {
      await alunoService.alterarEstado(id, estado);
      loadData();
    } catch (err) {
      alert(err.message);
    }
  }

  if (loading) return <div className="loading"><div className="spinner"></div></div>;

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">Alunos</h1>
        <button className="btn btn-primary" onClick={() => openModal()}>
          <Plus size={15} strokeWidth={2} /> Novo Aluno
        </button>
      </div>

      <div className="card">
        <table className="data-table">
          <thead>
            <tr>
              <th>N.o</th>
              <th>Nome</th>
              <th>Email</th>
              <th>Turma</th>
              <th>Media</th>
              <th>Estado</th>
              <th>Acoes</th>
            </tr>
          </thead>
          <tbody>
            {alunos.map((aluno) => (
              <tr key={aluno.id}>
                <td>{aluno.numeroAluno}</td>
                <td>{aluno.nome}</td>
                <td>{aluno.email}</td>
                <td>
                  <span
                    className={`badge ${aluno.turma ? 'badge-success' : 'badge-warning'}`}
                    style={{ cursor: 'pointer' }}
                    onClick={() => openTurmaModal(aluno)}
                  >
                    {aluno.turma?.nome || 'Sem Turma'}
                  </span>
                </td>
                <td>{(aluno.notas?.length > 0 ? (aluno.notas.reduce((a, b) => a + b, 0) / aluno.notas.length).toFixed(1) : '-')}</td>
                <td>
                  <select
                    className="form-select"
                    value={aluno.estado}
                    onChange={(e) => handleEstadoChange(aluno.id, e.target.value)}
                    style={{ width: 'auto', padding: '0.25rem 0.5rem', fontSize: '0.75rem' }}
                  >
                    {estadoOptions.map(e => <option key={e} value={e}>{e}</option>)}
                  </select>
                </td>
                <td>
                  <div className="actions">
                    <button className="btn btn-secondary btn-sm" onClick={() => { setSelectedAluno(aluno); setNotaModalOpen(true); }}>+ Nota</button>
                    <button className="btn btn-secondary btn-sm" onClick={() => openModal(aluno)} title="Editar">
                      <Pencil size={14} strokeWidth={1.75} />
                    </button>
                    <button className="btn btn-danger btn-sm" onClick={() => handleDelete(aluno.id)} title="Remover">
                      <Trash2 size={14} strokeWidth={1.75} />
                    </button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        {alunos.length === 0 && (
          <div className="empty-state">
            <div className="empty-state-icon"><Users size={24} strokeWidth={1.5} /></div>
            <p>Nenhum aluno encontrado</p>
          </div>
        )}
      </div>

      {/* Modal Criar/Editar */}
      <Modal isOpen={modalOpen} onClose={() => setModalOpen(false)} title={editingAluno ? 'Editar Aluno' : 'Novo Aluno'}>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label className="form-label">Nome</label>
            <input className="form-input" value={formData.nome} onChange={(e) => setFormData({...formData, nome: e.target.value})} required />
          </div>
          <div className="form-group">
            <label className="form-label">Email</label>
            <input type="email" className="form-input" value={formData.email} onChange={(e) => setFormData({...formData, email: e.target.value})} required />
          </div>
          <div className="form-group">
            <label className="form-label">Telefone</label>
            <input className="form-input" value={formData.telefone} onChange={(e) => setFormData({...formData, telefone: e.target.value})} />
          </div>
          <div className="form-group">
            <label className="form-label">Idade</label>
            <input type="number" className="form-input" value={formData.idade} onChange={(e) => setFormData({...formData, idade: e.target.value})} required />
          </div>
          <div className="form-group">
            <label className="form-label">Turma</label>
            <select className="form-select" value={formData.turmaId} onChange={(e) => setFormData({...formData, turmaId: e.target.value})}>
              <option value="">Selecionar turma...</option>
              {turmas.map(t => <option key={t.id} value={t.id}>{t.nome} ({t.curso?.nome || 'Sem curso'})</option>)}
            </select>
          </div>
          <div className="modal-footer">
            <button type="button" className="btn btn-secondary" onClick={() => setModalOpen(false)}>Cancelar</button>
            <button type="submit" className="btn btn-primary">{editingAluno ? 'Guardar' : 'Criar'}</button>
          </div>
        </form>
      </Modal>

      {/* Modal Mudar Turma */}
      <Modal isOpen={turmaModalOpen} onClose={() => setTurmaModalOpen(false)} title={`Turma de ${selectedAluno?.nome || ''}`}>
        <div style={{ marginBottom: '1rem' }}>
          <h4 className="section-title">Turma Atual</h4>
          {selectedAluno?.turma ? (
            <span className="badge badge-success" style={{ padding: '0.375rem 0.625rem' }}>
              {selectedAluno.turma.nome} - {selectedAluno.turma.curso?.nome || 'Sem curso'}
            </span>
          ) : (
            <p style={{ color: 'var(--text-muted)', fontSize: '0.875rem' }}>Aluno nao esta inscrito em nenhuma turma</p>
          )}
        </div>

        <div>
          <h4 className="section-title">Mudar para outra turma</h4>
          <div className="list-container">
            {turmas.filter(t => t.id !== selectedAluno?.turma?.id).map(turma => (
              <div key={turma.id} className="list-item">
                <div>
                  <span style={{ fontWeight: '500' }}>{turma.nome}</span>
                  <span style={{ fontSize: '0.75rem', color: 'var(--text-muted)', marginLeft: '0.5rem' }}>
                    {turma.curso?.nome || 'Sem curso'} &middot; {turma.alunos?.length || 0}/{turma.capacidadeMaxima}
                  </span>
                </div>
                <button
                  className="btn btn-primary btn-sm"
                  onClick={() => handleMudarTurma(turma.id)}
                  disabled={!turma.capacidadeMaxima || (turma.alunos?.length || 0) >= turma.capacidadeMaxima}
                >
                  Inscrever
                </button>
              </div>
            ))}
          </div>
        </div>

        <div className="modal-footer">
          <button className="btn btn-secondary" onClick={() => setTurmaModalOpen(false)}>Fechar</button>
        </div>
      </Modal>

      {/* Modal Adicionar Nota */}
      <Modal isOpen={notaModalOpen} onClose={() => setNotaModalOpen(false)} title={`Adicionar Nota - ${selectedAluno?.nome}`}>
        <div className="form-group">
          <label className="form-label">Nota (0-20)</label>
          <input type="number" min="0" max="20" step="0.1" className="form-input" value={novaNota} onChange={(e) => setNovaNota(e.target.value)} />
        </div>
        {selectedAluno?.notas?.length > 0 && (
          <div style={{ marginBottom: '1rem', color: 'var(--text-muted)', fontSize: '0.875rem' }}>
            Notas atuais: {selectedAluno.notas.join(', ')}
          </div>
        )}
        <div className="modal-footer">
          <button className="btn btn-secondary" onClick={() => setNotaModalOpen(false)}>Cancelar</button>
          <button className="btn btn-primary" onClick={handleAddNota}>Adicionar</button>
        </div>
      </Modal>
    </div>
  );
}
