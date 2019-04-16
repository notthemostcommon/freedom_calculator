import React from 'react'
import Cookies from 'universal-cookie'; 
import { Button } from 'reactstrap'; 
import { withRouter } from 'react-router-dom'; 
import { userStore } from '../../globalStore/UserContext';

const cookies = new Cookies(); 
const Logout = (props) => {

  const { state, dispatch } = userStore(); 
  console.log("logout ", state.user);

  const handleLogout = () => {
    cookies.remove("accessToken", {path: "/"});
    dispatch({type: "logout"}); 
    console.log(cookies.getAll()); 
    props.history.push('/'); 
    window.location.reload();
}
 
    return (
      <>
        <Button color="primary" onClick={()=>handleLogout()}>Sign Out</Button>
      </>
    )
  }


export default withRouter(Logout); 
