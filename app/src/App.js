import React, { useEffect } from 'react';
import { userStore } from './globalStore/UserContext';
import './App.css';
import Cookies from 'universal-cookie'; 
import routes from './routes/Routes';

const cookies = new Cookies(); 
export const ACCESS_TOKEN = cookies.get("accessToken"); 

const App = () => {
  const { state, dispatch } = userStore(); 
  const createRoutes = routes();

  useEffect(() => {
    (cookies.get("accessToken") && state.user.isAuth == null) && 
    dispatch({type:"login", payload:"already logged in"})
}, []);

   return (
      <div className="App">
            
          {createRoutes}
          
      </div>
    );
  }


export default App;
