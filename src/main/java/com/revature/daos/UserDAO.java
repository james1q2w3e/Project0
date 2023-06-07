package com.revature.daos;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;




public class UserDAO implements UserDAOInterface {

    @Override
    public ArrayList<User> getAllUsers() {

        ArrayList<User> users = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM users";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();

            AccountDAO accountDAO = new AccountDAO();

            while(resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int accountId = resultSet.getInt("account_id_fk");


                User user = new User(userId, firstName, lastName, accountDAO.getAccountById(accountId), accountId);
                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            System.out.println("Uh oh! Bad query for SELECT ALL");
            e.printStackTrace();
        }

        //return an empty ArrayList instead of null
        return new ArrayList<>();
    }


    @Override
    public void displayAllUsers() { //Make a formatted-ish table displaying all users
        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM users";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            //print column HEADERS
            for(int i=1; i<=columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();

            while(resultSet.next()) {
                for(int i=1; i<= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t" + "|" + "\t");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Uh oh! Bad query for DISPLAY ALL");
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(int id) {

        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM users WHERE user_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            AccountDAO accountDAO = new AccountDAO();

            while(resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int accountId = resultSet.getInt("account_id_fk");


                User user = new User(userId, firstName, lastName, accountDAO.getAccountById(accountId));
                return user;
            }


        } catch (SQLException e) {
            System.out.println("Uh oh! Bad query for getUserById");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User insertUser(User user) {

        try(Connection conn = ConnectionUtil.getConnection()) {


            String sql = "INSERT INTO users(first_name, " +
                    "last_name, account_id_fk)" +
                    "VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getFirst_name());
            ps.setString(2, user.getLast_name());
            ps.setInt(3, user.getAccount_id_fk());
            ps.executeUpdate();

            System.out.println("Insert Successful! (probably)");
            return user;

        } catch (SQLException e) {
            System.out.println("Uh oh! Failed to insert! >>> ");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User updateUser(User user) {

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE users SET first_name=?, last_name=?, account_id=? WHERE user_id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getFirst_name());
            ps.setString(2, user.getLast_name());
            ps.setInt(3, user.getAccount_id_fk());
            ps.setInt(4, user.getUser_id());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Oh no! Error updating user >>> ");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deleteUser(int id) {

        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "DELETE FROM users WHERE user_id=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Delete Successful!");

        } catch (SQLException e) {
            System.out.println("Oh no! Error deleting user >>> ");
            e.printStackTrace();
        }
    }
}
