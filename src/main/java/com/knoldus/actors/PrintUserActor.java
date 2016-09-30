package com.knoldus.actors;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import javaslang.collection.List;
import com.knoldus.models.User;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

/**
 * Created by Harmeet Singh(Taara) on 29/9/16.
 *
 * This actor is used to print the users only. We are never creating actors, which are not pass the message
 * to another actor. This is used of our demo purpose.
 */
public class PrintUserActor extends AbstractLoggingActor {

    public static final String ACTOR_NAME = "printUserActor";

    private PrintUserActor() {}

    /**
     * This method is used for create the Pops of PrintUserActor actor.
     * @return PrintUserActor actor props
     * @see PrintUserActor
     */
    public static Props props(){
        return Props.create(PrintUserActor.class);
    }

    /**
     * This method is used for handle received message of actor.
     * @return
     */
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

    /**
     * This is class for handling message of PrintUserActor.
     * @see PrintUserActor
     */
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
