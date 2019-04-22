import React, { useEffect } from 'react';
import { userStore } from './globalStore/UserContext';
import './App.css';
import Cookies from 'universal-cookie'; 
import routes from './routes/Routes';

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
