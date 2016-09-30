package com.knoldus.actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import com.knoldus.actors.PrintUserActor.PrintUser;
import javaslang.collection.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Harmeet Singh(Taara) on 29/9/16.
 */
public class PrintUserActorTest {

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
        Props props = PrintUserActor.props();

        assertThat(props.actorClass(), is(equalTo(PrintUserActor.class)));
    }

    @Test
    public void testReceiveMessage() {
        PrintUser printUser = new PrintUser(List.empty());

        new JavaTestKit(system) {{
            Props props = PrintUserActor.props();
            final ActorRef printUserActor = system.actorOf(props);
            printUserActor.tell(printUser, ActorRef.noSender());

            expectNoMsg(duration("2 second"));
        }};
    }
}
