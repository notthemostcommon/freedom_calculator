import React, { useEffect } from 'react'
import { Route, Redirect } from 'react-router-dom'
import { userStore } from '../globalStore/UserContext';
import { Spinner } from 'reactstrap';


const ProtectedRoute = ({component: Component, ...rest }) => {
    const { state } = userStore(); 
    console.log("protected route ", state.user);
    useEffect(() => {
        console.log("protected route effect ", state.user);
        
      
    }, [])
    return (
        state.user.isAuth ? 
        <Route 
        render = {props => 
            state.user.isAuth ? 
            <Component {...props} /> : 
            <Redirect to="/login" />
        }
        {...rest}
        /> : <Spinner color="dark" />


    )

}
export default ProtectedRoute; 