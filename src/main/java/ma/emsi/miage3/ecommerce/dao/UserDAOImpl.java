package ma.emsi.miage3.ecommerce.dao;

import ma.emsi.miage3.ecommerce.models.ShoppingCart;
import ma.emsi.miage3.ecommerce.models.User;
import ma.emsi.miage3.ecommerce.models.UserRole;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class UserDAOImpl implements UserDAO {

  @PersistenceContext(unitName = "unite1")
  private EntityManager entityManager;



  private User merge(User user){
    try {
      entityManager.merge(user);
      return user;
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  @Transactional
  public User add(User user) {
    try {
      System.out.println("BEFORE ADD : " + user);
      user = this.merge(user);
      System.out.println("AFTER ADD : " + user);
      if(user != null && user.getRole().equals(UserRole.client)){
        ShoppingCart shoppingCart = new ShoppingCart(user);
        entityManager.merge(shoppingCart);
      }
      return user;
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public boolean delete(Integer userID) {
    try {
      User user = this.get(userID);
      if(user.getRole().equals(UserRole.client)){
        entityManager.remove(user.getShoppingCart());
      }
      entityManager.remove(user);
      return true;
    }
    catch (Exception e){
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public User update(User user) {
    System.out.println("BEFORE UPDATE : " + user);
    User update = null;
    //update.map(user);
    update = this.merge(user);
    System.out.println("AFTER UPDATE : " + update);
    return update ;
  }

  @Override
  public User get(Integer userID) {
    try {
      return entityManager.find(User.class, userID);
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<User> getAll() {
    try {
      return (List<User>) entityManager.createQuery("select u from User u").getResultList();
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<User> getAllByRole(UserRole role) {
    try {
      String jpql = "select u from User u where u.role = :role";
      Query query = entityManager.createQuery(jpql);
      query.setParameter("role", role);
      List<User> users = (List<User>) query.getResultList();
      System.out.println(users);
      return users;
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public User findByUserName(String userName) {
    try {
      return (User) entityManager.createQuery("select u from User u where u.userName = '" + userName + "'").getResultList().stream().findFirst().orElse(null);
    }
    catch(NoResultException e){
      e.printStackTrace();
      return null;
    }
    catch(NonUniqueResultException e){
      e.printStackTrace();
      return null;
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public User findByEmail(String email) {
    try {
      return (User) entityManager.createQuery("select u from User u where u.email = '" + email + "'").getResultList().stream().findFirst().orElse(null);
    }
    catch(NoResultException e){
      e.printStackTrace();
      return null;
    }
    catch(NonUniqueResultException e){
      e.printStackTrace();
      return null;
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public User findByUserNameAndPassword(String userName, String password) {
    try {
      return (User) entityManager.createQuery("select u from User u where u.userName = '" + userName + "' and u.password = '" + password + "'").getResultList().stream().findFirst().orElse(null);
    }
    catch(NoResultException e){
      e.printStackTrace();
      return null;
    }
    catch(NonUniqueResultException e){
      e.printStackTrace();
      return null;
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<User> searchByUserName(String predicat) {
    try {
      return (List<User>) entityManager.createQuery("select u from User u where u.userName LIKE '%" + predicat + "%'").getResultList();
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
}
