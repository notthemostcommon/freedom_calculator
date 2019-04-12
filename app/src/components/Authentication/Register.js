import React from 'react'
import {
    Form, 
    FormGroup, 
    Label, 
    Container,
    Input, 
    Button
} from 'reactstrap'; 
import { withRouter } from 'react-router-dom'; 
import useForm from '../hooks/useForm';
import axios from 'axios';
import UserContext, { userStore } from '../../globalStore/UserContext';


const Register = (props) =>  {

    const { values, handleChange, handleSubmit } = useForm(register);  

    async function register (){
        await fetch('/users/register', {
            method: 'post', 
            headers: {
                'Accept':'application/json', 
                'Content-Type':'application/json'
            }, 
            body: JSON.stringify(values),
        }) 
        .then(res => {
            props.history.push("/login"); 
        }).catch(err => {
            console.log("error ", err); 
        }); 

    }
        return (
        <Container>
            <Form onSubmit={handleSubmit} >
                <FormGroup>
                    <Label for="firstName">First Name</Label>
                        <Input 
                        type="text" 
                        name="firstName" 
                        id="firstName" 
                        value={values.firstName || ''}
                        onChange={handleChange} 
                        required />
                </FormGroup>

                <FormGroup>
                    <Label for="lastName">Last Name</Label>
                        <Input 
                        type="text" 
                        name="lastName" 
                        id="lastName" 
                        value={values.lastName || ''}
                        onChange={handleChange} 
                        required/>
                </FormGroup>
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
                    <Button color="primary" type="submit">Save</Button>{' '}
                    <Button color="secondary">Cancel</Button>
                </FormGroup>
            </Form>
        </Container>
        )
    }


export default withRouter(Register); 