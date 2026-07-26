import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Home from './Home';
import TrainersList from './TrainersList';
import TrainerDetails from './TrainerDetails';
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        <nav style={{ backgroundColor: '#34495e', padding: '15px', display: 'flex', gap: '20px' }}>
          <Link to="/" style={{ color: 'white', textDecoration: 'none', fontWeight: 'bold' }}>Home</Link>
          <Link to="/trainers" style={{ color: 'white', textDecoration: 'none', fontWeight: 'bold' }}>Trainers List</Link>
        </nav>
        
        <div style={{ padding: '20px' }}>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/trainers" element={<TrainersList />} />
            <Route path="/trainer/:id" element={<TrainerDetails />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
