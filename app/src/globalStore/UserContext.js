import React, { createContext, useReducer, useContext } from 'react';
import { userInitialState, userActions } from './userActions';


const UserContext = createContext(); 

const Actions = {...userActions}; 

const reducer = (state, action) => {
    console.log("reducer", state, action);
    
    const act = Actions[action.type]; 
    const update = act(state);     
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