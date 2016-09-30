package com.knoldus.supervisor;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

/**
 * Created by harmeet on 29/9/16.
 */
public class ActorSupervisor extends AbstractLoggingActor {

    public static final String ACTOR_NAME = "actorSupervisor";

    public static Props props() {
        return Props.create(ActorSupervisor.class);
    }

    @Override
    public PartialFunction<Object, BoxedUnit> receive() {
        return ReceiveBuilder.
                match(Props.class, props -> {
                    log().info("Message Match... {}", Props.class.getSimpleName());

                    sender().tell(context().actorOf(props), ActorRef.noSender());
                }).
                matchAny(msg -> log().error("unknown message {}", msg)).
                build();
    }
}
