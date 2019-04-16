import React, { useEffect } from 'react'
import UserContext, { userStore } from '../globalStore/UserContext';

const Home = () => {
    const { state } = userStore(); 
    console.log(state.user.accessToken);
        
    return (
        <>
        <h3>{state.user.accessToken ? `user is logged in with ${state.user.accessToken}` : `you are not logged in with ${state.user.accessToken}`}
              </h3>
    </>
    )
}

export default Home; 
