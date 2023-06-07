package com.revature.daos;


import com.revature.models.User;

import java.util.ArrayList;

//Interfaces are good for organization and clarity
public interface UserDAOInterface {

    // \/ return ArrayList
    ArrayList<User> getAllUsers();

    void displayAllUsers();

    // \/ Returns User
    User getUserById(int id);
    User insertUser(User user); // Easier to just send an entire User Object
    User updateUser(User user);
    void deleteUser(int id);

}
