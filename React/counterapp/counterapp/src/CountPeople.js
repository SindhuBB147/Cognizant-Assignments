import React, { Component } from 'react';

class CountPeople extends Component {
  constructor(props) {
    super(props);
    this.state = {
      entrycount: 0,
      exitcount: 0
    };
    
    // Bind event handlers
    this.UpdateEntry = this.UpdateEntry.bind(this);
    this.UpdateExit = this.UpdateExit.bind(this);
  }

  UpdateEntry() {
    this.setState(prevState => ({
      entrycount: prevState.entrycount + 1
    }));
  }

  UpdateExit() {
    this.setState(prevState => ({
      exitcount: prevState.exitcount + 1
    }));
  }

  render() {
    return (
      <div style={{ textAlign: 'center', padding: '20px', fontFamily: 'Arial, sans-serif' }}>
        <h2>Visitor Counter Application</h2>
        <div style={{ display: 'flex', justifyContent: 'center', gap: '30px', margin: '20px' }}>
          <div style={{ border: '1px solid #ccc', padding: '20px', borderRadius: '8px', width: '150px', backgroundColor: '#d5f5e3' }}>
            <h3>Entry Count</h3>
            <p style={{ fontSize: '2em', margin: '10px 0' }}>{this.state.entrycount}</p>
            <button onClick={this.UpdateEntry} style={{ padding: '8px 16px', backgroundColor: '#2ecc71', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>
              Login
            </button>
          </div>
          <div style={{ border: '1px solid #ccc', padding: '20px', borderRadius: '8px', width: '150px', backgroundColor: '#fadbd8' }}>
            <h3>Exit Count</h3>
            <p style={{ fontSize: '2em', margin: '10px 0' }}>{this.state.exitcount}</p>
            <button onClick={this.UpdateExit} style={{ padding: '8px 16px', backgroundColor: '#e74c3c', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>
              Exit
            </button>
          </div>
        </div>
      </div>
    );
  }
}

export default CountPeople;
