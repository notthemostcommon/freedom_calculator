import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import Cookies from 'universal-cookie';

const cookies = new Cookies(); 

class AddCreditor extends Component {

  emptyItem = {
    creditorName: '',
    rate: '',
    balance: '',
    limit: '',
    minPayment: '',
  };


  constructor(props) {
    super(props);
    this.state = {
      item: this.emptyItem
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

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = value;
    this.setState({item});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;
    let jsonToken = JSON.stringify(cookies.get('accessToken')); 
    console.log("item " , item, jsonToken); 
    await fetch('/api/creditor{', {
    //   method: (item.id) ? 'PUT' : 'POST',
      method: 'post',         
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json', 
        'Authorization': jsonToken, 
      },
      body: JSON.stringify(item),
    })
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
            <Label for="creditorName">Creditor Name</Label>
            <Input type="text" name="creditorName" id="creditorName" value={item.creditorName || ''}
                   onChange={this.handleChange} autoComplete="creditorName"/>
          </FormGroup>
          <FormGroup>
            <Label for="rate">Rate</Label>
            <Input type="text" name="rate" id="rate" value={item.rate || ''}
                   onChange={this.handleChange} autoComplete="rate"/>
          </FormGroup>
          <FormGroup>
            <Label for="balance">Balance</Label>
            <Input type="text" name="balance" id="balance" value={item.balance || ''}
                   onChange={this.handleChange} autoComplete="address-level1"/>
          </FormGroup>
          <div className="row">
            <FormGroup className="col-md-4 mb-3">
              <Label for="limit">Credit Card Limit</Label>
              <Input type="text" name="limit" id="limit" value={item.limit || ''}
                     onChange={this.handleChange} autoComplete="limit"/>
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