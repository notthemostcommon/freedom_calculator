import React, { createContext, useReducer, useContext } from 'react';
import { userInitialState, userActions } from './userActions';
import Cookies  from 'universal-cookie';
const cookies = new Cookies(); 


const UserContext = createContext(); 

const Actions = {...userActions}; 

const reducer = (state, action) => {
    console.log("reducer action:" , action);
    
    const act = Actions[action.type]; 
    const update = act(state); 
    console.log("reducer act and update" , act, update.user);
    
    return {...state, ...update}
}

const initialState = {
   ...userInitialState
};
export const UserProvider = ({ children }) => {
    const [state, dispatch] = useReducer(reducer, initialState); 
    
    return (
        <UserContext.Provider  value={{ state, dispatch }} >  
            {children}      
        </UserContext.Provider>
    ); 
}; 

export const userStore = store => {
    const {state, dispatch } = useContext(UserContext); 
    return {state, dispatch}; 
} 