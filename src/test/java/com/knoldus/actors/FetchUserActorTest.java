package com.knoldus.actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import com.knoldus.actors.FetchUserActor.Query;
import com.knoldus.actors.PrintUserActor.PrintUser;
import com.knoldus.service.impl.UserServiceImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by harmeet on 29/9/16.
 */
public class FetchUserActorTest {

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
        final JavaTestKit probe = new JavaTestKit(system);

        Props props = FetchUserActor.props(probe.getRef(), new UserServiceImpl());

        assertThat(props.actorClass(), is(equalTo(FetchUserActor.class)));
    }

    @Test
    public void testReceiveMessage() {
        new JavaTestKit(system) {{
            final JavaTestKit probe = new JavaTestKit(system);
            final Props props = FetchUserActor.props(probe.getRef(), new UserServiceImpl());

            final ActorRef fetchUserActor = system.actorOf(props);
            fetchUserActor.tell(new Query("SELECT name FROM users"), ActorRef.noSender());
            new AwaitCond(duration("120 second"), duration("100 millis")) {
                @Override
                protected boolean cond() {
                    return probe.msgAvailable();
                }
            };

            probe.expectMsgClass(PrintUser.class);
        }};
    }
}
