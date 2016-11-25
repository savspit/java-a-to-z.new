package ru.shestakov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserHibernateRepository implements HibernateRepository {
    private static final Logger Log = LoggerFactory.getLogger(UserHibernateRepository.class);

    private final HibernateTemplate template;

    @Autowired
    public UserHibernateRepository(final HibernateTemplate template) {
        this.template = template;
    }

    @Transactional
    @Override
    public User save(User model) {
        this.template.save(model);
        return model;
    }

    @Override
    public List<User> getAll() {
        return (List<User>) this.template.find("from User");
    }
}
