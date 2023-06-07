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
    public Account getAccountById(int id) {

        //use a try-with-resources to open the connection to the DB.
        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM accounts WHERE account_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

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

    @Override
    public Account updateAccount(Account account) {

        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "UPDATE accounts SET account_total = ?, account_name=? WHERE account_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, account.getAccount_total());
            ps.setString(2, account.getAccount_name());
            ps.setInt(3, account.getAccount_id());
            //USES executeUPDATE!!
            ps.executeUpdate();

            return account;

        } catch (SQLException e) {
            System.out.println("Uh oh! Failed to update! >>> ");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Account insertAccount(Account account) {
        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "INSERT INTO accounts(account_name, account_total)" +
                    "VALUES (?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, account.getAccount_name());
            ps.setInt(2, account.getAccount_total());
            //USES executeUPDATE!!
            ps.executeUpdate();

            return account;

        } catch (SQLException e) {
            System.out.println("Uh oh! Failed to update! >>> ");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deleteAccount(int id) {

        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "DELETE FROM accounts WHERE account_id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Delete Successful!");

        } catch (SQLException e) {
            System.out.println("Oh no! Error deleting account >>> ");
            e.printStackTrace();
        }
    }
}
