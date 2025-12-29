package org.example.dao;

import org.example.domain.Store;
import org.hibernate.SessionFactory;

public class StoreRepository extends GenericRepository<Store> {
    public StoreRepository(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
