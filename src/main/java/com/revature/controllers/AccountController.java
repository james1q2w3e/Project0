package com.revature.controllers;

import com.revature.daos.AccountDAO;
import com.revature.services.AccountService;
import io.javalin.http.Context;

public class AccountController {

    private static final AccountService accountService = new AccountService();
    static AccountDAO accountDAO = new AccountDAO();

    public static void handleGetAll(Context ctx) {
        ctx.status(405);
        ctx.json(accountService.handleGetAllAccounts());
    }

    public static void handleGetById(Context ctx) {
        ctx.status(405);
    }

    public static void handleCreate(Context ctx) {
        ctx.status(405);
    }

    public static void handleUpdate(Context ctx) {
        ctx.status(405);
    }

    public static void handleDelete(Context ctx) {
        ctx.status(405);
    }


}
