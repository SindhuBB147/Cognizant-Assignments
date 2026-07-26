import React, { Component } from 'react';
import './App.css';

class ComplaintRegister extends Component {
  constructor(props) {
    super(props);
    this.state = { employeeName: '', complaint: '' };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  }

  handleSubmit(event) {
    event.preventDefault();
    const { employeeName, complaint } = this.state;
    if (!employeeName || !complaint) {
      alert('Please fill in all fields');
      return;
    }
    const refNumber = 'REF' + Date.now();
    alert(`Complaint registered successfully!\nReference Number: ${refNumber}\nEmployee: ${employeeName}\nComplaint: ${complaint}`);
    this.setState({ employeeName: '', complaint: '' });
  }

  render() {
    return (
      <div style={{ padding: '20px' }}>
        <h1>Complaint Register</h1>
        <form onSubmit={this.handleSubmit}>
          <div>
            <label>Employee Name: </label>
            <input
              type="text"
              name="employeeName"
              value={this.state.employeeName}
              onChange={this.handleChange}
              placeholder="Enter employee name"
            />
          </div>
          <br />
          <div>
            <label>Complaint: </label>
            <br />
            <textarea
              name="complaint"
              value={this.state.complaint}
              onChange={this.handleChange}
              placeholder="Enter your complaint"
              rows="5"
              cols="40"
            />
          </div>
          <br />
          <button type="submit">Submit Complaint</button>
        </form>
      </div>
    );
  }
}

function App() {
  return (
    <div className="App">
      <ComplaintRegister />
    </div>
  );
}

export default App;
