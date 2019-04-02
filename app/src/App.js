import React, { Component } from 'react';
import './App.css';
import { 
  Route, 
  withRouter, 
  Switch
} from 'react-router-dom'; 
import Register from './components/Register';


class App extends Component {
  render() {
    return (
      <div className="App">
        <Register/>
      </div>
    );
  }
}

export default App;
