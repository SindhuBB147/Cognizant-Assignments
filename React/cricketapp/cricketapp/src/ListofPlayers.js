import React from 'react';

function ListofPlayers() {
  const players = [
    { name: 'Sachin Tendulkar', score: 120 },
    { name: 'Virat Kohli', score: 95 },
    { name: 'MS Dhoni', score: 85 },
    { name: 'Rohit Sharma', score: 110 },
    { name: 'KL Rahul', score: 45 },
    { name: 'Shikhar Dhawan', score: 68 },
    { name: 'Hardik Pandya', score: 72 },
    { name: 'Rishabh Pant', score: 55 },
    { name: 'Ravindra Jadeja', score: 62 },
    { name: 'Jasprit Bumrah', score: 15 },
    { name: 'Mohammed Shami', score: 10 }
  ];

  // Filter players with scores below 70
  const playersBelow70 = players.filter(player => player.score < 70);

  return (
    <div style={{ padding: '10px' }}>
      <h3>List of All Players:</h3>
      <ul>
        {players.map((player, idx) => (
          <li key={idx}>{player.name} - Score: {player.score}</li>
        ))}
      </ul>

      <h3>Players with Score Below 70:</h3>
      <ul>
        {playersBelow70.map((player, idx) => (
          <li key={idx} style={{ color: 'red' }}>{player.name} - Score: {player.score}</li>
        ))}
      </ul>
    </div>
  );
}

export default ListofPlayers;
