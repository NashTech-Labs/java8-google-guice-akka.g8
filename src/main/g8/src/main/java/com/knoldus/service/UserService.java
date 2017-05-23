package com.knoldus.service;

import javaslang.collection.List;
import com.knoldus.models.User;

import java.util.concurrent.CompletableFuture;

/**
 * Created by Harmeet Singh(Taara) on 29/9/16.
 */
public interface UserService {
    String BEAN_NAME = "userService";

    /**
     * This method is used to fetch the users by passes query and return the result in CompletableFuture
     * @param query query for user table
     * @return List<User> Immutable users list
     * @see CompletableFuture
     * @see User
     */
    CompletableFuture<List<User>> findAllUsersByQuery(final String query);
}
