export const debtInitialState = {
    debts: [],
    payoff: [{
        debtName: '', 
        originalBalance: '', 
        accruedInterest: '', 
        monthsOfPayments: '', 
        finalPaymentMonth: ''
    }]
}; 

export const debtActions = {
    addDebts: (state, action) => ({
        ...state, 
        debts: action.payload
    }), 
    payoff: (state, action) => ({
        ...state, 
        payoff: action.payload
    })
}; 
