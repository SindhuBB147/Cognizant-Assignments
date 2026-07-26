import React, { useState } from 'react';

function CurrencyConvertor() {
  const [rupees, setRupees] = useState('');
  const [euros, setEuros] = useState(null);

  const handleSubmit = (event) => {
    event.preventDefault();
    if (rupees) {
      // 1 Euro = 85 Rupees conversion rate
      const result = (parseFloat(rupees) / 85).toFixed(2);
      setEuros(result);
    }
  };

  return (
    <div style={{ border: '1px solid #ccc', padding: '15px', borderRadius: '5px', marginTop: '20px', backgroundColor: '#fcfcfc' }}>
      <h3>Currency Convertor (Rupees to Euro)</h3>
      <form onSubmit={handleSubmit}>
        <div style={{ margin: '10px 0' }}>
          <label style={{ marginRight: '10px' }}>Enter Rupees (INR):</label>
          <input 
            type="number" 
            value={rupees} 
            onChange={(e) => setRupees(e.target.value)} 
            placeholder="INR"
            style={{ padding: '5px', borderRadius: '4px', border: '1px solid #ccc' }}
          />
        </div>
        <button type="submit" style={{ padding: '6px 12px', backgroundColor: '#3498db', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>
          Convert
        </button>
      </form>
      {euros !== null && (
        <h4 style={{ color: '#27ae60', marginTop: '10px' }}>Converted Amount: € {euros}</h4>
      )}
    </div>
  );
}

export default CurrencyConvertor;
