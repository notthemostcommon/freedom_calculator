import React from 'react'

import {
    Route,
    Switch
  } from 'react-router-dom';
import Register from '../components/Authentication/Register';
import Login from '../components/Authentication/Login';
import Logout from '../components/Authentication/Logout';
import AddCreditor from '../components/creditors/AddCreditor';
import Home from '../components/Home';
import CreditorList from '../components/creditors/CreditorList';

  const routes = (
      <Switch>
        <Route exact path="/" component ={Home}/>
        <Route path="/register" component={Register}/>
        <Route path="/login" component={Login}/>
        <Route path="/logout" component={Logout}/>
        <Route path="/creditors/new" component={AddCreditor}/>
        <Route path="/creditors" component={CreditorList}/>
          
      </Switch>
  )

  export default routes; 