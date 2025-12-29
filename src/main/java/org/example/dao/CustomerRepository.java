package org.example.dao;

import org.example.domain.Customer;
import org.hibernate.SessionFactory;

public class CustomerRepository extends GenericRepository<Customer> {
    public CustomerRepository(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
