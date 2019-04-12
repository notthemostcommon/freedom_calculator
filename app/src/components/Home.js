import React, { useEffect } from 'react'
import UserContext, { userStore } from '../globalStore/UserContext';

const Home = () => {
    const { state, dispatch } = userStore(); 
    console.log("home", state.user); 
    
    return (
        <>
        <h3>{state.user.accessToken != "" ? state.user.accessToken : "you are not logged in"}
              </h3>
    </>
    )
}

export default Home; 
