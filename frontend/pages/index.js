// import Head from 'next/head';
import styles from '../styles/Home.module.css';
import React from 'react';
import Link from 'next/link';


export default function Home(props) {

  console.log("Props: ", props);

  return (
    <div className={styles.container}>
      
      <div>

        <div>
          <Link href={`/users`}>
              Users
          </Link>
        </div>

        <br/>

        <div>
          <Link href={`/accounts`}>
              Accounts
          </Link>
        </div>

      </div>

    </div>
  )
}

export const getServerSideProps = async () => {
  console.log("Fetch data...");
  const res = await fetch('http://localhost:8080/');
  const json = await res.json();
  return { props: {
      props: json
    }
  }
};


// Home.getInitialProps = async () => {
//   const res = await fetch('http://localhost:8080/');
//   const json = await res.json();
//   return {
//       json
//   }
// };
