import React, { Component } from 'react';

class Getuser extends Component {
  constructor(props) {
    super(props);
    this.state = {
      title: '',
      firstname: '',
      image: ''
    };
  }

  componentDidMount() {
    fetch('https://api.randomuser.me/')
      .then(response => response.json())
      .then(data => {
        const user = data.results[0];
        this.setState({
          title: user.name.title,
          firstname: user.name.first,
          image: user.picture.large
        });
      })
      .catch(error => console.error('Error fetching user:', error));
  }

  render() {
    const { title, firstname, image } = this.state;
    return (
      <div style={{ padding: '20px' }}>
        <h2>User Details</h2>
        <p><strong>Title:</strong> {title}</p>
        <p><strong>First Name:</strong> {firstname}</p>
        {image && <img src={image} alt={firstname} style={{ width: '150px', height: '150px' }} />}
      </div>
    );
  }
}

export default Getuser;
