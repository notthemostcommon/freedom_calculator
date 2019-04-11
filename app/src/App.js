import React, { Component } from 'react';
import './App.css';
import Login from './components/Authentication/Login';
import { BrowserRouter as Router } from 'react-router-dom';
import routes from './Routes';
import {UserProvider} from './globalStore/UserContext';
// const initialState = useContext(UserContext);
// const [{ accessToken }, dispatch] = useReducer(
// UserReducer,
// initialState
// );
class App extends Component {
  render() {
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
}

export default App;
