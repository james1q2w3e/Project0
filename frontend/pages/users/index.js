/* eslint-disable react/prop-types */
import Head from 'next/head';
import styles from '../../styles/Home.module.css';
import React from 'react';
import Link from 'next/link';


export default function User ({ users }) {

  console.log("USERS >>> ", users);

  //if users contains only a single object, .map won't function properly.
  const userList = Array.isArray(users) ? users : [users];

  return (
    <div className={styles.container}>
      <Head>
        <title>Create Next App</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <div>
        <Link href={`/users/insert`} >
          Insert User
        </Link>
      </div>

      <table>
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
        {userList.map(user => (
          <tr key={user.user_id}>
            <td>{user.first_name}</td>
            <td>{user.last_name}</td>
            <td>{user.role_id_fk}</td>
            <td>{user.account?.account_id}</td>
            <td>{user.account?.account_name}</td>
            <td>{user.account?.account_total}</td>
          </tr>
        ))}
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