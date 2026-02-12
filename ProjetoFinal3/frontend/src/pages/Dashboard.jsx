import { useEffect, useState } from 'react';
import { estatisticasService } from '../services/api';
import { Users, UserCheck, BookOpen, UsersRound, FileText } from 'lucide-react';

export default function Dashboard() {
  const [totais, setTotais] = useState(null);
  const [alunosPorEstado, setAlunosPorEstado] = useState(null);
  const [ocupacaoTurmas, setOcupacaoTurmas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    async function loadData() {
      try {
        const [totaisData, estadosData, ocupacaoData] = await Promise.all([
          estatisticasService.getTotais(),
          estatisticasService.getAlunosPorEstado(),
          estatisticasService.getOcupacaoTurmas(),
        ]);
        setTotais(totaisData);
        setAlunosPorEstado(estadosData);
        setOcupacaoTurmas(ocupacaoData);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    }
    loadData();
  }, []);

  if (loading) return <div className="loading"><div className="spinner"></div></div>;
  if (error) return (
    <div className="card">
      <p style={{ color: 'var(--danger)' }}>Erro: {error}</p>
      <p style={{ color: 'var(--text-muted)', marginTop: '0.5rem', fontSize: '0.875rem' }}>
        Verifique se o backend esta a correr em http://localhost:8080
      </p>
    </div>
  );

  const statCards = [
    { label: 'Alunos', value: totais?.alunos || 0, icon: Users },
    { label: 'Professores', value: totais?.professores || 0, icon: UserCheck },
    { label: 'Cursos', value: totais?.cursos || 0, icon: BookOpen },
    { label: 'Turmas', value: totais?.turmas || 0, icon: UsersRound },
    { label: 'UCs', value: totais?.ucs || 0, icon: FileText },
  ];

  const estadoBadge = (estado) => {
    if (estado === 'ATIVO') return 'badge-success';
    if (estado === 'SUSPENSO') return 'badge-warning';
    if (estado === 'CONCLUIDO') return 'badge-info';
    return 'badge-danger';
  };

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">Dashboard</h1>
      </div>

      <div className="stats-grid">
        {statCards.map(({ label, value, icon: Icon }) => (
          <div className="stat-card" key={label}>
            <div className="stat-icon">
              <Icon size={20} strokeWidth={1.5} />
            </div>
            <div className="stat-content">
              <h3>{value}</h3>
              <p>{label}</p>
            </div>
          </div>
        ))}
      </div>

      <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '1rem' }}>
        <div className="card">
          <div className="card-header">
            <h3 className="card-title">Alunos por Estado</h3>
          </div>
          <div style={{ display: 'flex', flexDirection: 'column', gap: '0.625rem' }}>
            {alunosPorEstado && Object.entries(alunosPorEstado).map(([estado, count]) => (
              <div key={estado} style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                <span className={`badge ${estadoBadge(estado)}`}>{estado}</span>
                <span style={{ fontSize: '0.875rem', fontWeight: '600' }}>{count}</span>
              </div>
            ))}
          </div>
        </div>

        <div className="card">
          <div className="card-header">
            <h3 className="card-title">Ocupacao das Turmas</h3>
          </div>
          <div style={{ display: 'flex', flexDirection: 'column', gap: '0.875rem' }}>
            {ocupacaoTurmas.map((turma) => (
              <div key={turma.id}>
                <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '0.375rem', fontSize: '0.875rem' }}>
                  <span>{turma.nome}</span>
                  <span style={{ color: 'var(--text-muted)' }}>{turma.alunos}/{turma.capacidade}</span>
                </div>
                <div className="progress-track">
                  <div
                    className="progress-bar"
                    style={{
                      width: `${turma.ocupacao}%`,
                      background: turma.ocupacao > 90 ? 'var(--danger)' : 'var(--accent)',
                    }}
                  ></div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
