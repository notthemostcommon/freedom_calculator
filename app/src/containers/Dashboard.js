import React, { useState, useEffect, useCallback } from 'react'
import CreditorList from '../components/creditors/CreditorList';
import CreditorTotal from '../components/creditors/CreditorTotals';
import  client  from '../util/APIUtils';
import { DEBT_LIST_URL } from '../constants/index';
import { Link } from 'react-router-dom'; 
import { userStore } from '../globalStore/UserContext'; 

const Dashboard = () => {

    const [data, setData] = useState([]); 
    const [totals, setTotals] = useState({})
    const { state, dispatch } = userStore();
    const request = client(); 
    const setDebts = useCallback(
      (payload) => dispatch({type: "addDebts", payload:payload}), [dispatch]); 
    
      const  fetchData = async() => {
          console.log("fetching data");
          
          const results = await request.get(DEBT_LIST_URL); 
              
                  console.log("response ", results.data );
                  setDebts(results.data.debts); 
                //   setData(res.data.debts)
                  setTotals(
                      {
                      "totalBalance": results.data.totalBalance, 
                      "totalMinPayment": results.data.totalMinPayment, 
                      "totalCreditLimit": results.data.totalCreditLimit
                  }) 
                  
                 
          }; 

    useEffect(() => {    
        console.log("inside useEffect");
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