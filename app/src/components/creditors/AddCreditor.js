import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import Cookies from 'universal-cookie';
import Axios from 'axios';


const cookies = new Cookies(); 

class AddCreditor extends Component {

  emptyItem = {
    debtName: '',
    apr: '',
    balance: '',
    creditLimit: '',
    minPayment: '',
  };


  constructor(props) {
    super(props);
    this.state = {
      item: this.emptyItem,
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

//   async componentDidMount() {
//     if (this.props.match.params.id !== 'new') {
//         console.log(this.props.match.params.id); 
//       const creditor = await (await fetch(`/api/creditor/${this.props.match.params.id}`)).json();
//       this.setState({item: creditor});
//     }
//   }

componentDidMount() {
  this.getAccessToken()
}

getAccessToken = () => {
  const token = cookies.get('accessToken'); 
  this.setState({token})
  console.log(token); 
  return token; 
  // const JSONToken = JSON.stringify(token);
  // console.log(JSONToken); 

}


  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = value;
    this.setState({item});
  }

  headers = {
    'Accept': 'application/json',
    'Content-Type': 'application/json', 
    'Authorization': this.getAccessToken(), 
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;
    await Axios.post('/debts', item, {headers: this.headers})
    .then(res => {
        console.log("response " , res);
        this.props.history.push('/creditors');
    })
    .catch(err => {
        console.log("error " , err); 
    })
  }

  render() {
    const {item} = this.state;
    const title = <h2>{item.id ? 'Edit Creditor' : 'Add Creditor'}</h2>;

    return <div>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="debtName">Creditor Name</Label>
            <Input type="text" name="debtName" id="debtName" value={item.debtName || ''}
                   onChange={this.handleChange} autoComplete="debtName"/>
          </FormGroup>
          <FormGroup>
            <Label for="apr">apr</Label>
            <Input type="text" name="apr" id="apr" value={item.apr || ''}
                   onChange={this.handleChange} autoComplete="apr"/>
          </FormGroup>
          <FormGroup>
            <Label for="balance">Balance</Label>
            <Input type="text" name="balance" id="balance" value={item.balance || ''}
                   onChange={this.handleChange} autoComplete="address-level1"/>
          </FormGroup>
          <div className="row">
            <FormGroup className="col-md-4 mb-3">
              <Label for="creditLimit">Credit Card Limit</Label>
              <Input type="text" name="creditLimit" id="limit" value={item.creditLimit || ''}
                     onChange={this.handleChange} autoComplete="creditLimit"/>
            </FormGroup>
            <FormGroup className="col-md-5 mb-3">
              <Label for="minPayment">Minimum Payment</Label>
              <Input type="text" name="minPayment" id="minPayment" value={item.minPayment || ''}
                     onChange={this.handleChange} autoComplete="minPayment"/>
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
}

export default withRouter(AddCreditor);