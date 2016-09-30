package com.knoldus.main;

import com.knoldus.actors.FetchUserActor;
import com.knoldus.actors.FetchUserActor.Query;
import akka.actor.ActorRef;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Created by Harmeet Singh(Taara) on 29/9/16.
 */
public class App {

    private final ActorRef fetchUserActor;

    @Inject
    public App(@Named(FetchUserActor.ACTOR_NAME) final ActorRef fetchUserActor) {
        this.fetchUserActor = fetchUserActor;
    }

    public void fetchUsers(String query) {
        fetchUserActor.tell(new Query(query), ActorRef.noSender());
    }
}
