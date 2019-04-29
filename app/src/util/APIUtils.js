import axios from 'axios';
import { userStore } from '../globalStore/UserContext';

const client = () => {
    const {state} = userStore(); 
    const token = state.user.accessToken; 
    const defaultOptions = {
        headers: {
            "Authorization": `${token}`,
            'Accept': 'application/json',
            'Content-Type': 'application/json', 
        },
    };

    return {
        get: (url, options = {}) => axios.get(url, { ...defaultOptions, ...options }),
        post: (url, data, options = {}) => axios.post(url, data, { ...defaultOptions, ...options }),
        put: (url, data, options = {}) => axios.put(url, data, { ...defaultOptions, ...options }),
        delete: (url, options = {}) => axios.delete(url, { ...defaultOptions, ...options }),
    };
};

export default client; 