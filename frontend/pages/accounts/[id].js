/* eslint-disable react/prop-types */
import styles from '../../styles/accounts.module.css';
import React, { useState, useEffect } from 'react';

export default function SingleAccount ({ account }) {

  const accountToUpdate = {
    account_id: account.account_id,
    account_name: account.account_name,
    account_total: account.account_total,
  }

  const [accountDataState, setAccountDataState] = useState(accountToUpdate);
  const [hide, setHide] = useState(true);
  const { 
    account_name, account_total, account_id
  } = accountDataState;


  console.log("account >>> ", account);
  console.log("STATE >>>> ", accountDataState);

  const handleChange = (event) => {
    //To ensure the account_id is a number, not a string
    const value = event.target.name === 'account_id' ? parseInt(event.target.value) : event.target.value;
    setAccountDataState({
      ...accountDataState,
      [event.target.name]: value
    });
  };

  const handleSubmit = async (evt) => {
    evt.preventDefault();
    console.log(accountDataState);

    try {
      const response = await fetch('http://localhost:8080/accounts', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(accountDataState),
      });

      if(response.ok) {
        console.log('account updated successfully!');
        window.location.reload(); //Listen. React was being a pain and not re-rendering.
        // const res = await fetch(`http://localhost:8080/accounts/${account_id}`);
        // const json = await res.json();
        // console.log("AAAAAAA", json);
        // setAccountDataState(json)
        
      } else {
        console.error('Failed to update account :(')
      }

    } catch(e) {
      console.error("An error occurred.", e)
    }

  }



  return (
    <div className={styles.container}>

      <div>
        <button onClick={() => {setHide(!hide)}}>
          Update account
        </button>
        {hide ? (
          <div></div>
         ) : (
          <div>

            <label>Account name</label>
            <input
              type="text"
              name="account_name"
              value={account_name}
              onChange={handleChange}
            />

            <label>Account Total</label>
            <input
              type="number"
              name="account_total"
              value={account_total}
              onChange={handleChange}
            />

            <label>Account ID</label>
            <input
              type="number"
              name="account_id"
              value={account_id}
              disabled={true}
            />

            <button onClick={handleSubmit}>Submit</button>

          </div>
        )}
      </div>

      <table>
      <thead>
        <tr>
          <th>Account ID</th>
          <th>Account Name</th>
          <th>Account Total</th>
        </tr>
      </thead>
      <tbody>
          <tr>
            <td>{account.account_id}</td>
            <td>{account.account_name}</td>
            <td>{account.account_total}</td>
          </tr>
      </tbody>
    </table>
    

    </div>
  )
}

export const getServerSideProps = async ({ query }) => {
  const id = query.id; // Assuming the parameter in the URL is named "id"
  const res = await fetch(`http://localhost:8080/accounts/${id}`);
  const json = await res.json();
  console.log("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA", query.id);
  return { props: {
      account: json
    }
  };
};