import { useState } from "react";

const useForm = (callback) => {
    const [values, setValues] = useState({}); 
    
    const handleSubmit = (e) => {
    if (e) e.preventDefault(); 
    console.log(values);
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
