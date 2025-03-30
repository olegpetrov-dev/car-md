import { BrowserRouter as Router, Routes, Route, useLocation } from 'react-router-dom';
import CarSearch from './components/CarSearch';
import CarDetails from './components/CarDetails';
import './App.css';

function AppContent() {
  const location = useLocation();
  const showHeader = location.pathname === '/';

  return (
    <div className="app">
      {showHeader && (
        <header className="app-header">
          <h1>Car Search</h1>
        </header>
      )}
      <main>
        <Routes>
          <Route path="/" element={<CarSearch />} />
          <Route path="/cars/:id" element={<CarDetails />} />
        </Routes>
      </main>
    </div>
  );
}

function App() {
  return (
    <Router>
      <AppContent />
    </Router>
  );
}

export default App;
