import React from 'react';
import { Link } from 'react-router-dom';
import TrainersData from './TrainersMock';

function TrainersList() {
  return (
    <div style={{ padding: '20px' }}>
      <h2>Trainers List</h2>
      <ul>
        {TrainersData.map(trainer => (
          <li key={trainer.TrainerId} style={{ margin: '10px 0' }}>
            <Link to={`/trainer/${trainer.TrainerId}`}>{trainer.Name}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TrainersList;
