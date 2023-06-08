/* eslint-disable react/prop-types */
// import Head from 'next/head';
// import styles from '../../styles/Home.module.css';
import React, { useState } from 'react';


const initialState = {
  first_name: '',
  last_name: '',
  account_id_fk: 0,
};

export default function InsertUser (props) {

  // console.log(props);

  const [state, setState] = useState(initialState);

  // Accessing the state values
  const { first_name, last_name, account_id_fk } = state;

  // Updating the state
  const handleChange = (event) => {
    //To ensure the account_id_fk is a number, not a string
    const value = event.target.name === 'account_id_fk' ? parseInt(event.target.value) : event.target.value;
    setState({
      ...state,
      [event.target.name]: value
    });
  };

  const handleSubmit = async (evt) => {
    evt.preventDefault();
    console.log(state);

    try {
      const response = await fetch('http://localhost:8080/users', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(state),
      });

      if(response.ok) {
        console.log('User created successfully!');
      } else {
        console.error('Failed to create user :(')
      }

    } catch(e) {
      console.error("An error occurred.", e)
    }

  }


  return (
    <div>

      <label>First name</label>
      <input
        type="text"
        name="first_name"
        value={first_name}
        onChange={handleChange}
      />

      <label>Last name</label>
      <input
        type="text"
        name="last_name"
        value={last_name}
        onChange={handleChange}
      />

      <label>Account ID</label>
      <input
        type="number"
        name="account_id_fk"
        value={account_id_fk}
        onChange={handleChange}
      />

      <button onClick={handleSubmit}>Submit</button>

    </div>
  )
}

export const getServerSideProps = async () => {

  const res = await fetch('http://localhost:8080/users');

  const json = await res.json();
  return { props: {
      users: json,
    }
  }

};