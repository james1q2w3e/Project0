package com.revature.models;

public class Role {

    //in our model classes, we need to directly reflect our DB tables
    //we need a variable for every DB column
    private int role_id;
    private String role_title;
    private int role_salary;

    //boilerplate code

    //no args
    public Role() {
    }

    //all args
    public Role(int role_id, String role_title, int role_salary) {
        this.role_id = role_id;
        this.role_title = role_title;
        this.role_salary = role_salary;
    }

    //all args minus ID - the ID is serial, so we never need to worry about
    // including the primary key
    public Role(String role_title, int role_salary) {
        this.role_title = role_title;
        this.role_salary = role_salary;
    }

    public int getRole_id() {
        return role_id;
    }

    // unneeded, as the ID is auto generated (serial)
    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_title() {
        return role_title;
    }

    public void setRole_title(String role_title) {
        this.role_title = role_title;
    }

    public int getRole_salary() {
        return role_salary;
    }

    public void setRole_salary(int role_salary) {
        this.role_salary = role_salary;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", role_title='" + role_title + '\'' +
                ", role_salary=" + role_salary +
                '}';
    }



}
