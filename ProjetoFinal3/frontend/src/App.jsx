import { Route, Routes } from 'react-router-dom';
import Sidebar from './components/Sidebar';
import Alunos from './pages/Alunos';
import Cursos from './pages/Cursos';
import Dashboard from './pages/Dashboard';
import Professores from './pages/Professores';
import Turmas from './pages/Turmas';
import UCs from './pages/UCs';

export default function App() {
  return (
    <div className="app-layout">
      <Sidebar />
      <main className="main-content">
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/alunos" element={<Alunos />} />
          <Route path="/professores" element={<Professores />} />
          <Route path="/cursos" element={<Cursos />} />
          <Route path="/turmas" element={<Turmas />} />
          <Route path="/ucs" element={<UCs />} />
        </Routes>
      </main>
    </div>
  );
}
