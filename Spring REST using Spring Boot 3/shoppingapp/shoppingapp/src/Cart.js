import React, { Component } from 'react';

class Cart extends Component {
  render() {
    return (
      <tr style={{ borderBottom: '1px solid #ccc' }}>
        <td style={{ padding: '10px' }}>{this.props.Itemname}</td>
        <td style={{ padding: '10px' }}>{this.props.Price}</td>
      </tr>
    );
  }
}

export default Cart;
