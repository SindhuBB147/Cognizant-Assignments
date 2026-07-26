import React, { useState } from 'react';
import CurrencyConvertor from './CurrencyConvertor';
import './App.css';

function App() {
  const [counter, setCounter] = useState(0);

  // Invoke multiple methods on increment
  const handleIncrement = () => {
    // 1. To increment the value
    setCounter(prev => prev + 1);
    // 2. Say Hello followed by static message
    alert("Hello, welcome to the event examples!");
  };

  const handleDecrement = () => {
    setCounter(prev => prev - 1);
  };

  const handleSayWelcome = (msg) => {
    alert(msg);
  };

  const handleOnPress = (event) => {
    // Displays "I was clicked" using synthetic event
    alert("I was clicked");
  };

  return (
    <div style={{ padding: '25px', fontFamily: 'Arial, sans-serif', maxWidth: '600px', margin: '0 auto' }}>
      <h2>React Event Examples</h2>
      <hr />
      
      {/* 1. Counter */}
      <div style={{ margin: '20px 0', border: '1px solid #ccc', padding: '15px', borderRadius: '5px' }}>
        <h3>Counter Value: {counter}</h3>
        <button onClick={handleIncrement} style={{ marginRight: '10px', padding: '8px 16px', cursor: 'pointer' }}>
          Increment
        </button>
        <button onClick={handleDecrement} style={{ padding: '8px 16px', cursor: 'pointer' }}>
          Decrement
        </button>
      </div>

      {/* 2. Say Welcome */}
      <div style={{ margin: '20px 0', border: '1px solid #ccc', padding: '15px', borderRadius: '5px' }}>
        <h3>Parametrized Click Handler</h3>
        <button onClick={() => handleSayWelcome('welcome')} style={{ padding: '8px 16px', cursor: 'pointer' }}>
          Say Welcome
        </button>
      </div>

      {/* 3. Synthetic Event click */}
      <div style={{ margin: '20px 0', border: '1px solid #ccc', padding: '15px', borderRadius: '5px' }}>
        <h3>Synthetic Event Handler</h3>
        <button onClick={handleOnPress} style={{ padding: '8px 16px', cursor: 'pointer' }}>
          Synthetic OnPress
        </button>
      </div>

      {/* 4. Currency Convertor Component */}
      <CurrencyConvertor />
    </div>
  );
}

export default App;
