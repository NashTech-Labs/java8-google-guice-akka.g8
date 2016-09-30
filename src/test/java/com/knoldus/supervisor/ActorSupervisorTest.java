package com.knoldus.supervisor;

import akka.actor.*;
import akka.testkit.JavaTestKit;
import akka.util.Timeout;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

import java.util.concurrent.TimeUnit;

import static akka.pattern.PatternsCS.ask;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by harmeet on 30/9/16.
 */
public class ActorSupervisorTest {

    private static ActorSystem system;

    @BeforeClass
    public static void init() {
        system = ActorSystem.create();
    }

    @AfterClass
    public static void destroy() {
        JavaTestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testProps() {
        Props props = ActorSupervisor.props();

        assertThat(props.actorClass(), is(equalTo(ActorSupervisor.class)));
    }

    @Test
    public void testReceiveMessage() throws Exception {

        final Props props = Props.create(TestActor.class);

        final Props supervisorProps = ActorSupervisor.props();
        final ActorRef supervisor = system.actorOf(supervisorProps, "ActorSupervisor");
        ActorRef childActor = (ActorRef) ask(supervisor, props, Timeout.apply(1, TimeUnit.SECONDS))
                .toCompletableFuture().get(2, TimeUnit.SECONDS);

        assertTrue(childActor.path().parent().name().equals("ActorSupervisor"));
    }

    final static class TestActor extends AbstractLoggingActor {
        public TestActor() {}
    }
}
