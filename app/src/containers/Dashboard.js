import React, { useState, useEffect, useCallback } from 'react'
import CreditorList from '../components/creditors/CreditorList';
import CreditorTotal from '../components/creditors/CreditorTotals';
import { request } from '../util/APIUtils';
import { DEBT_LIST_URL } from '../constants/index';
import { Link } from 'react-router-dom'; 
import { userStore } from '../globalStore/UserContext'; 

const Dashboard = () => {

    const [data, setData] = useState([]); 
    const [totals, setTotals] = useState({})
    const { state, dispatch } = userStore();

    const setDebts = useCallback(
      (payload) => dispatch({type: "addDebts", payload:payload}), [dispatch]); 
    
    useEffect(() => {    

        console.log("inside useEffect");
        
        const  fetchData = async() => {
            request.get(DEBT_LIST_URL)
                .then(res => {
                    console.log("response ", res);
                    setDebts(res.data.debts); 
                    setData(res.data.debts)
                    setTotals(
                        {
                        "totalBalance": res.data.totalBalance, 
                        "totalMinPayment": res.data.totalMinPayment, 
                        "totalCreditLimit": res.data.totalCreditLimit
                    }) 
                    
                })   
            }; 
            fetchData()            
        }, []);  
        
        console.log("dashboard", state.debts);
        

    return (
        <>
            <Link to="/creditors/new">
                <button>
                Add Creditor
                </button> 
                </Link>
            <CreditorList debts={state.debts}/>
            <CreditorTotal totals={totals}/>
        </>

    )
}

export default Dashboard; 