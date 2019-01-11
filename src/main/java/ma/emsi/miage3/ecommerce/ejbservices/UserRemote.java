package ma.emsi.miage3.ecommerce.ejbservices;

import ma.emsi.miage3.ecommerce.models.User;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface UserRemote {
  public List<User> getAllUsers();
  public User getUser(Integer userID);
  public User addUser(User user);
  public User updateUser(User user);
  public boolean deleteUser(Integer userID);
  public User authenticateByUserNameAndPassword(String userName, String password);
  public User findUserByUserName(String userName);
  public User findUserByEmail(String email);
  public List<User> searchUsersByUserName(String predicat);
}
