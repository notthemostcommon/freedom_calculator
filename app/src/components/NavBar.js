import React, { useState } from 'react';
import { Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, NavLink } from 'reactstrap';
import UserContext, { userStore } from '../globalStore/UserContext';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom'



const NavBar = () => {
    const [collapsed, setcollapsed] = useState(true); 
    const { state } = userStore(); 


    const toggleNavbar = () => {
        setcollapsed(!collapsed); 
    }; 
    console.log("navbar", state.user);
    

    return (
        <Navbar color="faded" light>
          <NavbarBrand to="/" className="mr-auto">Freedom Calculator</NavbarBrand>
          <NavbarToggler onClick={toggleNavbar} className="mr-2" />
          <Collapse isOpen={!collapsed} navbar>
          <Nav navbar>
          <NavItem>
                <Link to="/">Home</Link>
              </NavItem>
          {state.user.accessToken ? 
              <div>
              <NavItem>
                <Link to="/logout/">Logout</Link>
              </NavItem>
              <NavItem>
                <Link to="/dashboard/">Dashboard</Link>
              </NavItem>
              </div>
               : 
               <div>

                <NavItem>
                <Link to="/login/">Login</Link>
              </NavItem>
              <NavItem>
              <Link to="/register/">Register</Link>
            </NavItem>
               </div>

          
        }
              <NavItem>
                <Link to="https://github.com/reactstrap/reactstrap">GitHub</Link>
              </NavItem>
            </Nav>
          </Collapse>
        </Navbar>


    )
}

export default NavBar; 