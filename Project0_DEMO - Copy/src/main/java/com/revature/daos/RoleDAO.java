package com.revature.daos;

import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//This Class is responsible for all things ROLE DATA. (Role DAO == Role Data Access Object)
//This Class will access/query the roles table in the DB.
public class RoleDAO implements RoleDAOInterface {


    @Override
    public Role getRoleById(int id) {

        //use a try-with-resources to open the connection to the DB.
        try(Connection conn = ConnectionUtil.getConnection()) {

            //We need a String that hold the SQL command we want to run on the DB.
            //This string has a wildcard/parameter/variable for the role_id (the ?)
            //we have to take the user-inputted role id and put it into this wildcard
            String sql = "SELECT * FROM roles WHERE role_id=?";

            //We need a PreparedStatement object to fill the wildcard in. (the ?)
            PreparedStatement ps = conn.prepareStatement(sql);

            //insert a value for the ? wildcard.
            //This is saying "The first wildcard will get filled by the id variable."
            ps.setInt(1,id);

            //Here, we are executing the fully-prepared SQL command that was stored in the PreparedStatement.
            //The results of the query will be stored in a ResultSet object.
            ResultSet rs = ps.executeQuery();

            //Extracting the Role data from the result set.
            //We need to use the all-args constructor to store all the data.
            //To get data, we use the rs.get<??>() method

            if(rs.next()) {
                Role role = new Role(
                        rs.getInt("role_id"),
                        rs.getString("role_title"),
                        rs.getInt("role_salary")
                );

                return role;
            }

        } catch(SQLException e) {
            System.out.println("Uh oh! Error getting role! >>> ");
            e.printStackTrace();
        }

        //If something goes wrong, return NULL
        return null;
    }

//    public Role getAllRoles() {
//
//        try(Connection conn = ConnectionUtil.getConnection()) {
//
//            String sql = "SELECT * FROM roles";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet
//
//        } catch(SQLException e) {
//            System.out.println("Uh oh! error getting roles");
//            e.printStackTrace();
//        }
//
//    }

    @Override
    public boolean updateRoleSalary(int salary, String title) {

        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "UPDATE roles SET role_salary = ? WHERE role_title = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, salary);
            ps.setString(2, title);
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
