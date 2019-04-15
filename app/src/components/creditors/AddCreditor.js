import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import Cookies from 'universal-cookie';
import axios from 'axios';
import useForm from '../hooks/useForm';
import { userStore } from '../../globalStore/UserContext';

const cookies = new Cookies(); 

const AddCreditor = (props) =>  {
  const { values, handleChange, handleSubmit } = useForm(addAccount);  
  const { state } = userStore(); 

  const headers = {
    'Accept': 'application/json',
    'Content-Type': 'application/json', 
    'Authorization': cookies.get("accessToken"), 
  }  
  
  async function addAccount() {
    await axios.post('/debts', values, {headers: headers})
    .then(res => {
        props.history.push('/creditors');
    })
    .catch(err => {
        console.log("error " , err); 
    })
  }
    return <div>
      
      <Container>
        <Form onSubmit={handleSubmit}>
          <FormGroup>
            <Label for="debtName">Creditor Name</Label>
            <Input 
            type="text" 
            name="debtName" 
            id="debtName" 
            value={values.debtName || ''}
            onChange={handleChange} 
            required/>
          </FormGroup>
          <FormGroup>
            <Label for="apr">apr</Label>
            <Input 
            type="text" 
            name="apr" 
            id="apr" 
            value={values.apr || ''}
            onChange={handleChange}
            required/>
          </FormGroup>
          <FormGroup>
            <Label for="balance">Balance</Label>
            <Input 
            type="text" 
            name="balance" 
            id="balance" 
            value={values.balance || ''}
            onChange={handleChange} 
            required/>
          </FormGroup>
          <div className="row">
            <FormGroup className="col-md-4 mb-3">
              <Label for="creditLimit">Credit Card Limit</Label>
              <Input 
              type="text" 
              name="creditLimit" 
              id="limit" 
              value={values.creditLimit || ''}
              onChange={handleChange}
              required/>
            </FormGroup>
            <FormGroup className="col-md-5 mb-3">
              <Label for="minPayment">Minimum Payment</Label>
              <Input 
              type="text" 
              name="minPayment" 
              id="minPayment" 
              value={values.minPayment || ''}
              onChange={handleChange} 
              required/>
            </FormGroup>
          </div>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/groups">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }


export default withRouter(AddCreditor);