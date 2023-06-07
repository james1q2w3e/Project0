package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOInterface;
import com.revature.models.Account;
import com.revature.models.Account;

import java.util.ArrayList;

public class AccountService {

    private final AccountDAOInterface accountDAO = new AccountDAO();
    public ArrayList<Account> handleGetAllAccounts() {return accountDAO.getAllAccounts();}

    public Account getAccountById(int id) {
        return accountDAO.getAccountById(id);
    }

    public Account insertAccount(Account account) {
        return accountDAO.insertAccount(account);
    }

    public void deleteAccount(int id) {
        accountDAO.deleteAccount(id);
    }

    public Account updateAccount(Account account) {
        return accountDAO.updateAccount(account);
    }

}
