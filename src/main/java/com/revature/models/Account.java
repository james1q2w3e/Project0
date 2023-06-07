package com.revature.models;

public class Account {

    //in our model classes, we need to directly reflect our DB tables
    //we need a variable for every DB column
    private int account_id;
    private String account_name;
    private int account_total;

    //boilerplate code

    //no args
    public Account() {
    }

    //all args
    public Account(int account_id, String account_name, int account_total) {
        this.account_id = account_id;
        this.account_name = account_name;
        this.account_total = account_total;
    }

    //all args minus ID - the ID is serial, so we never need to worry about
    // including the primary key
    public Account(String account_name, int account_total) {
        this.account_name = account_name;
        this.account_total = account_total;
    }

    public int getAccount_id() {
        return account_id;
    }

    // unneeded, as the ID is auto generated (serial)
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public int getAccount_total() {
        return account_total;
    }

    public void setAccount_total(int account_total) {
        this.account_total = account_total;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", account_name='" + account_name + '\'' +
                ", account_total=" + account_total +
                '}';
    }



}
