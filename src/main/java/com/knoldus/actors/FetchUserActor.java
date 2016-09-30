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
 * Created by harmeet on 20/8/16.
 */
public class FetchUserActor extends AbstractLoggingActor {

    public static final String ACTOR_NAME = "fetchUserActor";

    private final UserService userService;
    private final ActorRef printUsersActorRef;

    private FetchUserActor(final ActorRef printUsersActorRef, final UserService userService) {
        this.userService = userService;
        this.printUsersActorRef = printUsersActorRef;
    }

    public static Props props(final ActorRef printUsersActorRef, final UserService userService) {
        return Props.create(FetchUserActor.class, printUsersActorRef, userService);
    }

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
