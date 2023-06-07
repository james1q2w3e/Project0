package com.revature.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.controllers.UserController;
import com.revature.models.User;
import io.javalin.Javalin;
import io.javalin.json.JsonMapper;
import jakarta.validation.constraints.NotNull;

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

    private Javalin app = Javalin.create(config -> config.jsonMapper(gsonMapper))
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
            });

//    app.get("/users", UserController::handleGetAll);
//    app.get("/users/{id}", UserController::handleGetById);

    public void start(int port) {
        app.start(port);
    }
}
