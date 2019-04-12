import React, { useEffect } from 'react';
import UserContext, { userStore } from './globalStore/UserContext';

import './App.css';
import Login from './components/Authentication/Login';

const App = () => {
  const { state, dispatch } = userStore(); 

  useEffect(() => {
    dispatch({type:"login"})
}, [])

   return (
      <div className="App">
        <h1> Hello to my app! </h1>
      </div>
    );
  }


export default App;
