package com.revature.daos;


import com.revature.models.Employee;

import java.util.ArrayList;

//Interfaces are good for organization and clarity
public interface EmployeeDAOInterface {

    // \/ return ArrayList
    ArrayList<Employee> getAllEmployees();

    void displayAllEmployees();

    // \/ Returns Employee
    Employee getEmployeeById(int id);
    boolean insertEmployee(Employee emp); // Easier to just send an entire Employee Object
    Employee updateEmployee(int id);
    boolean deleteEmployee(int id);

}
