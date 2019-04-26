import React from 'react'

import { Table } from 'reactstrap';
import {userStore} from '../../globalStore/UserContext'; 


const CreditorList = (props) => {

  const { state } = userStore(); 


    
console.log("props", state.debts);

    // const displayData = props.debts.map( (data, index) =>
    //     <tr key={index}>
    //         <th scope="row">{index + 1}</th>
    //         <td>{data.debtName}</td>
    //         <td>{data.balance}</td>
    //         <td>{data.apr}%</td>
    //         <td>{data.creditLimit}</td>
    //         <td>{data.minPayment}</td>
    //       </tr>
    // )

    const displayData = state.debts.map( (data, index) =>
        <tr key={index}>
            <th scope="row">{index + 1}</th>
            <td>{data.debtName}</td>
            <td>{data.balance}</td>
            <td>{data.apr}%</td>
            <td>{data.creditLimit}</td>
            <td>{data.minPayment}</td>
          </tr>
    )

    return (
        <Table striped>
        <thead>
          <tr>
            <th></th>
            <th>Creditor</th>
            <th>Balance</th>
            <th>Rate</th>
            <th>Credit Limit</th>
            <th>Minimum Payment</th>
          </tr>
        </thead>
        <tbody>
          {displayData}
          
          </tbody>
          </Table>
       
    )


}

export default CreditorList;  