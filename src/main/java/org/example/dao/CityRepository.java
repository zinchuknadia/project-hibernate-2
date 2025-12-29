package org.example.dao;

import org.example.domain.City;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CityRepository extends GenericRepository<City> {
    public CityRepository(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public City getByName(String name) {
        Query<City> query = getCurrentSession().createQuery("select c from City c where c.title = :name", City.class);
        query.setParameter("name", name);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
