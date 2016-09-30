package com.knoldus.actors;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import javaslang.collection.List;
import com.knoldus.models.User;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

/**
 * Created by harmeet on 29/9/16.
 */
public class PrintUserActor extends AbstractLoggingActor {

    public static final String ACTOR_NAME = "printUserActor";

    private PrintUserActor() {}

    public static Props props(){
        return Props.create(PrintUserActor.class);
    }

    @Override
    public PartialFunction<Object, BoxedUnit> receive() {
        return ReceiveBuilder.
                match(PrintUser.class, printUsers -> {
                    log().info("Message Match... {}", PrintUser.class.getSimpleName());

                    printUsers.getUsers().forEach(System.out::println);
                }).
                matchAny(msg -> log().error("unknown message {}", msg)).
                build();
    }

    public static class PrintUser {
        private final List<User> users;

        public PrintUser(final List<User> users) {
            this.users = users;
        }

        public final List<User> getUsers() {
            return this.users;
        }
    }
}
