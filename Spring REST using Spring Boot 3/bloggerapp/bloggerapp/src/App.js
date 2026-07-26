import React, { Component } from 'react';
import './App.css';

function BookDetails() {
  return (
    <div>
      <h3>Book Details</h3>
      <p><strong>Title:</strong> Learning React</p>
      <p><strong>Author:</strong> Alex Banks</p>
      <p><strong>Price:</strong> Rs 500</p>
    </div>
  );
}

function BlogDetails() {
  return (
    <div>
      <h3>Blog Details</h3>
      <p><strong>Title:</strong> Introduction to React Hooks</p>
      <p><strong>Author:</strong> Sarah Johnson</p>
      <p><strong>Date:</strong> 15-Mar-2024</p>
    </div>
  );
}

function CourseDetails() {
  return (
    <div>
      <h3>Course Details</h3>
      <p><strong>Course:</strong> Full Stack Development</p>
      <p><strong>Duration:</strong> 3 Months</p>
      <p><strong>Fee:</strong> Rs 15000</p>
    </div>
  );
}

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { flag: 1 };
  }

  render() {
    const { flag } = this.state;

    // Approach 1: if/else
    let componentByIfElse;
    if (flag === 1) {
      componentByIfElse = <BookDetails />;
    } else if (flag === 2) {
      componentByIfElse = <BlogDetails />;
    } else {
      componentByIfElse = <CourseDetails />;
    }

    // Approach 2: Element variable
    let elementVariable = null;
    if (flag === 1) {
      elementVariable = <BookDetails />;
    } else if (flag === 2) {
      elementVariable = <BlogDetails />;
    } else {
      elementVariable = <CourseDetails />;
    }

    return (
      <div style={{ padding: '20px' }}>
        <h1>Blogger App - Conditional Rendering</h1>

        <div>
          <button onClick={() => this.setState({ flag: 1 })}>Book</button>
          <button onClick={() => this.setState({ flag: 2 })}>Blog</button>
          <button onClick={() => this.setState({ flag: 3 })}>Course</button>
        </div>

        <hr />
        <h2>Approach 1: if/else</h2>
        {componentByIfElse}

        <hr />
        <h2>Approach 2: Element Variable</h2>
        {elementVariable}

        <hr />
        <h2>Approach 3: Ternary Operator</h2>
        {flag === 1 ? <BookDetails /> : flag === 2 ? <BlogDetails /> : <CourseDetails />}

        <hr />
        <h2>Approach 4: Short-circuit (&&)</h2>
        {flag === 1 && <BookDetails />}
        {flag === 2 && <BlogDetails />}
        {flag === 3 && <CourseDetails />}
      </div>
    );
  }
}

export default App;
