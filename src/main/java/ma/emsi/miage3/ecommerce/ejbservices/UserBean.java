package ma.emsi.miage3.ecommerce.ejbservices;

import ma.emsi.miage3.ecommerce.dao.UserDAO;
import ma.emsi.miage3.ecommerce.models.User;
import ma.emsi.miage3.ecommerce.models.UserRole;
import ma.emsi.miage3.ecommerce.rulesServices.UserRule;
import ma.emsi.miage3.ecommerce.rulesServices.UserRuleImpl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserBean implements UserRemote {

  @EJB
  UserDAO userDAO;

  @EJB
  UserRule rule;

  @Override
  public List<User> getAllUsers() {
    return userDAO.getAll();
  }

  @Override
  public List<User> getAllClients() {
    return userDAO.getAllByRole(UserRole.client);
  }

  @Override
  public List<User> getAllAdministrators() {
    return userDAO.getAllByRole(UserRole.administrateur);
  }

  @Override
  public User getUser(Integer userID) {
    return userDAO.get(userID);
  }

  private User mergeUser(User user){
    if (
            user != null &&
                    rule.isCheckedEmail(user.getEmail(), user.getId()) &&
                    rule.isCheckedUserName(user.getUserName(), user.getId()) &&
                    rule.isCheckedPhone(user.getPhone()) &&
                    rule.isCheckedPassword(user.getPassword())
    ){
      return userDAO.add(user);
    }
    else {
      return null;
    }
  }

  @Override
  public User addUser(User user) {
    return this.mergeUser(user);
  }

  @Override
  public User updateUser(User user) {
    return this.mergeUser(user);
  }

  @Override
  public boolean deleteUser(Integer userID) {
    return userDAO.delete(userID);
  }

  @Override
  public User authenticateByUserNameAndPassword(String userName, String password) {
    return userDAO.findByUserNameAndPassword(userName, password);
  }

  @Override
  public User findUserByUserName(String userName) {
    return userDAO.findByUserName(userName);
  }

  @Override
  public User findUserByEmail(String email) {
    return userDAO.findByEmail(email);
  }

  @Override
  public List<User> searchUsersByUserName(String predicat) {
    return userDAO.searchByUserName(predicat);
  }
}
