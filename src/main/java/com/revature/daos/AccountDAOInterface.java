package com.revature.daos;


import com.revature.models.Account;

import java.util.ArrayList;

//This interface will lay out the methods that the AccountDAO Implements
//This is a great way to document what functionalities exist in the AccountDAO
public interface AccountDAOInterface {

    //a method that SELECTS an Account by its ID
    Account getAccountById(int id);

    //a method that UPDATEs an Account's salary
    boolean updateAccountTotal(int total, String title);

    ArrayList<Account> getAllAccounts();
}
