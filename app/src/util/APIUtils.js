

export function register(signupRequest) {
    return fetch('/users/register', {
        
        method: 'post',
        header: {
            'Accept':'application/json', 
            'Content-Type':'application/json'
        }, 
        body: JSON.stringify(signupRequest)
    });
}