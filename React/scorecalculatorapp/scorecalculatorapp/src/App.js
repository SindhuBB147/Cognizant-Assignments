import React from 'react';
import CalculateScore from './Components/CalculateScore';
import './App.css';

function App() {
  return (
    <div className="App" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh', flexDirection: 'column' }}>
      <h1 style={{ color: '#2c3e50' }}>Student Portal - Score Calculator</h1>
      <CalculateScore 
        Name="Ramansh" 
        School="Delhi Public School" 
        Total={450} 
        goal={500} 
      />
    </div>
  );
}

export default App;
