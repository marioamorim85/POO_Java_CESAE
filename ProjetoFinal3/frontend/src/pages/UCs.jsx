import { useEffect, useState } from 'react';
import { Plus, Pencil, Trash2, FileText } from 'lucide-react';
import Modal from '../components/Modal';
import { professorService, ucService } from '../services/api';

export default function UCs() {
  const [ucs, setUCs] = useState([]);
  const [professores, setProfessores] = useState([]);
  const [loading, setLoading] = useState(true);
  const [modalOpen, setModalOpen] = useState(false);
  const [editingUC, setEditingUC] = useState(null);
  const [formData, setFormData] = useState({ nome: '', codigo: '', cargaHoraria: '', professorId: '' });

  useEffect(() => { loadData(); }, []);

  async function loadData() {
    try {
      const [ucsData, profsData] = await Promise.all([ucService.listar(), professorService.listar()]);
      setUCs(ucsData);
      setProfessores(profsData);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  }

  function openModal(uc = null) {
    if (uc) {
      setEditingUC(uc);
      setFormData({ nome: uc.nome, codigo: uc.codigo, cargaHoraria: uc.cargaHoraria, professorId: uc.professor?.id || '' });
    } else {
      setEditingUC(null);
      setFormData({ nome: '', codigo: '', cargaHoraria: '', professorId: '' });
    }
    setModalOpen(true);
  }

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      const data = { nome: formData.nome, codigo: formData.codigo, cargaHoraria: parseInt(formData.cargaHoraria) };
      let ucId;
      if (editingUC) {
        await ucService.atualizar(editingUC.id, data);
        ucId = editingUC.id;
      } else {
        const newUC = await ucService.criar(data);
        ucId = newUC.id;
      }
      if (formData.professorId) {
        await ucService.associarProfessor(ucId, formData.professorId);
      }
      setModalOpen(false);
      loadData();
    } catch (err) {
      alert(err.message);
    }
  }

  async function handleDelete(id) {
    if (confirm('Tem certeza que deseja remover esta UC?')) {
      try {
        await ucService.remover(id);
        loadData();
      } catch (err) {
        alert(err.message);
      }
    }
  }

  if (loading) return <div className="loading"><div className="spinner"></div></div>;

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">Unidades Curriculares</h1>
        <button className="btn btn-primary" onClick={() => openModal()}>
          <Plus size={15} strokeWidth={2} /> Nova UC
        </button>
      </div>

      <div className="card">
        <table className="data-table">
          <thead>
            <tr>
              <th>Codigo</th>
              <th>Nome</th>
              <th>Carga Horaria</th>
              <th>Professor</th>
              <th>Acoes</th>
            </tr>
          </thead>
          <tbody>
            {ucs.map((uc) => (
              <tr key={uc.id}>
                <td><span className="badge badge-info">{uc.codigo}</span></td>
                <td>{uc.nome}</td>
                <td>{uc.cargaHoraria}h</td>
                <td>{uc.professor?.nome || <span style={{ color: 'var(--text-muted)' }}>Sem professor</span>}</td>
                <td>
                  <div className="actions">
                    <button className="btn btn-secondary btn-sm" onClick={() => openModal(uc)} title="Editar">
                      <Pencil size={14} strokeWidth={1.75} />
                    </button>
                    <button className="btn btn-danger btn-sm" onClick={() => handleDelete(uc.id)} title="Remover">
                      <Trash2 size={14} strokeWidth={1.75} />
                    </button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        {ucs.length === 0 && (
          <div className="empty-state">
            <div className="empty-state-icon"><FileText size={24} strokeWidth={1.5} /></div>
            <p>Nenhuma UC encontrada</p>
          </div>
        )}
      </div>

      <Modal isOpen={modalOpen} onClose={() => setModalOpen(false)} title={editingUC ? 'Editar UC' : 'Nova UC'}>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label className="form-label">Codigo</label>
            <input className="form-input" value={formData.codigo} onChange={(e) => setFormData({...formData, codigo: e.target.value})} placeholder="Ex: POO-101" required />
          </div>
          <div className="form-group">
            <label className="form-label">Nome</label>
            <input className="form-input" value={formData.nome} onChange={(e) => setFormData({...formData, nome: e.target.value})} required />
          </div>
          <div className="form-group">
            <label className="form-label">Carga Horaria (horas)</label>
            <input type="number" className="form-input" value={formData.cargaHoraria} onChange={(e) => setFormData({...formData, cargaHoraria: e.target.value})} required />
          </div>
          <div className="form-group">
            <label className="form-label">Professor</label>
            <select className="form-select" value={formData.professorId} onChange={(e) => setFormData({...formData, professorId: e.target.value})}>
              <option value="">Selecionar professor...</option>
              {professores.map(p => <option key={p.id} value={p.id}>{p.nome} ({p.especialidade})</option>)}
            </select>
          </div>
          <div className="modal-footer">
            <button type="button" className="btn btn-secondary" onClick={() => setModalOpen(false)}>Cancelar</button>
            <button type="submit" className="btn btn-primary">{editingUC ? 'Guardar' : 'Criar'}</button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
