package org.example.dao;

import org.example.domain.Staff;
import org.hibernate.SessionFactory;

public class StaffRepository extends GenericRepository<Staff> {
    public StaffRepository(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
