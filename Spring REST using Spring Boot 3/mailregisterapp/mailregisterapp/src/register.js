import React, { Component } from 'react';

class Register extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: '',
      email: '',
      password: '',
      nameError: '',
      emailError: '',
      passwordError: ''
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  }

  validate() {
    let nameError = '';
    let emailError = '';
    let passwordError = '';

    if (this.state.name.length < 5) {
      nameError = 'Name should have atleast 5 characters';
    }

    if (!this.state.email.includes('@') || !this.state.email.includes('.')) {
      emailError = 'Email should have @ and .';
    }

    if (this.state.password.length < 8) {
      passwordError = 'Password should have atleast 8 characters';
    }

    this.setState({ nameError, emailError, passwordError });
    return !nameError && !emailError && !passwordError;
  }

  handleSubmit(event) {
    event.preventDefault();
    if (this.validate()) {
      alert('Registration successful!');
      this.setState({ name: '', email: '', password: '' });
    }
  }

  render() {
    return (
      <div style={{ padding: '20px' }}>
        <h2>Register</h2>
        <form onSubmit={this.handleSubmit}>
          <div>
            <label>Name: </label>
            <input
              type="text"
              name="name"
              value={this.state.name}
              onChange={this.handleChange}
            />
            <span style={{ color: 'red' }}>{this.state.nameError}</span>
          </div>
          <br />
          <div>
            <label>Email: </label>
            <input
              type="text"
              name="email"
              value={this.state.email}
              onChange={this.handleChange}
            />
            <span style={{ color: 'red' }}>{this.state.emailError}</span>
          </div>
          <br />
          <div>
            <label>Password: </label>
            <input
              type="password"
              name="password"
              value={this.state.password}
              onChange={this.handleChange}
            />
            <span style={{ color: 'red' }}>{this.state.passwordError}</span>
          </div>
          <br />
          <button type="submit">Register</button>
        </form>
      </div>
    );
  }
}

export default Register;
