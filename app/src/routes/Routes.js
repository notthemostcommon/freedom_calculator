import React from 'react'

import {
    BrowserRouter as Router,
    Route,
    Switch
  } from 'react-router-dom';
import Register from '../components/Authentication/Register';
import Login from '../components/Authentication/Login';
import Logout from '../components/Authentication/Logout';
import AddCreditor from '../components/creditors/AddCreditor';
import Home from '../components/Home';
import CreditorList from '../components/creditors/CreditorList';
import Dashboard from '../containers/Dashboard';
import ProtectedRoute from './ProtectedRoute';
import NavBar from '../components/NavBar';


  const Routes = () => {
    return (
      <Router>
        <NavBar/>
          <Switch> 
            <Route exact path="/" component ={Home}/>
            <Route path="/register" component={Register}/>
            <Route path="/login" component={Login}/>
            <ProtectedRoute path="/dashboard" component={Dashboard}/>
            <ProtectedRoute path="/logout" component={Logout}/>
            <ProtectedRoute path="/creditors/new" component={AddCreditor}/>
            <ProtectedRoute path="/creditors" component={CreditorList}/>
            {/* <Route path="/dashboard" component={Dashboard}/> */}
              
          </Switch>
        </Router>
      )
    }

  export default Routes; 