package com.revature.daos;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//This Class is responsible for all things ACCOUNT DATA. (Account DAO == Account Data Access Object)
//This Class will access/query the accounts table in the DB.
public class AccountDAO implements AccountDAOInterface {


    @Override
    public Account getAccountById(int id) {

        //use a try-with-resources to open the connection to the DB.
        try(Connection conn = ConnectionUtil.getConnection()) {

            //We need a String that hold the SQL command we want to run on the DB.
            //This string has a wildcard/parameter/variable for the account_id (the ?)
            //we have to take the user-inputted account id and put it into this wildcard
            String sql = "SELECT * FROM accounts WHERE account_id=?";

            //We need a PreparedStatement object to fill the wildcard in. (the ?)
            PreparedStatement ps = conn.prepareStatement(sql);

            //insert a value for the ? wildcard.
            //This is saying "The first wildcard will get filled by the id variable."
            ps.setInt(1,id);

            //Here, we are executing the fully-prepared SQL command that was stored in the PreparedStatement.
            //The results of the query will be stored in a ResultSet object.
            ResultSet rs = ps.executeQuery();

            //Extracting the Account data from the result set.
            //We need to use the all-args constructor to store all the data.
            //To get data, we use the rs.get<??>() method

            if(rs.next()) {
                Account account = new Account(
                        rs.getInt("account_id"),
                        rs.getString("account_name"),
                        rs.getInt("account_total")
                );

                return account;
            }

        } catch(SQLException e) {
            System.out.println("Uh oh! Error getting account! >>> ");
            e.printStackTrace();
        }

        //If something goes wrong, return NULL
        return null;
    }

    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM accounts";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()) {
                int accountId = resultSet.getInt("account_id");
                int accountTotal = resultSet.getInt("account_total");
                String accountName = resultSet.getString("account_name");

                Account account = new Account(accountId, accountName, accountTotal);
                accounts.add(account);
            }
            return accounts;

        } catch(SQLException e) {
            System.out.println("Uh oh! error getting accounts");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean updateAccountTotal(int total, String name) {

        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "UPDATE accounts SET account_total = ? WHERE account_name = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, total);
            ps.setString(2, name);
            //USES executeUPDATE!!
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Uh oh! Failed to update! >>> ");
            e.printStackTrace();
        }

        return false;
    }
}
