import React, { Component } from 'react'
import Cookies from 'universal-cookie'; 
import { Button } from 'reactstrap'; 
import { withRouter } from 'react-router-dom'; 

const cookies = new Cookies(); 
class Logout extends Component {

handleLogout = () => {
    cookies.remove('accessToken');
    console.log(cookies.getAll());  
    this.props.history.push('/'); 
    window.location.reload();
}
  render() {
    return (
      <>
        <Button color="primary" onClick={()=>this.handleLogout()}>Sign Out</Button>
      </>
    )
  }
}

export default withRouter(Logout); 
