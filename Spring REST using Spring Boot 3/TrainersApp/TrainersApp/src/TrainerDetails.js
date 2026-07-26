import React from 'react';
import { useParams, Link } from 'react-router-dom';
import TrainersData from './TrainersMock';

function TrainerDetails() {
  const { id } = useParams();
  const trainer = TrainersData.find(t => t.TrainerId === parseInt(id));

  if (!trainer) {
    return (
      <div style={{ padding: '20px' }}>
        <h3>Trainer details not found for ID: {id}</h3>
        <Link to="/trainers">Back to Trainers List</Link>
      </div>
    );
  }

  return (
    <div style={{ padding: '20px', border: '1px solid #ccc', borderRadius: '5px', maxWidth: '400px', margin: '20px' }}>
      <h3>Trainer details: {trainer.Name}</h3>
      <p><strong>ID:</strong> {trainer.TrainerId}</p>
      <p><strong>Email:</strong> {trainer.Email}</p>
      <p><strong>Phone:</strong> {trainer.Phone}</p>
      <p><strong>Technology:</strong> {trainer.Technology}</p>
      <p><strong>Skills:</strong> {trainer.Skills}</p>
      <br />
      <Link to="/trainers" style={{ textDecoration: 'underline', color: 'blue' }}>Back to Trainers List</Link>
    </div>
  );
}

export default TrainerDetails;
