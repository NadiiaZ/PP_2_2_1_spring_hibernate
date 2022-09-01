package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   @Transactional
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @Transactional
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Transactional
   public User getUserByCar(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
              "from User as user where user.car.model = :model and user.car.series = :serial");
      query.setParameter("model", model);
      query.setParameter("serial", series);
      return query.getSingleResult();
   }

}
