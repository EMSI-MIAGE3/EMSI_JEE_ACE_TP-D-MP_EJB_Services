package ma.emsi.miage3.ecommerce.dao;

import ma.emsi.miage3.ecommerce.models.User;
import ma.emsi.miage3.ecommerce.models.UserRole;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserDAO {
  public User add(User user);
  public boolean delete(Integer userID);
  public User update(User user);
  public User get(Integer userID);
  public List<User> getAll();
  public List<User> getAllByRole(UserRole role);
  public User findByUserName(String userName);
  public User findByEmail(String email);
  public User findByUserNameAndPassword(String userName, String password);
  public List<User> searchByUserName(String predicat);
}
