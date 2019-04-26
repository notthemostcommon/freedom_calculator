export const debtInitialState = {
    debts: []
}; 

export const debtActions = {
    addDebts: (state, action) => ({
        ...state, 
        debts: action.payload
    })
}; 
