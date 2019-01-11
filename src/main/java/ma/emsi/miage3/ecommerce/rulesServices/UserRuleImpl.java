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
      System.out.println("EMAIL : " + email + "->" +  email.matches(emailRegex));
      return email.matches(emailRegex);
    }
    else
      return false;
  }

  private boolean isUniqueEmail(String email, Integer userID){
    User user = userDAO.findByEmail(email);
    System.out.println("EMAIL-UNIQ : " + (  (user == null)   ||    (userID != null && user.getId() != userID)  ));
    return (  (user == null)   ||    (userID != null && user.getId() != userID)  );
  }

  @Override
  public boolean isCheckedEmail(String email, Integer userID){
    System.out.println("EMAIL-CHECK : " + (this.isValidEmail(email) && this.isUniqueEmail(email, userID)));
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
    return (  (user == null)   ||    (userID != null && user.getId() != userID)  );
  }

  @Override
  public boolean isCheckedUserName(String userName, Integer userID){
    System.out.println("UserName : " + userName  + "->" +  (this.isValidUserName(userName) && this.isUniqueUserName(userName, userID)));
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
    System.out.println("PWD : " + password + "->" + this.isValidPassword(password));
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
    System.out.println("PHONE : " + phone + "->" + this.isValidPhone(phone));
    return this.isValidPhone(phone);
  }


}
