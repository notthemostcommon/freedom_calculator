import axios from 'axios';
import { ACCESS_TOKEN } from '../constants';

const client = (token = null) => {
    const defaultOptions = {
        headers: {
            "Authorization": token ? `${token}` : '',
            'Accept': 'application/json',
            'Content-Type': 'application/json', 
        },
    };
    console.log(defaultOptions.headers)

    return {
        get: (url, options = {}) => axios.get(url, { ...defaultOptions, ...options }),
        post: (url, data, options = {}) => axios.post(url, data, { ...defaultOptions, ...options }),
        put: (url, data, options = {}) => axios.put(url, data, { ...defaultOptions, ...options }),
        delete: (url, options = {}) => axios.delete(url, { ...defaultOptions, ...options }),
    };
};

export const request = client(ACCESS_TOKEN);