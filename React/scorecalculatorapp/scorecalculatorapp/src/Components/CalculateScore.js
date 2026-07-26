import React from 'react';
import '../Stylesheets/mystyle.css';

const CalculateScore = ({ Name, School, Total, goal }) => {
  // Calculate average score
  // Since it accepts Name, School, Total (e.g., marks obtained) and goal (e.g., total marks or number of subjects)
  // Let's compute average. We'll show percentage as well.
  const average = Total && goal ? ((Total / goal) * 100).toFixed(2) : 0;

  return (
    <div className="score-card">
      <h2>Student Details</h2>
      <p><strong>Name:</strong> {Name}</p>
      <p><strong>School:</strong> {School}</p>
      <p><strong>Total Marks:</strong> {Total}</p>
      <p><strong>Goal (Total Max Marks):</strong> {goal}</p>
      <p className="average"><strong>Average Score:</strong> {average}%</p>
    </div>
  );
};

export default CalculateScore;
