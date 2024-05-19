package web.dao;

import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        Query query = entityManager.createQuery("from User");
        List<User> allUsers = query.getResultList();
        return allUsers;
    }

    @Override
    public void saveUser(User user) {
        User dbUser = entityManager.merge(user);
        user.setId(dbUser.getId());
    }

    @Override
    public User getUser(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public void deleteUser(int id) {
        Query query = entityManager.createQuery("delete from User where id=:userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }
}
