package org.example.dao;

import org.example.domain.Address;
import org.hibernate.SessionFactory;

public class AddressRepository extends GenericRepository<Address> {
    public AddressRepository(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
