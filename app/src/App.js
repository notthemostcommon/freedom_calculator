import React, { Component } from 'react';
import './App.css';
import Login from './components/Authentication/Login';
import { BrowserRouter as Router } from 'react-router-dom';
import routes from './routes/Routes';
import {UserProvider} from './globalStore/UserContext';

const App = () => {

    return (
      <div className="App">
      <UserProvider>
        <Router>
          
            <h1> Hello to my app! </h1>
            
            
            {routes}
        </Router>

        
      </UserProvider>
      </div>
    );
  }


export default App;
