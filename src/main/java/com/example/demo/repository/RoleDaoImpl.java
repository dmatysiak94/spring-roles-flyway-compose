package com.example.demo.repository;

import com.example.demo.entity.AvailableRoleEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@Repository
public class RoleDaoImpl implements IRoleDao {

    private EntityManager entityManager;

    @Autowired
    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<AvailableRoleEntity> listRoles() {
        Query<AvailableRoleEntity> theQuery =
                (Query<AvailableRoleEntity>) entityManager.createQuery("from AvailableRoleEntity",
                        AvailableRoleEntity.class);

        List<AvailableRoleEntity> rolesList = theQuery.getResultList();

        entityManager.close();

        return rolesList;
    }

    @Override
    public String addRole(AvailableRoleEntity role) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(role);

        return "role added";
    }

    @Override
    public void updateRole(AvailableRoleEntity role) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(role);
    }

    @Override
    public String deleteRole(AvailableRoleEntity role) {
        Session currentSession = entityManager.unwrap(Session.class);

        int id = role.getRoleId();
        Query theQuery =
                currentSession.createQuery(
                        "delete from AvailableRoleEntity where role_id=:id");
        theQuery.setParameter("id", id);

        theQuery.executeUpdate();

        return "Role deleted";
    }

    @Override
    public boolean isPresent(String role) {
        Session session = entityManager.unwrap(Session.class);

        return Objects.nonNull(session.createSQLQuery("select 1 from available_roles where role = :role")
                .setParameter("role", role)
                .uniqueResult());
    }

}
