import React from 'react'; 
import { Form, Input, Button } from 'reactstrap'; 
import useForm from './hooks/useForm';
import client from '../util/APIUtils';
import { PAYMENT_URL } from '../constants';



const AddPayment = () => {
    
    const request = client(); 
    const { values, handleChange, handleSubmit } = useForm(submitPayment); 
    
    async function submitPayment () {
        await request.put(PAYMENT_URL, values); 
    }
    return ( 
        <Form onSubmit={handleSubmit} >
            <Input 
            type="text"
            name="additionalPayment"
            id="additionalPayment"
            value={values.additionalPayment || ""}
            onChange={handleChange}
            placeholder="Additional Payment"
            required/>
            <Button color="primary" type="submit">Save</Button>
        </Form>
     );
}
 
export default AddPayment;