package exercise.controllers;

import io.javalin.http.Handler;

public final class RootController {

    public static Handler welcome = ctx -> {
        ctx.render("index.html");
    };
}
