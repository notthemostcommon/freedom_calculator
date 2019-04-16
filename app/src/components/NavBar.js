import React, { useState } from 'react';
import { Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, NavLink } from 'reactstrap';
import UserContext, { userStore } from '../globalStore/UserContext';


const NavBar = () => {
    const [collapsed, setcollapsed] = useState(true); 
    const { state } = userStore(); 


    const toggleNavbar = () => {
        setcollapsed(!collapsed); 
    }; 

    return (
        <Navbar color="faded" light>
          <NavbarBrand href="/" className="mr-auto">Freedom Calculator</NavbarBrand>
          <NavbarToggler onClick={toggleNavbar} className="mr-2" />
          <Collapse isOpen={!collapsed} navbar>
          <Nav navbar>
          {state.user.accessToken ? 
              <div>
              <NavItem>
                <NavLink href="/logout/">Logout</NavLink>
              </NavItem>
              <NavItem>
                <NavLink href="/dashboard/">Dashboard</NavLink>
              </NavItem>
              </div>
               : 
               <div>

                <NavItem>
                <NavLink href="/login/">Login</NavLink>
              </NavItem>
              <NavItem>
              <NavLink href="/register/">Register</NavLink>
            </NavItem>
               </div>

          
        }
              <NavItem>
                <NavLink href="https://github.com/reactstrap/reactstrap">GitHub</NavLink>
              </NavItem>
            </Nav>
          </Collapse>
        </Navbar>


    )
}

export default NavBar; 