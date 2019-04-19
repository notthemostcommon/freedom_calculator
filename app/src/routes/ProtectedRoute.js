import React, { useEffect } from 'react'
import { Route, Redirect } from 'react-router-dom'
import { userStore } from '../globalStore/UserContext';
import { Spinner } from 'reactstrap';


const ProtectedRoute = ({component: Component, ...rest }) => {
    const { state } = userStore(); 
    console.log("protected route ", state.user);
    useEffect(() => {
        setTimeout(2000); 
        console.log("protected route effect ", state.user);
        
      
    }, []); 

    const launchSpinner = () => {
    
        let whatnext = state.user.isAuth ? 
        <Spinner color = "light"/> :
        < Redirect to="/"/>

        return whatnext; 

    }
  

    return (
        state.user.isAuth ? 
        <Route 
            render = {props => 
            state.user.isAuth ? 
            <Component {...props} /> : 
            <Redirect to="/login" />
        }
        {...rest}
        /> : setTimeout(launchSpinner(), 2000)


    )

}
export default ProtectedRoute; 