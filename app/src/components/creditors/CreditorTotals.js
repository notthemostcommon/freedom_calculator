import React from 'react'

const CreditorTotal = (props) => {

    return(
        <>
        <div>Totals {props.totals.totalBalance}</div>
        <div>Monthly Payment Total {props.totals.totalMinPayment}</div>
        <div>Total Credit  {props.totals.totalCreditLimit}</div>
            
        </>

    )
}

export default CreditorTotal; 