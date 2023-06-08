/* eslint-disable react/prop-types */
import styles from '../../styles/Home.module.css';
import React, { useState } from 'react';


export default function SingleUser ({ user }) {

  const userToUpdate = {
    user_id: user.user_id,
    first_name: user.first_name,
    last_name: user.last_name,
    account_id_fk: user.account.account_id,
  }

  const [state, setState] = useState(userToUpdate);
  const [hide, setHide] = useState(true);
  const { 
    first_name, last_name, account_id_fk, user_id
  } = state;


  console.log("USER >>> ", user);
  console.log("AccountID >>>", account_id_fk);

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
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(state),
      });

      if(response.ok) {
        console.log('User updated successfully!');
      } else {
        console.error('Failed to update user :(')
      }

    } catch(e) {
      console.error("An error occurred.", e)
    }

  }



  return (
    <div className={styles.container}>

      <div>
        <button onClick={() => {setHide(!hide)}}>
          Update User
        </button>
        {hide ? (
          <div></div>
         ) : (
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
        )}
      </div>

      <table>
      <thead>
        <tr>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Account ID</th>
          <th>Account Name</th>
          <th>Account Total</th>
        </tr>
      </thead>
      <tbody>
          <tr>
            <td>{user.first_name}</td>
            <td>{user.last_name}</td>
            <td>{user.account.account_id}</td>
            <td>{user.account.account_name}</td>
            <td>$ {user.account.account_total}</td>
          </tr>
      </tbody>
    </table>

    </div>
  )
}

SingleUser.getInitialProps = async ({ query }) => {
  const id = query.id; // Assuming the parameter in the URL is named "id"
  const res = await fetch(`http://localhost:8080/users/${id}`);
  const json = await res.json();
  console.log("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA", query.id);
  return {
    user: json
  };
};