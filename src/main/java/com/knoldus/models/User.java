package com.knoldus.models;

import java.util.Objects;

/**
 * Created by Harmeet Singh(Taara) on 29/9/16.
 */
public class User {

    private final String name;

    public User(final String name) {
        this.name = name;
    }

    public final String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj != null && getClass() == obj.getClass()) {
            User notification = (User) obj;
            return Objects.equals(this.name, notification.name);
        }
        return false;

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
