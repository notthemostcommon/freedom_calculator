import React from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import useForm from '../hooks/useForm';
import client  from '../../util/APIUtils';
import { DEBT_LIST_URL } from '../../constants/index';


const AddCreditor = (props) =>  {
  const { values, handleChange, handleSubmit } = useForm(addAccount);  
  const request = client(); 
  async function addAccount() {
    await request.post(DEBT_LIST_URL, values )
    .then(res => {
        props.history.push('/dashboard');
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