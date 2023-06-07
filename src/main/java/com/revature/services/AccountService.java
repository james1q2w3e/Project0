package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOInterface;
import com.revature.models.Account;

import java.util.ArrayList;

public class AccountService {

    private final AccountDAOInterface accountDAO = new AccountDAO();
    public ArrayList<Account> handleGetAllAccounts() {
        return accountDAO.getAllAccounts();
    }
}
