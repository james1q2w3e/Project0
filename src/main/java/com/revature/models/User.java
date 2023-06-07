package com.revature.models;

public class User {

    private int user_id;
    private String first_name;
    private String last_name;

    /*User objects in Java will contain entire Account objects instead of just an int foreign key
      an int FK is less useful to us than an entire Account object. Account objects have all the data relevant to Account.*/
    private Account account;
    /*We have this variable to make inserts easier (when we insert a new User)
      When inserting a new User, we can just include the FK (the int) instead of an entire Account object*/
    private int account_id_fk;


    //We'll have different constructors using one or the other foreign key variable, used for different purposes

    //no args
    public User() {
    }

    public User(int user_id, String first_name, String last_name, Account account, int account_id_fk) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.account = account;
        this.account_id_fk = account_id_fk;

    }

    //all args (FOR ACCOUNT OBJECT)
    public User(int user_id, String first_name, String last_name, Account account) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.account = account;
    }

    //all args minus ID with ACCOUNT object
    public User(String first_name, String last_name, Account account) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.account = account;
    }

    

    //all args minus ID (with account_id_fk INT) ***THIS WILL ONLY BE USED FOR INSERTS***
    public User(String first_name, String last_name, int account_id_fk) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.account_id_fk = account_id_fk;

    }

    //Getters / Setters

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getAccount_id_fk() {
        return account_id_fk;
    }

    public void setAccount_id_fk(int account_id_fk) {
        this.account_id_fk = account_id_fk;
    }

    //toString() so we can print/view our User objects


    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", account=" + account +
                ", account_id_fk=" + account_id_fk +
                '}';
    }
}
