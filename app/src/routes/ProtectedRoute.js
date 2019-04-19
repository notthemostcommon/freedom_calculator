import React, { useEffect } from 'react'
import { Route, Redirect } from 'react-router-dom'
import { userStore } from '../globalStore/UserContext';
import { Spinner } from 'reactstrap';


const ProtectedRoute = ({component: Component, ...rest }) => {
    const { state } = userStore(); 
    return (
        // state.user.isAuth ? 
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