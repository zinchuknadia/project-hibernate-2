package org.example.dao;

import org.example.domain.Category;
import org.hibernate.SessionFactory;

public class CategoryRepository extends GenericRepository<Category> {
    public CategoryRepository(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
