import React, { useEffect, useState } from 'react'
import { request } from '../../util/APIUtils';
import { DEBT_LIST_URL } from '../../constants/index';
import { Table } from 'reactstrap';


const CreditorList = () => {
    const [data, setData] = useState([]); 
    
    useEffect(() => {
    
        const  fetchData = async() => {
            const response = request.get(DEBT_LIST_URL)
                .then(res => {
                    setData(res.data)        
                })   
            }; 
            fetchData()            
        },[]);         

    const displayData = data.map( (data, index) =>
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