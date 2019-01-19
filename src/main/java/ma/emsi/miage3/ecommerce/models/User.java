package ma.emsi.miage3.ecommerce.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USERS", schema = "EMSI_ECOMMERCE")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false, unique = true)
  private String userName;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = true)
  private String city;

  @Column(nullable = true)
  private String phone;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private UserRole role;

  @OneToOne(mappedBy = "clientOwner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private ShoppingCart shoppingCart;

  @OneToMany(mappedBy = "clientOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Order> orders;

  public User() {
    super();
  }

  public User(String lastName, String firstName, String userName, String email, String city, String phone, String password, UserRole role) {
    super();
    this.lastName = lastName;
    this.firstName = firstName;
    this.userName = userName;
    this.email = email;
    this.city = city;
    this.phone = phone;
    this.password = password;
    this.role = role;
  }

  public User map(User user){
    this.lastName = (user.lastName == null || user.lastName.equals(""))? this.lastName : user.lastName;
    this.firstName = (user.firstName == null || user.firstName.equals(""))? this.firstName : user.firstName;
    this.userName = (user.userName == null || user.userName.equals(""))? this.userName : user.userName;
    this.email = (user.email == null || user.email.equals(""))? this.email : user.email;
    this.city = (user.city == null || user.city.equals(""))? this.city : user.city;
    this.phone = (user.phone == null || user.phone.equals(""))? this.phone : user.phone;
    this.password = (user.password == null || user.password.equals(""))? this.password : user.password;
    this.role = (user.role == null)? this.role : user.role;
    return this;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public ShoppingCart getShoppingCart() {
    return shoppingCart;
  }

  public void setShoppingCart(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", lastName='" + lastName + '\'' +
            ", firstName='" + firstName + '\'' +
            ", userName='" + userName + '\'' +
            ", email='" + email + '\'' +
            ", city='" + city + '\'' +
            ", phone='" + phone + '\'' +
            ", password='" + password + '\'' +
            ", role=" + role +
            ", shoppingCart=" + shoppingCart +
            ", orders=" + orders +
            '}';
  }
}
