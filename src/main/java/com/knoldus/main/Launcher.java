package com.knoldus.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.knoldus.config.ActorSystemModule;

/**
 * Created by harmeet on 29/9/16.
 */
public class Launcher {

    public static void main(String... args) {
        Injector injector = Guice.createInjector(new ActorSystemModule());
        App app = injector.getInstance(App.class);

        app.fetchUsers("SELECT name FROM users");
    }
}
