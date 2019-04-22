import React from 'react'
import { userStore } from '../globalStore/UserContext';

const Home = () => {
    const { state } = userStore(); 
    
    return (
        <>
        <h3>{state.user.accessToken ? `user is logged in with ${state.user.accessToken}` : `you are not logged in with ${state.user.accessToken}`}
              </h3>
    </>
    )
}

export default Home; 
