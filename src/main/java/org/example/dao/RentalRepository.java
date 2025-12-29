package org.example.dao;

import org.example.domain.Rental;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class RentalRepository extends GenericRepository<Rental> {
    public RentalRepository(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    public Rental getAnyUnreturnedItem() {
        Query<Rental> query = getCurrentSession().createQuery("select r from Rental r where r.returnDate is null", Rental.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
