import React, { useCallback } from 'react'; 
import Cookies from 'universal-cookie'; 
import {
    Form, 
    FormGroup, 
    Label, 
    Container,
    Input, 
    Button
} from 'reactstrap'; 
import useForm from '../hooks/useForm';
import axios from 'axios';
import { userStore } from '../../globalStore/UserContext';

const cookies = new Cookies(); 

const Login = (props) =>  {
    
const { dispatch } = userStore(); 

const { values, handleChange, handleSubmit } = useForm(signIn);  

const setLogin = useCallback(() => dispatch({type: "login"}), [dispatch]); 

function signIn() {    
        axios.post("/login", values )
        .then(res => {        
        let accessToken = res.headers["authorization"]; 
        cookies.set("accessToken", accessToken, {path: "/"}) 
        setLogin();  
        props.history.push("/")

    }).catch(err => {
        console.log("error ", err);
    })            
}
return (
<Container>

    <Form onSubmit={handleSubmit} >
        <FormGroup>
            <Label for="username">Email Address</Label>
                <Input 
                type="email" 
                name="username" 
                id="username" 
                value={values.username || ''}
                onChange={handleChange} 
                required />
        </FormGroup>
        <FormGroup>
            <Label for="password">Password</Label>
                <Input 
                type="password" 
                name="password" 
                id="password" 
                value={values.password || ''}
                onChange={handleChange} 
                required />
        </FormGroup>
        <FormGroup>
            <Button color="primary" type="submit">Login</Button>{' '}
            <Button color="secondary">Cancel</Button>
        </FormGroup>
    </Form>
</Container>
)
}

export default Login; 