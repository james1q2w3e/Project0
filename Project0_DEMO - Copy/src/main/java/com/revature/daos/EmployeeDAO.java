package com.revature.daos;

import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;




public class EmployeeDAO implements EmployeeDAOInterface {

    @Override
    public ArrayList<Employee> getAllEmployees() {

        ArrayList<Employee> employees = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM employees";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();

            RoleDAO roleDAO = new RoleDAO();

            while(resultSet.next()) {
                int empId = resultSet.getInt("employee_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int roleId = resultSet.getInt("role_id_fk");


                Employee employee = new Employee(empId, firstName, lastName, roleDAO.getRoleById(roleId));
                employees.add(employee);
            }

            return employees;

        } catch (SQLException e) {
            System.out.println("Uh oh! Bad query for SELECT ALL");
            e.printStackTrace();
        }

        //return an empty ArrayList instead of null
        return new ArrayList<>();
    }


    @Override
    public void displayAllEmployees() { //Make a formatted-ish table displaying all employees
        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM employees";
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
    public Employee getEmployeeById(int id) {
        return null;
    }

    @Override
    public boolean insertEmployee(Employee emp) {

        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "INSERT INTO employees(first_name, " +
                    "last_name, role_id_fk)" +
                    "VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getFirst_name());
            ps.setString(2, emp.getLast_name());
            ps.setInt(3, emp.getRole_id_fk());
            ps.executeUpdate();

            System.out.println("Insert Successful! (probably)");
            return true;

        } catch (SQLException e) {
            System.out.println("Uh oh! Failed to insert! >>> ");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Employee updateEmployee(int id) {
        return null;
    }

    @Override
    public boolean deleteEmployee(int id) {

        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "DELETE FROM employees WHERE employee_id=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Delete Successful!");
            return true;

        } catch (SQLException e) {
            System.out.println("Oh no! Error deleting employee >>> ");
            e.printStackTrace();
        }

        return false;
    }
}
