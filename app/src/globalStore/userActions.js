import Cookies  from 'universal-cookie';
const cookies = new Cookies(); 


export const userInitialState = {
    user: {
        accessToken: "",
        isAuth: null
    }
}; 

const getToken = () => {    
    return cookies.get("accessToken"); 
}

export const userActions = {
    login: state => ({
        ...state, 
        user: {accessToken: getToken(), isAuth: true }
    }),

    logout: state => ({
        ...state, 
        user: {accessToken: "", isAuth: false }
    }),
}