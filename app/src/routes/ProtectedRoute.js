import React from 'react'
import { Route, Redirect } from 'react-router-dom'
import { userStore } from '../globalStore/UserContext';


const ProtectedRoute = ({component: Component, ...rest }) => {
    const { state } = userStore(); 
    return (
        <Route 
            render = {props => 
            state.user.isAuth ? 
            <Component {...props} /> : 
            <Redirect to="/login" />
        }
        {...rest}
        /> 
    )

}
export default ProtectedRoute; 