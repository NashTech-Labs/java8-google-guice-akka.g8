package com.knoldus.config;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Timeout;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.knoldus.actors.FetchUserActor;
import com.knoldus.actors.PrintUserActor;
import com.knoldus.service.UserService;
import com.knoldus.service.impl.UserServiceImpl;
import com.knoldus.supervisor.ActorSupervisor;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static akka.pattern.PatternsCS.ask;

/**
 * Created by harmeet on 29/9/16.
 */
public class ActorSystemModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ActorSystem.class).toInstance(ActorSystem.apply());
        bind(UserService.class).annotatedWith(Names.named(UserService.BEAN_NAME))
                .to(UserServiceImpl.class).in(Singleton.class);
    }

    @Provides
    @Named(ActorSupervisor.ACTOR_NAME)
    public final ActorRef actorSupervisor(final ActorSystem system){
        return system.actorOf(ActorSupervisor.props());
    }

    @Provides
    @Named(PrintUserActor.ACTOR_NAME)
    public final ActorRef printUserActor(@Named(ActorSupervisor.ACTOR_NAME) final ActorRef supervisor) throws Exception {
        final CompletionStage<Object> future = ask(supervisor, PrintUserActor.props(),
                Timeout.apply(100, TimeUnit.MILLISECONDS));
        return (ActorRef) future.toCompletableFuture().get(150, TimeUnit.MILLISECONDS);
    }

    @Provides
    @Named(FetchUserActor.ACTOR_NAME)
    public final ActorRef fetchUserActor(@Named(ActorSupervisor.ACTOR_NAME) final ActorRef supervisor,
                                         @Named(PrintUserActor.ACTOR_NAME) final ActorRef printUserActor,
                                         @Named(UserService.BEAN_NAME) final UserService userService) throws Exception {
        final Props props = FetchUserActor.props(printUserActor, userService);
        final CompletionStage<Object> future = ask(supervisor, props,
                Timeout.apply(100, TimeUnit.MILLISECONDS));
        return (ActorRef) future.toCompletableFuture().get(200, TimeUnit.MILLISECONDS);
    }
}
