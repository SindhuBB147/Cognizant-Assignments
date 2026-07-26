import React from 'react';
import Posts from './Posts';
import './App.css';

function App() {
  return (
    <div className="App">
      <header style={{ backgroundColor: '#2c3e50', padding: '20px', color: 'white', textAlign: 'center' }}>
        <h1>My Personal Blog</h1>
      </header>
      <Posts />
    </div>
  );
}

export default App;
