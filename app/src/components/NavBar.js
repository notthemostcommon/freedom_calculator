import React, { useState } from 'react';
import { Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem } from 'reactstrap';
import { userStore } from '../globalStore/UserContext';
import { Link } from 'react-router-dom'



const NavBar = () => {
    const [collapsed, setcollapsed] = useState(true); 
    const { state } = userStore(); 

    const toggleNavbar = () => {
        setcollapsed(!collapsed); 
    };     

    return (
        <Navbar color="faded" light>
          <NavbarBrand to="/" className="mr-auto">Freedom Calculator</NavbarBrand>
          <NavbarToggler onClick={toggleNavbar} className="mr-2" />
          <Collapse isOpen={!collapsed} navbar>
          <Nav navbar onClick={toggleNavbar}>
            <NavItem >
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