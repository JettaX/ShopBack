package com.okon.okon.repository;

import com.okon.okon.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryJPA implements UserRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public User insert(User user) {
        return em.merge(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = em
                .createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.ofNullable(user);
    }
}
