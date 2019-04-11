import React from 'react'

import {
    Route,
    Switch
  } from 'react-router-dom';
import Register from './components/Authentication/Register';
import Login from './components/Authentication/Login';
import Logout from './components/Authentication/Logout';
import App from './App';
import AddCreditor from './components/creditors/AddCreditor';

  const routes = (
      <Switch>
        <Route exact path="/" component ={App}/>
        <Route path="/register" component={Register}/>
        <Route path="/login" component={Login}/>
        <Route path="/logout" component={Logout}/>
        {/* <Route path="/creditors" component={CreditorLists}/> */}
        <Route path="/creditors/new" component={AddCreditor}/>
          
      </Switch>
  )

  export default routes; 