/* eslint-disable react/prop-types */
// import Head from 'next/head';
// import styles from '../../styles/accounts.module.css';
import React, { useState } from 'react';

//TODO: fix this monstrosity
const initialState = {
  account_name: '',
  account_total: '',
  // account_id: 0,
};

export default function InsertAccount (props) {

  const [state, setState] = useState(initialState);

  // Accessing the state values
  const { account_name, account_total } = state;

  // Updating the state
  const handleChange = (event) => {setState({
      ...state,
      [event.target.name]: event.target.value
    });
  };

  const handleSubmit = async (evt) => {
    evt.preventDefault();
    console.log(state);

    try {
      const response = await fetch('http://localhost:8080/accounts', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(state),
      });

      if(response.ok) {
        console.log('Account created successfully!');
      } else {
        console.error('Failed to create Account :(')
      }

    } catch(e) {
      console.error("An error occurred.", e)
    }

  }


  return (
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

      <button onClick={handleSubmit}>Submit</button>

    </div>
  )
}

export const getServerSideProps = async () => {

  const res = await fetch('http://localhost:8080/accounts');

  const json = await res.json();
  return { props: {
      accounts: json,
    }
  }

};