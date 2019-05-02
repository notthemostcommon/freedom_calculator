import React, { useCallback, useEffect, useState } from 'react'; 
import { userStore } from '../../globalStore/UserContext'; 
import StrategyTable from './StrategyTable';

const calcMonthlyAccruedInterest = (apr, balance) => {
    const decimalRate = apr/100; 
    const monthlyRate = decimalRate/12; 
    const monthlyInterest = balance * monthlyRate;
    return monthlyInterest; 
}
const sortArrayOfObjects = (arr, key) => {
    return arr.sort((a, b) => {
        return a[key] - b[key];
    });
}

const calcPayoffMonth = (monthsToPayoff) => {
    const date = new Date();
    date.setFullYear(date.getFullYear(), date.getMonth() + monthsToPayoff); 
    let fullDate = date.toDateString().split(" "); 
    
    const finalDate = fullDate.reduce((acc, val, index) => {
        index % 2 !== 0 && acc.push(val); 
        return acc;  
    },[])
    
    return finalDate.join(" "); 
}

const formatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
  });
  
  const totalInterestPaid = (list) => {
      const total = list.reduce((total, amount) => {
          if(amount.charAt(0) === "$"){
              amount = amount.substring(1);                    
          }
          return total += parseFloat(amount); 
      }, 0); 
    return formatter.format(total); 
  }

  export default function CalculateInterest() {
      const strategy = {
          NONE: "NONE", 
          SNOWBALL: "SNOWBALL", 
          AVALANCHE: "AVALANCHE"
        }
        
        const { state, dispatch } = userStore(); 
        const [ dropdownOpen, setDropdownOpen ] = useState(false); 
        const [ payoffStrategy, setStrategy ] = useState(strategy.NONE); 
        const [ propsAvail, setPropsAvail ] = useState(false); 
        const [ totalInterest, setTotalInterest ] = useState(formatter.format(0)); 
        
        const setPayoff = useCallback(
            (payload) => dispatch({type: "payoff", payload:payload}), [dispatch]); 
            
            const calculateTotalAccruedInterest = (debtName, apr, balance, payment) => {
                let accruedInterest = 0.00; 
                let remainingBalance = balance; 
                let month = 0;         
                while(remainingBalance >= payment) {
                    const monthlyInterest = calcMonthlyAccruedInterest(apr, balance);             
                    accruedInterest += monthlyInterest; 
                    remainingBalance = (remainingBalance + monthlyInterest) - payment; 
                    month ++; 
                }
                if (remainingBalance > 0) month++; 
                let finalPaymentMonth = calcPayoffMonth(month); 
                const results = new Map(); 
                results.set("debtName", debtName)
                results.set("originalBalance", formatter.format(balance)); 
                results.set("accruedInterest", formatter.format(accruedInterest)); 
                results.set("remainingBalance", formatter.format(remainingBalance)); 
                results.set("monthsOfPayments", month);
                results.set("finalPaymentMonth", finalPaymentMonth);          
                return results; 
                
            }
            
        const arrangedList = (strategy) => {        
            const list = state.debts;         
            switch(strategy){
                case "NONE": return list; 
                // break;
                case "SNOWBALL": 
                return sortArrayOfObjects(list, "balance")
                default: 
                console.log("Not a valid strategy");
                // break; 
                
            }
        }


        const payoffSchedule = () => {
            const list = arrangedList(payoffStrategy);
            const results = []; 
            const interestList = []; 
            list.map(d => {
                const result = calculateTotalAccruedInterest(d.debtName, d.apr, d.balance, d.minPayment);
                interestList.push(result.get("accruedInterest")); 
                return results.push(result);  
            })  
            
            setPayoff(results); 
            setTotalInterest(() => totalInterestPaid(interestList))
            setPropsAvail(true); 
        }

        useEffect(() => {
        payoffSchedule(); 
        }, [])

        const toggle = () => {
            setDropdownOpen(true)
            payoffSchedule(); 
        }


  return (
      <div>
          <button onClick={toggle}>No Strategy</button>
          {propsAvail && <StrategyTable data={state.payoff} interest={totalInterest}/>}
    
      </div>
  )
}