package com.knoldus.service.impl;

import com.knoldus.service.UserService;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import javaslang.collection.List;
import com.knoldus.models.User;

import java.util.concurrent.CompletableFuture;

/**
 * Created by Harmeet Singh(Taara) on 29/9/16.
 */
public class UserServiceImpl implements UserService {

    private Config config = ConfigFactory.load();

    @Override
    public CompletableFuture<List<User>> findAllUsersByQuery(String query) {
        return CompletableFuture.supplyAsync(() ->
            List.ofAll(config.getStringList(query+".names"))
                .map(name -> new User(name))
        );
    }
}
