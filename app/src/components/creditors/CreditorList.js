import React, { useEffect, useState } from 'react'
import { request } from '../../util/APIUtils';
import { DEBT_LIST_URL } from '../../constants/index';


const CreditorList = () => {
    const [data, setData] = useState(); 
    
    useEffect(() => {
    
        const  fetchData = async() => {
            const response = request.get(DEBT_LIST_URL)
                .then(res => {
                    setData(res.data)        
                })   
            }; 
            fetchData()            
        },[]);         


    return (
        data ? 
        <div>
        {data.map( (data, index) =>
        <div key={index}>
            <h1>{data.debtName}</h1>
            <p> {data.apr}</p>
            <hr/>
        </div>
        )}
    </div>
    : <h1> There is no data </h1>
    )


}

export default CreditorList;  