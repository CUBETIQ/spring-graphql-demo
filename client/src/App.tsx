import React from 'react';
import './App.less';
import {gql, useQuery, useSubscription} from "@apollo/client";
import { Table } from 'antd';

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

const SUB_ACCOUNTS = gql`
    subscription {
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

const accountColumns = [
    {
        title: "Account ID",
        dataIndex: "id",
        key: "id",
    },
    {
        title: "Account Code",
        dataIndex: "code",
        key: "code",
    },
    {
        title: "User",
        dataIndex: ["user", "name"],
        key: "user.name",
    }
]
function App() {
    // const {error, loading, data} = useQuery<AccountResult>(ACCOUNTS)
    const {error, loading, data} = useSubscription<AccountResult>(SUB_ACCOUNTS)
    // const {error, loading, data} = useSubscription(HELLO)
    console.log(data)
    return (
        <>
            <h1>Accounts</h1>
            {
                loading || !data ? <p>Loading...</p> :
                    // data.fetchAccounts.map(account => (
                    //         <>
                    //             <div>Account ID: {account.id}</div>
                    //             <div>Account Code: {account.code}</div>
                    //             <div>Account User: {account.user.name}</div>
                    //         </>
                    //     )
                    // )
                <Table dataSource={data.fetchAccounts} columns={accountColumns}/>
                // <p>{`${data.hello}`}</p>
            }
        </>
    );
}

export default App;
