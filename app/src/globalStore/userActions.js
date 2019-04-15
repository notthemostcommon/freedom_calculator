import Cookies  from 'universal-cookie';
const cookies = new Cookies(); 


export const userInitialState = {
    user: {
        accessToken: "",
    }
}; 

const getToken = () => {    
    return cookies.get("accessToken"); 
}

export const userActions = {
    login: state => ({
        ...state, 
        user: {accessToken: getToken() }
    }),

    logout: state => ({
        ...state, 
        user: {accessToken: "" }
    }),
}