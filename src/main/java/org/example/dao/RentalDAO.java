package org.example.dao;

import org.example.domain.Rental;
import org.hibernate.SessionFactory;

public class RentalDAO extends GenericDAO<Rental> {
    public RentalDAO(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }
}
