package com.revature.controllers;
import com.revature.daos.UserDAO;
import com.revature.models.User;
import com.revature.services.UserService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserController {

    static UserDAO userDAO = new UserDAO();
    private static final UserService userService = new UserService();

    //Logger
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public static void handleGetAll(Context ctx) {
        try {
            ctx.json(userService.getAllUsers());
            ctx.status(200);
            logger.info("Returned from <handleGetAll> : ");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void handleGetById(Context ctx) {
        int id;
        try{
            id = Integer.parseInt(ctx.pathParam("id"));
            User user = userService.getUserById(id);
            if(user != null) {
                ctx.status(200);
                ctx.json(user);
            } else {
                ctx.status(404);
            }
        } catch (NumberFormatException e) {
            ctx.status(400);
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void handleCreate(Context ctx) {
        try {
            User user = ctx.bodyAsClass(User.class);
            User result = userService.insertUser(user);

            if (result != null) {
                ctx.status(201);
                ctx.json(result);
            } else {
                ctx.status(400);
                logger.warn("<handleCreate> method failed (UserController)");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void handleUpdate(Context ctx) {
        try {
            User user = ctx.bodyAsClass(User.class);
            User result = userService.updateUser(user);

            if (result != null) {
                ctx.status(201);
                ctx.json(result);
            } else {
                ctx.status(400);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void handleDelete(Context ctx) {
//        int id;
        try{
//            id = Integer.parseInt(ctx.pathParam("id"));
            User user = ctx.bodyAsClass(User.class);
            userService.deleteUser(user.getUser_id());
            ctx.status(200);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            ctx.status(400);
            throw new RuntimeException(e);
        }
    }

}
