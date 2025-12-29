package org.example.dao;

import org.example.domain.Country;
import org.hibernate.SessionFactory;

public class CountryRepository extends GenericRepository<Country> {
    public CountryRepository(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}
