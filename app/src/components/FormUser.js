import React, { Component } from 'react'
import {
    InputGroup, 
    InputGroupAddon, 
    InputGroupText, 
    Form, 
    FormGroup, 
    Label, 
    Container,
    Input, 
    Button
} from 'reactstrap'; 

class FormUser extends Component {

    
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
        lastName: ''
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

        await fetch('/users', {
            method: 'post', 
            headers: {
                'Accept': 'application/json', 
                'Content-Type': 'application/json'
            }, 
            body: JSON.stringify(item),
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
                    <Button color="primary" type="submit">Save</Button>{' '}
                    <Button color="secondary">Cancel</Button>
                </FormGroup>
            </Form>
        </Container>
        )}
}

export default FormUser; 