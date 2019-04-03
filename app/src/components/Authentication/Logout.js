import React, { Component } from 'react'
import { ACCESS_TOKEN } from '../../constants';

export default class Logout extends Component {

handleLogout = () => {
    redirectTo="/"; 
    localStorage.removeItem(ACCESS_TOKEN); 
}
  render() {
    return (
      <div>
        
      </div>
    )
  }
}
