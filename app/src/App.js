import React, { Component } from 'react';
import './App.css';
import Logout from './components/Authentication/Logout';


class App extends Component {
  render() {
    return (
      <div className="App">
      <Logout/> 
        <h1> Hello to my app! </h1>
      </div>
    );
  }
}

export default App;
