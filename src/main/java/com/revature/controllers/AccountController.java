package com.revature.controllers;

import com.revature.models.Account;
import com.revature.daos.AccountDAO;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.UserService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountController {

    static AccountDAO accountDAO = new AccountDAO();
    private static final AccountService accountService = new AccountService();

    //Logger
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    public static void handleGetAll(Context ctx) {
        try {
            ctx.json(accountService.handleGetAllAccounts());
            ctx.status(200);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void handleGetById(Context ctx) {
        int id;
        try {
            id = Integer.parseInt(ctx.pathParam("id"));
            Account account = accountService.getAccountById(id);
            if(account != null) {
                ctx.status(200);
                ctx.json(account);
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
            Account account = ctx.bodyAsClass(Account.class);
            System.out.println(account.toString());
            Account result = accountService.insertAccount(account);

            if(result != null) {
                logger.info("Success creating an account! :D "+result);
                ctx.status(201);
                ctx.json(result);
            } else {
                ctx.status(400);
                logger.warn("<handleCreate> method failed (AccountController");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void handleUpdate(Context ctx) {
        try {
            Account account = ctx.bodyAsClass(Account.class);
            Account result = accountService.updateAccount(account);

            if (result != null) {
                ctx.status(200);
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
//
        try{
            int id = Integer.parseInt(ctx.body());
            logger.info("Attempting to <parseInt> the ctx.body()");
            accountService.deleteAccount(id);
            ctx.status(200);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            ctx.status(400);
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            ctx.status(400);
            throw new RuntimeException(e);
        }
    }

}
