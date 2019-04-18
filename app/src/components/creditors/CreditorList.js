import React, { useEffect, useState } from 'react'

import { Table } from 'reactstrap';


const CreditorList = (props) => {
    

    const displayData = props.debts.map( (data, index) =>
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