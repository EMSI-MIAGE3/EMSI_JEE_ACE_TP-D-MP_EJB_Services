package ma.emsi.miage3.ecommerce.rulesServices;

import ma.emsi.miage3.ecommerce.dao.UserDAO;
import ma.emsi.miage3.ecommerce.models.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserRuleImpl implements UserRule {
  @EJB
  public UserDAO userDAO;


  // **************************************************************************************************************

  private boolean isValidEmail(String email){
    if(email != null){
      String emailRegex = "[a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}[.] {0,1}[a-zA-Z]+";
      return email.matches(emailRegex);
    }
    else
      return false;
  }

  private boolean isUniqueEmail(String email, Integer userID){
    User user = userDAO.findByEmail(email);
    return (  (user == null)   ||    (userID != null && user.getId().equals(userID))  );
  }

  @Override
  public boolean isCheckedEmail(String email, Integer userID){
    return this.isValidEmail(email) && this.isUniqueEmail(email, userID);
  }


 // **************************************************************************************************************

  private boolean isValidUserName(String userName){
    if(userName != null){
      String userNameRegex = "^[a-zA-Z0-9_]{5,15}$";
      return userName.matches(userNameRegex);
    }
    else
      return false;
  }

  private boolean isUniqueUserName(String userName, Integer userID){
    User user = userDAO.findByUserName(userName);
    return (  (user == null)   ||    (userID != null && user.getId().equals(userID))  );
  }

  @Override
  public boolean isCheckedUserName(String userName, Integer userID){
    return this.isValidUserName(userName) && this.isUniqueUserName(userName, userID);
  }


  // **************************************************************************************************************

  private boolean isValidPassword(String password){
    if(password != null){
      String passwordRegex = ".{6,30}";
      return password.matches(passwordRegex);
    }
    else
      return false;
  }

  @Override
  public boolean isCheckedPassword(String password){
    return this.isValidPassword(password);
  }

  // **************************************************************************************************************

  private boolean isValidPhone(String phone){
    if(phone != null){
      String phoneRegex = "(0|\\+212)([0-9]{9})";
      return phone.matches(phoneRegex);
    }
    else
      return true;
  }

  @Override
  public boolean isCheckedPhone(String phone){
    return this.isValidPhone(phone);
  }


}
