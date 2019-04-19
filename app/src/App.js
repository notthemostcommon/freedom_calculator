import React, { useEffect } from 'react';
import UserContext, { userStore, UserProvider } from './globalStore/UserContext';
import NavBar from './components/NavBar'; 
import './App.css';
import Cookies from 'universal-cookie'; 
import CreateRoutes from './routes/Routes';
import routes from './routes/Routes';
import {
  BrowserRouter as Router,
  Route,
  Switch
} from 'react-router-dom';
import Register from './components/Authentication/Register';
import Login from './components/Authentication/Login';
import Logout from './components/Authentication/Logout';
import AddCreditor from './components/creditors/AddCreditor';
import Home from './components/Home';
import CreditorList from './components/creditors/CreditorList';
import Dashboard from './containers/Dashboard';
import ProtectedRoute from './routes/ProtectedRoute';

const cookies = new Cookies(); 

const App = () => {
  const { state, dispatch } = userStore(); 
  const createRoutes = routes();

  useEffect(() => {
    (cookies.get("accessToken") && state.user.isAuth == null) && 
    dispatch({type:"login"})
}, []);

   return (
      <div className="App">
            <h1> Hello to my app! </h1>
          {createRoutes}
          
      </div>
    );
  }


export default App;
