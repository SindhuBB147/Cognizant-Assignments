import React, { Component } from 'react';
import Cart from './Cart';

class OnlineShopping extends Component {
  render() {
    const items = [
      { id: 1, Itemname: 'Laptop', Price: 'Rs 55000' },
      { id: 2, Itemname: 'Smartphone', Price: 'Rs 15000' },
      { id: 3, Itemname: 'Headphones', Price: 'Rs 2000' },
      { id: 4, Itemname: 'Smartwatch', Price: 'Rs 5000' },
      { id: 5, Itemname: 'Keyboard', Price: 'Rs 1200' }
    ];

    return (
      <div style={{ padding: '20px', maxWidth: '500px', margin: '0 auto', fontFamily: 'Arial, sans-serif' }}>
        <h2 style={{ borderBottom: '2px solid #333', paddingBottom: '10px', textAlign: 'center' }}>Online Shopping Items</h2>
        <table style={{ width: '100%', borderCollapse: 'collapse', marginTop: '10px' }}>
          <thead>
            <tr style={{ backgroundColor: '#34495e', color: 'white', textAlign: 'left' }}>
              <th style={{ padding: '10px' }}>Item Name</th>
              <th style={{ padding: '10px' }}>Price</th>
            </tr>
          </thead>
          <tbody>
            {items.map(item => (
              <Cart key={item.id} Itemname={item.Itemname} Price={item.Price} />
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

export default OnlineShopping;
