package org.example.dao;

import org.example.domain.FilmText;
import org.hibernate.SessionFactory;

public class FilmTextRepository extends GenericRepository<FilmText> {
    public FilmTextRepository(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
