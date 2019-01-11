package ma.emsi.miage3.ecommerce.rulesServices;

import javax.ejb.Local;

@Local
public interface UserRule {
  boolean isCheckedEmail(String email, Integer userID);

  boolean isCheckedUserName(String userName, Integer userID);

  boolean isCheckedPassword(String password);

  boolean isCheckedPhone(String phone);
}
