import React from 'react';
import './App.css';
import {gql, useQuery, useSubscription} from "@apollo/client";

interface User {
    id: number
    code: string
    name: string
}

interface Account {
    id: number
    code: string
    balance: number
    user: User
}

interface AccountResult {
    fetchAccounts: Array<Account>
}

const ACCOUNTS = gql`
    {
        fetchAccounts {
            id
            code
            balance
            user {
                code
                name
            }
        }
    }
`

const HELLO = gql`
    subscription {
        hello
    }
`

function App() {
    const {error, loading, data} = useQuery<AccountResult>(ACCOUNTS)
    // const {error, loading, data} = useSubscription(HELLO)
    console.log(data)
    return (
        <>
            <h1>Accounts</h1>
            {
                loading || !data ? <p>Loading...</p> :
                    data.fetchAccounts.map(account => (
                            <>
                                <div>Account ID: {account.id}</div>
                                <div>Account Code: {account.code}</div>
                                <div>Account User: {account.user.name}</div>
                            </>
                        )
                    )
                // <p>{`${data.hello}`}</p>
            }
        </>
    );
}

export default App;
