package com.revature.controllers;
import com.revature.daos.UserDAO;
import com.revature.models.User;
import com.revature.services.UserService;
import io.javalin.http.Context;

public class UserController {

    static UserDAO userDAO = new UserDAO();
    private static final UserService userService = new UserService();

    public static void handleGetAll(Context ctx) {
        ctx.json(userService.getAllUsers());
        ctx.status(200);
    }

    public static void handleGetById(Context ctx) {
        int id;
        try{
            id = Integer.parseInt(ctx.pathParam("id"));
        } catch (NumberFormatException e) {
            ctx.status(400);
            throw new RuntimeException(e);
        }
        User user = userService.getUserById(id);

        if(user != null) {
            ctx.status(201);
            ctx.json(user);
        } else {
            ctx.status(404);
        }
    }

    public static void handleCreate(Context ctx) {
        User user = ctx.bodyAsClass(User.class);
        User result = userService.insertUser(user);

        if(result != null) {
            ctx.status(201);
            ctx.json(result);
        } else {
            ctx.status(400);
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
            throw new RuntimeException(e);
        }
    }

    public static void handleDelete(Context ctx) {
        int id;
        try{
            id = Integer.parseInt(ctx.pathParam("id"));
            userService.deleteUser(id);
        } catch (NumberFormatException e) {
            ctx.status(400);
            throw new RuntimeException(e);
        }
        ctx.status(200);
    }

}
