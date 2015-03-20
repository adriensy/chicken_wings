package fr.esgi.demo.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Pro on 18/03/2015.
 */
@Transactional
public class UserDaoImpl {
    @PersistenceContext
    private EntityManager em;

    public Long save(User user) {
        em.persist(user);
        return user.getId();
    }

    public List<User>getAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}
