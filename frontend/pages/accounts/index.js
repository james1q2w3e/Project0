/* eslint-disable react/no-unescaped-entities */
/* eslint-disable react/prop-types */
import Head from 'next/head';
import styles from '../../styles/accounts.module.css';
import React, {useState} from 'react';
import Link from 'next/link';


export default function Accounts({accounts}) {
  console.log("Accounts are up?", accounts);

  //if accounts contains only a single object, .map won't function properly.
  const accountList = Array.isArray(accounts) ? accounts : [accounts];
  const [accountState, setAccountState] = useState(accountList);

  const handleDelete = async (evt) => {
    evt.preventDefault();
    console.log("handleDelete user_id >", evt.target.value);
    try {
      const response = await fetch('http://localhost:8080/accounts', {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
        body: evt.target.value, //we're just sending an INT, so this is probably ok(eh?)
      })

      if(response.ok) {
        console.log('User deleted successfully!');
        //To ensure we get the updated data, and it re-renders the content
        const newAccountsResponse = await fetch('http://localhost:8080/accounts');
        const newAccounts = await newAccountsResponse.json();
        console.log("newAccounts >>> ", newAccounts);
        setAccountState(newAccounts);
      } else {
        console.error('Failed to update user :(')
      }
      
    } catch(e) {
      console.error("An error occurred.", e)
    }
  }

  return(
    <div className={styles.container}>
      <Head>
        <title>James' Project0</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <div>
        <Link href={`/accounts/insert`} >
          Insert account
        </Link>
      </div>

      <table className={styles.tableContainer}>
      <thead>
        <tr>
          <th>Account ID</th>
          <th>Account Name</th>
          <th>Account Total</th>
        </tr>
      </thead>
      <tbody>
        {accountState ? (
        accountState.map(account => (
          <tr key={account.account_id} className={styles.tr} >
            <td>{account?.account_id}</td>
            <td>
              <Link href={`/accounts/${account.account_id}`}>{account.account_name}</Link>
            </td>
            <td>
            <Link href={`/accounts/${account.account_id}`}>{account.account_total}</Link>
            </td>
            <td>
              <button style={{color: 'red', backgroundColor: 'black'}} onClick={handleDelete} value={account.account_id} >
                DELETE ACCOUNT
              </button>
            </td>
          </tr>
        ))
        ) : (
          <tr>
            <td>Loading...</td>
          </tr>
        )}
      </tbody>
    </table>

    </div>
  )
}


export const getServerSideProps = async () => {
  console.log("Fetch data...");
  const res = await fetch('http://localhost:8080/accounts');
  const json = await res.json();
  return { props: {
      accounts: json
    }
  }
};