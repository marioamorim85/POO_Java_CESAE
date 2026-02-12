import { Pencil, Plus, Trash2, UserCheck } from 'lucide-react';
import { useEffect, useState } from 'react';
import Modal from '../components/Modal';
import { professorService, ucService } from '../services/api';

export default function Professores() {
  const [professores, setProfessores] = useState([]);
  const [ucs, setUCs] = useState([]);
  const [loading, setLoading] = useState(true);
  const [modalOpen, setModalOpen] = useState(false);
  const [ucsModalOpen, setUcsModalOpen] = useState(false);
  const [editingProf, setEditingProf] = useState(null);
  const [selectedProf, setSelectedProf] = useState(null);
  const [formData, setFormData] = useState({ nome: '', email: '', telefone: '', idade: '', especialidade: '', salario: '' });

  useEffect(() => { loadData(); }, []);

  async function loadData() {
    try {
      const [profsData, ucsData] = await Promise.all([
        professorService.listar(),
        ucService.listar()
      ]);
      setProfessores(profsData);
      setUCs(ucsData);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  }

  function openModal(prof = null) {
    if (prof) {
      setEditingProf(prof);
      setFormData({ nome: prof.nome, email: prof.email, telefone: prof.telefone || '', idade: prof.idade, especialidade: prof.especialidade, salario: prof.salario });
    } else {
      setEditingProf(null);
      setFormData({ nome: '', email: '', telefone: '', idade: '', especialidade: '', salario: '' });
    }
    setModalOpen(true);
  }

  function openUcsModal(prof) {
    setSelectedProf(prof);
    setUcsModalOpen(true);
  }

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      const data = { ...formData, idade: parseInt(formData.idade), salario: parseFloat(formData.salario) || 0 };
      if (editingProf) {
        await professorService.atualizar(editingProf.id, data);
      } else {
        await professorService.criar(data);
      }
      setModalOpen(false);
      loadData();
    } catch (err) {
      alert(err.message);
    }
  }

  async function handleAssociarUC(ucId) {
    try {
      await ucService.associarProfessor(ucId, selectedProf.id);
      loadData();
      const updatedProf = await professorService.buscar(selectedProf.id);
      setSelectedProf(updatedProf);
    } catch (err) {
      alert(err.message);
    }
  }

  async function handleDelete(id) {
    if (confirm('Tem certeza que deseja remover este professor?')) {
      try {
        await professorService.remover(id);
        loadData();
      } catch (err) {
        alert(err.message);
      }
    }
  }

  if (loading) return <div className="loading"><div className="spinner"></div></div>;

  const profUcIds = selectedProf?.unidadesCurriculares?.map(uc => uc.id) || [];
  const ucsDisponiveis = ucs.filter(uc => !uc.professor && !profUcIds.includes(uc.id));

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">Professores</h1>
        <button className="btn btn-primary" onClick={() => openModal()}>
          <Plus size={15} strokeWidth={2} /> Novo Professor
        </button>
      </div>

      <div className="card">
        <table className="data-table">
          <thead>
            <tr>
              <th>N.o</th>
              <th>Nome</th>
              <th>Email</th>
              <th>Especialidade</th>
              <th>Salario</th>
              <th>UCs</th>
              <th>Acoes</th>
            </tr>
          </thead>
          <tbody>
            {professores.map((prof) => (
              <tr key={prof.id}>
                <td>{prof.numeroProfessor}</td>
                <td>{prof.nome}</td>
                <td>{prof.email}</td>
                <td>{prof.especialidade}</td>
                <td>{prof.salario?.toFixed(2)} &euro;</td>
                <td>
                  <span
                    className="badge badge-blue badge-clickable"
                    onClick={() => openUcsModal(prof)}
                  >
                    {prof.unidadesCurriculares?.length || 0}/5 UCs
                  </span>
                </td>
                <td>
                  <div className="actions">
                    <button className="btn btn-secondary btn-sm" onClick={() => openModal(prof)} title="Editar">
                      <Pencil size={14} strokeWidth={1.75} />
                    </button>
                    <button className="btn btn-danger btn-sm" onClick={() => handleDelete(prof.id)} title="Remover">
                      <Trash2 size={14} strokeWidth={1.75} />
                    </button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        {professores.length === 0 && (
          <div className="empty-state">
            <div className="empty-state-icon"><UserCheck size={24} strokeWidth={1.5} /></div>
            <p>Nenhum professor encontrado</p>
          </div>
        )}
      </div>

      {/* Modal Criar/Editar Professor */}
      <Modal isOpen={modalOpen} onClose={() => setModalOpen(false)} title={editingProf ? 'Editar Professor' : 'Novo Professor'}>
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
            <label className="form-label">Especialidade</label>
            <input className="form-input" value={formData.especialidade} onChange={(e) => setFormData({...formData, especialidade: e.target.value})} required />
          </div>
          <div className="form-group">
            <label className="form-label">Salario (&euro;)</label>
            <input type="number" step="0.01" className="form-input" value={formData.salario} onChange={(e) => setFormData({...formData, salario: e.target.value})} />
          </div>
          <div className="modal-footer">
            <button type="button" className="btn btn-secondary" onClick={() => setModalOpen(false)}>Cancelar</button>
            <button type="submit" className="btn btn-primary">{editingProf ? 'Guardar' : 'Criar'}</button>
          </div>
        </form>
      </Modal>

      {/* Modal Gerir UCs do Professor */}
      <Modal isOpen={ucsModalOpen} onClose={() => setUcsModalOpen(false)} title={`UCs do Professor ${selectedProf?.nome || ''}`}>
        <div style={{ marginBottom: '1rem' }}>
          <h4 className="section-title">UCs que Leciona ({selectedProf?.unidadesCurriculares?.length || 0}/5)</h4>
          {selectedProf?.unidadesCurriculares?.length > 0 ? (
            <div style={{ display: 'flex', flexWrap: 'wrap', gap: '0.375rem' }}>
              {selectedProf.unidadesCurriculares.map(uc => (
                <span key={uc.id} className="badge badge-success" style={{ padding: '0.375rem 0.625rem' }}>
                  {uc.codigo} - {uc.nome}
                </span>
              ))}
            </div>
          ) : (
            <p style={{ color: 'var(--text-muted)', fontSize: '0.875rem' }}>Nenhuma UC atribuida</p>
          )}
        </div>

        {(selectedProf?.unidadesCurriculares?.length || 0) < 5 && (
          <div>
            <h4 className="section-title">Atribuir UC</h4>
            {ucsDisponiveis.length > 0 ? (
              <div className="list-container">
                {ucsDisponiveis.map(uc => (
                  <div key={uc.id} className="list-item">
                    <span>{uc.codigo} - {uc.nome}</span>
                    <button className="btn btn-primary btn-sm" onClick={() => handleAssociarUC(uc.id)}>
                      Atribuir
                    </button>
                  </div>
                ))}
              </div>
            ) : (
              <p style={{ color: 'var(--text-muted)', fontSize: '0.875rem' }}>Nao ha UCs disponiveis (sem professor)</p>
            )}
          </div>
        )}

        <div className="modal-footer">
          <button className="btn btn-secondary" onClick={() => setUcsModalOpen(false)}>Fechar</button>
        </div>
      </Modal>
    </div>
  );
}
