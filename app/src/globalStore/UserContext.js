import React, { createContext, useReducer, useContext } from 'react';
import { userInitialState, userActions } from './userActions';
import { debtInitialState, debtActions } from './debtActions';


const UserContext = createContext(); 

const Actions = {...userActions, ...debtActions}; 

const reducer = (state, action) => {
    console.log("reducer", state, action.payload);
    
    const act = Actions[action.type]; 
    const update = act(state, action);     
    return {...state, ...update}
}

const initialState = {
   ...userInitialState, 
   ...debtInitialState
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