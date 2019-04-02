import React from 'react'

import {
    Route,
    Switch
  } from 'react-router-dom';
import Register from './components/Authentication/Register';
import Login from './components/Authentication/Login';
import App from './App';

  const routes = (
      <Switch>
        <Route exact path="/" component ={App}/>
        <Route path="/register" component={Register}/>
        <Route path="/login" component={Login}/>
      </Switch>
  )

  export default routes; 