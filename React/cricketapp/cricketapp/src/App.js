import React from 'react';
import ListofPlayers from './ListofPlayers';
import IndianPlayers from './IndianPlayers';
import './App.css';

function App() {
  const Flag = true; // Set to false to see IndianPlayers

  return (
    <div className="App" style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
      <h1>Cricket Application Dashboard</h1>
      <p>Current Flag Value: <strong>{Flag.toString()}</strong></p>
      <hr />
      {Flag ? <ListofPlayers /> : <IndianPlayers />}
    </div>
  );
}

export default App;
