package com.asso.stamp.business;

import com.asso.stamp.model.Stamp;
import java.util.List;
import javax.persistence.*;
import javax.ejb.Stateless;

@Stateless
public class StampEJB {

    // ======================================
    // =             Attributes             =
    // ======================================
    @PersistenceContext(unitName = "jsfExamplePU")
    private EntityManager em;

    // ======================================
    // =           Public Methods           =
    // ======================================
    public List<Stamp> findAll() {
        Query query = em.createNamedQuery(Stamp.FIND_ALL);
        return query.getResultList();
    }

    public Stamp create(Stamp stamp) {
        em.persist(stamp);
        return stamp;
    }

    public Stamp update(Stamp stamp) {
        return em.merge(stamp);
    }

    public void delete(List<Stamp> list) {
        for (Stamp stamp : list) {
            delete(stamp);
        }
    }

    public void delete(Stamp stamp) {
        em.remove(em.merge(stamp));
    }
}
