import React, { Component } from 'react';
import './App.css';

class TicketBooking extends Component {
  constructor(props) {
    super(props);
    this.state = { isLoggedIn: false };
    this.handleLogin = this.handleLogin.bind(this);
    this.handleLogout = this.handleLogout.bind(this);
  }

  handleLogin() {
    this.setState({ isLoggedIn: true });
  }

  handleLogout() {
    this.setState({ isLoggedIn: false });
  }

  render() {
    const { isLoggedIn } = this.state;
    return (
      <div style={{ padding: '20px' }}>
        <h1>Flight Ticket Booking</h1>
        <div>
          {isLoggedIn ? (
            <button onClick={this.handleLogout}>Logout</button>
          ) : (
            <button onClick={this.handleLogin}>Login</button>
          )}
        </div>
        <hr />
        {isLoggedIn ? <UserPage /> : <GuestPage />}
      </div>
    );
  }
}

function GuestPage() {
  return (
    <div>
      <h2>Guest Page</h2>
      <p>You can browse flight details below, but please login to book tickets.</p>
      <FlightDetails />
    </div>
  );
}

function UserPage() {
  return (
    <div>
      <h2>User Page</h2>
      <p>Welcome! You can now book tickets for the flights below.</p>
      <FlightDetails />
    </div>
  );
}

function FlightDetails() {
  const flights = [
    { id: 1, airline: 'Air India', source: 'Delhi', destination: 'Mumbai', price: 4500 },
    { id: 2, airline: 'IndiGo', source: 'Bangalore', destination: 'Chennai', price: 3200 },
    { id: 3, airline: 'SpiceJet', source: 'Kolkata', destination: 'Hyderabad', price: 3800 },
  ];

  return (
    <div>
      <h3>Flight Details</h3>
      <table border="1" cellPadding="10" cellSpacing="0">
        <thead>
          <tr>
            <th>Airline</th>
            <th>Source</th>
            <th>Destination</th>
            <th>Price (Rs)</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {flights.map(flight => (
            <tr key={flight.id}>
              <td>{flight.airline}</td>
              <td>{flight.source}</td>
              <td>{flight.destination}</td>
              <td>{flight.price}</td>
              <td>
                <button onClick={() => alert(`Booking confirmed for ${flight.airline}!`)}>
                  Book Now
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default TicketBooking;
