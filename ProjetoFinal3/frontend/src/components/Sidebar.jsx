import { NavLink } from 'react-router-dom';
import {
  LayoutDashboard,
  Users,
  UserCheck,
  BookOpen,
  UsersRound,
  FileText,
} from 'lucide-react';

export default function Sidebar() {
  const navItems = [
    { path: '/', label: 'Dashboard', icon: LayoutDashboard },
    { path: '/alunos', label: 'Alunos', icon: Users },
    { path: '/professores', label: 'Professores', icon: UserCheck },
    { path: '/cursos', label: 'Cursos', icon: BookOpen },
    { path: '/turmas', label: 'Turmas', icon: UsersRound },
    { path: '/ucs', label: 'Unidades Curriculares', icon: FileText },
  ];

  return (
    <aside className="sidebar">
      <div className="sidebar-header">
        <h1>CESAE Digital</h1>
        <span>Gestao Academica</span>
      </div>

      <nav className="sidebar-nav">
        {navItems.map((item) => {
          const Icon = item.icon;
          return (
            <NavLink
              key={item.path}
              to={item.path}
              className={({ isActive }) => `nav-item ${isActive ? 'active' : ''}`}
              end={item.path === '/'}
            >
              <Icon size={18} strokeWidth={1.75} />
              {item.label}
            </NavLink>
          );
        })}
      </nav>

      <div className="sidebar-footer">
        POO Java - CESAE 2025
      </div>
    </aside>
  );
}
