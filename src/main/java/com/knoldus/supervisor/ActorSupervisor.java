package com.knoldus.supervisor;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

/**
 * Created by Harmeet Singh(Taara) on 29/9/16.
 *
 * This actor is used to create its child actor. It will receive the props from its message and send the actorRef
 * to sender. This design makes the actor loosely coupled from its supervisor.
 */
public class ActorSupervisor extends AbstractLoggingActor {

    public static final String ACTOR_NAME = "actorSupervisor";

    /**
     * This method is used for create the Pops of ActorSupervisor actor.
     * @return ActorSupervisor actor props
     * @see ActorSupervisor
     */
    public static Props props() {
        return Props.create(ActorSupervisor.class);
    }

    /**
     * This method is used for handle received message of actor.
     * @return
     */
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
