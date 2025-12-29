package org.example.dao;

import org.example.domain.Payment;
import org.hibernate.SessionFactory;

public class PaymentRepository extends GenericRepository<Payment> {
    public PaymentRepository(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
