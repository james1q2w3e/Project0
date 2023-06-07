package com.revature.daos;


import com.revature.models.Account;

import java.util.ArrayList;

//This interface will lay out the methods that the AccountDAO Implements
//This is a great way to document what functionalities exist in the AccountDAO
public interface AccountDAOInterface {

    ArrayList<Account> getAllAccounts();
    //a method that SELECTS an Account by its ID
    Account getAccountById(int id);

    //a method that UPDATEs an Account
    Account updateAccount(Account account);

    Account insertAccount(Account account);

    /*TODO: set the SQL table to set account_id_fk to null in the case
            that the account associated is deleted    */
    void deleteAccount(int account_id);

}
