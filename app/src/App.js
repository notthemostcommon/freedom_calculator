import React, { Component } from 'react';
import './App.css';
import Register from './components/Authentication/Register';
import { 
  Route, 
  withRouter, 
  Switch
} from 'react-router-dom'; 


class App extends Component {
  render() {
    return (
      <div className="App">
        <h1> Hello to my app! </h1>/>
      </div>
    );
  }
}

export default App;
