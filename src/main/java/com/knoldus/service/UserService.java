package com.knoldus.service;

import javaslang.collection.List;
import com.knoldus.models.User;

import java.util.concurrent.CompletableFuture;

/**
 * Created by harmeet on 29/9/16.
 */
public interface UserService {
    String BEAN_NAME = "userService";

    CompletableFuture<List<User>> findAllUsersByQuery(final String query);
}
