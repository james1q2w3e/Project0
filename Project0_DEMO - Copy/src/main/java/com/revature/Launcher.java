package com.revature;

import com.revature.daos.EmployeeDAO;
import com.revature.daos.RoleDAO;
import com.revature.models.Employee;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Launcher {

    public static void main(String[] args) {

        //Try-with-resources block
        //A resource is opened within the parenthesis
        //The resource will also CLOSE for us when the block completes
        //Helpful for code-cleanup and preventing memory leaks
        try(Connection conn = ConnectionUtil.getConnection()) {

            System.out.println("Connection Successful!!!");

        } catch (SQLException e) {
            System.out.println("Connection Failed :( ");
        }

        //Calling DAO methods...
        RoleDAO roleDAO = new RoleDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();

        //get a Role object by its ID
//        System.out.println(roleDAO.getRoleById(1));
//
//        System.out.println(roleDAO.updateRoleSalary(20001, "Manager"));
//
//
//        Employee employee =
//                new Employee("Jeffrey", "Bobbert", 1);
//        System.out.println(employeeDAO.insertEmployee(employee));


        ArrayList<Employee> employeeList = employeeDAO.getAllEmployees();
        for (Employee e : employeeList) {
            System.out.println(e);
        }

        System.out.println();

        //to display in a (more-or-less) formatted table
        employeeDAO.displayAllEmployees();

        System.out.println();

        System.out.println(employeeDAO.deleteEmployee(1));

    }

}