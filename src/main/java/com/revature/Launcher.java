package com.revature;

import com.revature.controllers.UserController;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.JavalinAppConfig;
import io.javalin.Javalin;


import java.sql.Connection;
import java.sql.SQLException;

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


        JavalinAppConfig app = new JavalinAppConfig();
        app.start(8080);



    }

}