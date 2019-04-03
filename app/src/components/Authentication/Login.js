import React, { Component } from 'react'; 
import Cookies from 'universal-cookie'; 
import {
    Form, 
    FormGroup, 
    Label, 
    Container,
    Input, 
    Button
} from 'reactstrap'; 
import { ACCESS_TOKEN } from '../../constants';
const cookies = new Cookies(); 

class Login extends Component {

    
    constructor(props) {
        super(props); 
        this.state = {
            item : this.emptyItem
        }
        
        this.handleChange = this.handleChange.bind(this); 
        this.handleSubmit = this.handleSubmit.bind(this); 
        
    }
    
    emptyItem = {
        username: '', 
        password: ''
    }

    handleChange = (e) => {
        const target = e.target; 
        const value = target.value; 
        const name = target.name; 
        let item = {...this.state.item}; 
        item[name] = value; 

        this.setState({item}); 
    }

    async handleSubmit(e) {
        e.preventDefault(); 
        const {item} = this.state; 
        console.log(item)

        await fetch('/login', {
            method: 'post', 
            headers: {
                'Accept':'application/json', 
                'Content-Type':'application/json'
            }, 
            body: JSON.stringify(item),
        }) 
        .then(res => {
            let token = res.headers.get('authorization'); 
            cookies.set("accessToken", token, {path: '/'}); 
            console.log("token ", cookies.get('accessToken') ); 
        }).catch(err => {
            console.log("error ", err); 
        }); 

    }

    render() {
        const {item} = this.state; 
        return (
        <Container>
            <Form onSubmit={this.handleSubmit} >
                <FormGroup>
                    <Label for="username">Email Address</Label>
                        <Input type="text" name="username" id="username" value={item.username || ''}
                        onChange={this.handleChange} autoComplete="username" />
                </FormGroup>
                <FormGroup>
                    <Label for="password">Password</Label>
                        <Input type="text" name="password" id="password" value={item.password || ''}
                        onChange={this.handleChange} autoComplete="off" />
                </FormGroup>
                <FormGroup>
                    <Button color="primary" type="submit">Login</Button>{' '}
                    <Button color="secondary">Cancel</Button>
                </FormGroup>
            </Form>
        </Container>
        )}
}

export default Login; 