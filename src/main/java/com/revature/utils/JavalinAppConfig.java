package com.revature.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.controllers.AccountController;
import com.revature.controllers.UserController;
import io.javalin.Javalin;
import io.javalin.json.JsonMapper;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinAppConfig {

    Gson gson = new GsonBuilder().create();
    JsonMapper gsonMapper = new JsonMapper() {
        @Override
        public String toJsonString(@NotNull Object obj, @NotNull Type type) {
            return gson.toJson(obj, type);
        }

        @Override
        public <T> T fromJsonString(@NotNull String json, @NotNull Type targetType) {
            return gson.fromJson(json, targetType);
        }
    };

    private static final Logger logger = LoggerFactory.getLogger(JavalinAppConfig.class);

    private Javalin app = Javalin.create(config -> config.jsonMapper(gsonMapper))

            .before(ctx -> {
                //this logic will run before all requests to the server
                logger.info(ctx.method() + " request was called to: " + ctx.fullUrl());
            })
            //routes will declare all our possible paths
            .routes(() -> {
                //each path will allow us to group like-methods
                path("users", () -> {
                   //declare routes and methods
                    get(UserController::handleGetAll);
                    post(UserController::handleCreate);
                    put(UserController::handleUpdate);
                    delete(UserController::handleDelete);
                    ///users/{id}
                    path("{id}", () -> {
                        get(UserController::handleGetById);
                    });
                });
                path("accounts", () -> {
                    get(AccountController::handleGetAll);
                    post(AccountController::handleCreate);
                    put(AccountController::handleUpdate);
                    delete(AccountController::handleDelete);
                    //accounts/{id}
                    path("{id}", () -> {
                        get(AccountController::handleGetById);
                    });
                });
            });


    public void start(int port) {
        app.start(port);
    }
}
