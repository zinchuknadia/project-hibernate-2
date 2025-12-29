package org.example.dao;

import org.example.domain.Language;
import org.hibernate.SessionFactory;

public class LanguageRepository extends GenericRepository<Language> {
    public LanguageRepository(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
