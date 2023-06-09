/* eslint-disable react/no-unescaped-entities */
/* eslint-disable react/prop-types */
import Head from 'next/head';
import styles from '../../styles/users.module.css';
import React, {useState} from 'react';
import Link from 'next/link';


export default function User ({ users }) {

  console.log("USERS >>> ", users); //TODO: why is this not updating correctly?

  //if users contains only a single object, .map won't function properly.
  const userList = Array.isArray(users) ? users : [users];
  const [userState, setUserState] = useState(userList);

  const handleDelete = async (evt) => {
    evt.preventDefault();
    console.log("handleDelete user_id >", evt.target.value);
    try {
      const response = await fetch('http://localhost:8080/users', {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
        body: evt.target.value, //we're just sending an INT, so this is probably ok(eh?)
      })

      if(response.ok) {
        console.log('User deleted successfully!');
        const newUsersResponse = await fetch('http://localhost:8080/users');
        const newUsers = await newUsersResponse.json();
        console.log("newUsers >>> ", newUsers);
        setUserState(newUsers);
      } else {
        console.error('Failed to update user :(')
      }
      
    } catch(e) {
      console.error("An error occurred.", e)
    }
  }

  return (
    <div className={styles.container}>
      <Head>
        <title>James' Project0</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <div>
        <Link href={`/users/insert`} >
          Insert User
        </Link>
      </div>

      <table className={styles.tableContainer}>
      <thead>
        <tr>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Role ID</th>
          <th>Account ID</th>
          <th>Account Name</th>
          <th>Account Total</th>
        </tr>
      </thead>
      <tbody>
        {userState ? (
        userState.map(user => (
          // <Link key={user.user_id} href={`/users/${user.user_id}`}>
          <tr key={user.user_id} className={styles.tr} >
            <td>
              <Link href={`/users/${user.user_id}`}>{user.first_name}</Link>
            </td>
            <td>
            <Link href={`/users/${user.user_id}`}>{user.last_name}</Link>
            </td>
            <td>{user.role_id_fk}</td>
            <td>{user.account?.account_id}</td>
            <td>{user.account?.account_name}</td>
            <td>{user.account?.account_total}</td>
            <td>
              <button style={{color: 'red', backgroundColor: 'black'}} onClick={handleDelete} value={user.user_id} >
                DELETE USER
              </button>
            </td>
          </tr>
          // </Link>
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
  const res = await fetch('http://localhost:8080/users');
  const json = await res.json();
  return { props: {
      users: json
    }
  }
};