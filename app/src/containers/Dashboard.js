import React, { useState, useEffect } from 'react'
import CreditorList from '../components/creditors/CreditorList';
import CreditorTotal from '../components/creditors/CreditorTotals';
import { request } from '../util/APIUtils';
import { DEBT_LIST_URL } from '../constants/index';
const Dashboard = () => {

    const [data, setData] = useState([]); 
    const [totals, setTotals] = useState({})
    console.log(totals);
    
    useEffect(() => {
    
        const  fetchData = async() => {
            const response = request.get(DEBT_LIST_URL)
                .then(res => {
                    setData(res.data.debts)
                    setTotals({"totalBalance": res.data.totalBalance, "totalMinPayment": res.data.totalMinPayment, "totalCreditLimit": res.data.totalCreditLimit})      
                })   
            }; 
            fetchData()            
        },[]);         

    return (
        <>
            <CreditorList debts={data}/>
            <CreditorTotal totals={totals}/>
        </>

    )
}

export default Dashboard; 