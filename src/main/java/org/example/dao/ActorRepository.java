package org.example.dao;

import org.example.domain.Actor;
import org.hibernate.SessionFactory;

public class ActorRepository extends GenericRepository<Actor> {
    public ActorRepository(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
