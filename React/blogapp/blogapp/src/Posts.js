import React, { Component } from 'react';
import Post from './Post';

class Posts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      error: null
    };
  }

  loadPosts() {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch posts');
        }
        return response.json();
      })
      .then(data => {
        // Instantiate Post class for each post
        const postList = data.map(p => new Post(p.id, p.title, p.body));
        this.setState({ posts: postList });
      })
      .catch(error => {
        this.setState({ error: error.message });
      });
  }

  componentDidMount() {
    this.loadPosts();
  }

  componentDidCatch(error, info) {
    alert("An error occurred: " + error.toString());
  }

  render() {
    if (this.state.error) {
      return (
        <div style={{ color: 'red', padding: '20px' }}>
          <h4>Error: {this.state.error}</h4>
        </div>
      );
    }

    return (
      <div style={{ padding: '20px', maxWidth: '800px', margin: '0 auto' }}>
        <h2 style={{ borderBottom: '2px solid #333', paddingBottom: '10px' }}>Blog Posts</h2>
        {this.state.posts.map(post => (
          <div key={post.id} style={{ margin: '20px 0', borderBottom: '1px solid #ddd', paddingBottom: '15px' }}>
            <h3 style={{ color: '#2c3e50', textTransform: 'capitalize' }}>{post.title}</h3>
            <p style={{ color: '#555', lineHeight: '1.6' }}>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;
