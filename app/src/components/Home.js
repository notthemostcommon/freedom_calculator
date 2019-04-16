import React, { useEffect } from 'react'
import UserContext, { userStore } from '../globalStore/UserContext';
import Cookies from 'universal-cookie'; 

const cookies = new Cookies(); 

const Home = () => {
    const { state, dispatch } = userStore(); 

    console.log("home", state.user);
    
    return (
        <>
        <h3>{state.user.accessToken ? `user is logged in with ${state.user.accessToken}` : `you are not logged in with ${state.user.accessToken}`}
              </h3>
    </>
    )
}

export default Home; 
