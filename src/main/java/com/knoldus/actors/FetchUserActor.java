package com.knoldus.actors;

import com.knoldus.actors.PrintUserActor.PrintUser;
import akka.actor.*;
import akka.japi.pf.ReceiveBuilder;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;
import com.knoldus.service.UserService;

import static akka.pattern.PatternsCS.pipe;
import java.util.concurrent.CompletableFuture;

/**
 * Created by Harmeet Singh(Taara) on 20/8/16.
 *
 * This actor used to fetch the details of users from query and pass the CompletableFuture using pipe
 * to next actor
 */
public class FetchUserActor extends AbstractLoggingActor {

    public static final String ACTOR_NAME = "fetchUserActor";

    private final UserService userService;
    private final ActorRef printUsersActorRef;

    private FetchUserActor(final ActorRef printUsersActorRef, final UserService userService) {
        this.userService = userService;
        this.printUsersActorRef = printUsersActorRef;
    }

    /**
     * This method is used for create the Pops of FetchUserActor actor.
     * @param printUsersActorRef reference of another actor for send the message
     * @param userService used to fetch the users
     * @return FetchUserActor actor props
     * @see FetchUserActor
     */
    public static Props props(final ActorRef printUsersActorRef, final UserService userService) {
        return Props.create(FetchUserActor.class, printUsersActorRef, userService);
    }

    /**
     * This method is used for handle received message of actor.
     * @return
     */
    @Override
    public PartialFunction<Object, BoxedUnit> receive() {
        return ReceiveBuilder.
                match(Query.class, query -> {
                    log().info("Message Match... {}", Query.class.getSimpleName());

                    CompletableFuture<PrintUser> future = userService.findAllUsersByQuery(query.query)
                            .thenApplyAsync(users -> new PrintUser(users));
                    pipe(future, context().dispatcher()).to(printUsersActorRef);
                }).
                matchAny(msg -> log().error("unknown message: "+ msg)).
                build();
    }

    /**
     * This is class for handling message of FetchUserActor.
     * @see FetchUserActor
     */
    public static class Query {
        private final String query;

        public Query(final String query) {
            this.query = query;
        }

        public final String getQuery() {
            return this.query;
        }
    }
}
