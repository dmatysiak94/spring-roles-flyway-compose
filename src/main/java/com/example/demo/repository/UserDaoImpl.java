package com.example.demo.repository;

import com.example.demo.entity.UsersEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImpl implements IUserDao {

    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<UsersEntity> listUsers() {

        Query<UsersEntity> theQuery =
                (Query<UsersEntity>) entityManager.createQuery("from UsersEntity",
                        UsersEntity.class);

        List<UsersEntity> usersList = theQuery.getResultList();

        return usersList;
    }

    @Override
    public String addUser(UsersEntity user) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.save(user);

        return "user added";
    }

    @Override
    public String updateUser(UsersEntity user) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(user);
        return "user Updated";
    }

    @Override
    public String deleteUser(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery =
                currentSession.createQuery(
                        "delete from UsersEntity where id=:id");
        theQuery.setParameter("id", id);

        theQuery.executeUpdate();

        return "user Deleted ";
    }

    @Override
    public boolean isPresent(Long id) {
        Session session = entityManager.unwrap(Session.class);

        return Objects.nonNull(session.createSQLQuery("select 1 from users where user_id =:id")
                .setParameter("id", id)
                .uniqueResult());

    }
}
