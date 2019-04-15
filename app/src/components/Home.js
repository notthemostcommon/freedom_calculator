import React, { useEffect } from 'react'
import UserContext, { userStore } from '../globalStore/UserContext';

const Home = () => {
    const { state } = userStore();     
    return (
        <>
        <h3>{state.user.accessToken != "" ? "user is logged in" : "you are not logged in"}
              </h3>
    </>
    )
}

export default Home; 
