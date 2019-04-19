import { useState } from "react";
//https://upmostly.com/tutorials/using-custom-react-hooks-simplify-forms/
const useForm = (callback) => {
    const [values, setValues] = useState({}); 
    
    const handleSubmit = (e) => {
    if (e) e.preventDefault(); 
    callback(); 
}; 

    const handleChange = (e) => {
        e.persist();         
        setValues(values => ({ ...values, [e.target.name]: e.target.value}));  
    }; 
    return {
        handleChange, 
        handleSubmit, 
        values, 
    }
}; 

export default useForm; 
