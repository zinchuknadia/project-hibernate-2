package org.example.dao;

import org.example.domain.Inventory;
import org.hibernate.SessionFactory;

public class InventoryRepository extends GenericRepository<Inventory> {
    public InventoryRepository(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
}
