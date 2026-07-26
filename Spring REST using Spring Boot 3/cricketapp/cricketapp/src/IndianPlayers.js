import React from 'react';

function IndianPlayers() {
  const indianPlayersList = [
    'Sachin Tendulkar',
    'Virat Kohli',
    'MS Dhoni',
    'Rohit Sharma',
    'KL Rahul',
    'Shikhar Dhawan'
  ];

  // Destructuring players
  const [first, second, third, fourth, fifth, sixth] = indianPlayersList;
  const oddTeam = [first, third, fifth];
  const evenTeam = [second, fourth, sixth];

  // Merge using ES6 Spread/Merge
  const T20players = ['Virat Kohli', 'Rohit Sharma', 'Hardik Pandya'];
  const RanjiTrophyPlayers = ['Cheteshwar Pujara', 'Ajinkya Rahane', 'Priyank Panchal'];
  const mergedPlayers = [...T20players, ...RanjiTrophyPlayers];

  return (
    <div style={{ padding: '10px' }}>
      <h3>Odd Team Players:</h3>
      <ul>
        {oddTeam.map((p, idx) => <li key={idx}>{p}</li>)}
      </ul>

      <h3>Even Team Players:</h3>
      <ul>
        {evenTeam.map((p, idx) => <li key={idx}>{p}</li>)}
      </ul>

      <h3>Merged T20 & Ranji Trophy Players:</h3>
      <ul>
        {mergedPlayers.map((p, idx) => <li key={idx}>{p}</li>)}
      </ul>
    </div>
  );
}

export default IndianPlayers;
