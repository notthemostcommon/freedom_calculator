import React, { Component } from 'react'
import {
    Form, 
    FormGroup, 
    Label, 
    Container,
    Input, 
    Button
} from 'reactstrap'; 
import { withRouter } from 'react-router-dom'; 

class Register extends Component {

    
    constructor(props) {
        super(props); 
        this.state = {
            item : this.emptyItem
        }
        
        this.handleChange = this.handleChange.bind(this); 
        this.handleSubmit = this.handleSubmit.bind(this); 
        
    }
    
    emptyItem = {
        firstName: '', 
        lastName: '', 
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

        await fetch('/users/register', {
            method: 'post', 
            headers: {
                'Accept':'application/json', 
                'Content-Type':'application/json'
            }, 
            body: JSON.stringify(item),
        }) 
        .then(res => {
            console.log("success " , res); 
            this.props.history.push("/login"); 
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
                    <Label for="firstName">First Name</Label>
                        <Input type="text" name="firstName" id="firstName" value={item.firstName || ''}
                        onChange={this.handleChange} autoComplete="firstName" />
                </FormGroup>

                <FormGroup>
                    <Label for="lastName">Last Name</Label>
                        <Input type="text" name="lastName" id="lastName" value={item.lastName || ''}
                        onChange={this.handleChange} autoComplete="lastName" />
                </FormGroup>
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
                    <Button color="primary" type="submit">Save</Button>{' '}
                    <Button color="secondary">Cancel</Button>
                </FormGroup>
            </Form>
        </Container>
        )}
}

export default withRouter(Register); 