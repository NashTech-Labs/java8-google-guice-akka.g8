package com.knoldus.config;

import akka.actor.ActorRef;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import com.knoldus.actors.FetchUserActor;
import com.knoldus.actors.PrintUserActor;
import com.knoldus.service.UserService;
import com.knoldus.service.impl.UserServiceImpl;
import com.knoldus.supervisor.ActorSupervisor;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Harmeet Singh(Taara) on 30/9/16.
 */
public class ActorSystemModuleTest {

    private static Injector injector;

    @BeforeClass
    public static void init() {
        injector = Guice.createInjector(new ActorSystemModule());
    }

    @Test
    public void testUserServiceBindings() {
        UserService service = injector.getInstance(Key.get(UserService.class,
                Names.named(UserService.BEAN_NAME)));

        assertThat(service, is(notNullValue()));
        assertThat(service, instanceOf(UserServiceImpl.class));
    }

    @Test
    public void testPrintUserActorDependency() {
        Provider<ActorRef> provider = injector.getProvider(Key.get(ActorRef.class,
                Names.named(PrintUserActor.ACTOR_NAME)));

        assertThat(provider.get(), is(not(nullValue())));
    }

    @Test
    public void testFetchUserActorDependency() {
        Provider<ActorRef> provider = injector.getProvider(Key.get(ActorRef.class,
                Names.named(FetchUserActor.ACTOR_NAME)));

        assertThat(provider.get(), is(not(nullValue())));
    }

    @Test
    public void testActorSupervisorActorDependency() {
        Provider<ActorRef> provider = injector.getProvider(Key.get(ActorRef.class,
                Names.named(ActorSupervisor.ACTOR_NAME)));

        assertThat(provider.get(), is(not(nullValue())));
    }
}
