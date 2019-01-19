package ma.emsi.miage3.ecommerce.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SHOPPING_CARTS", schema = "EMSI_ECOMMERCE")
public class ShoppingCart implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "client_id", nullable = false)
  private User clientOwner;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "shopping_cart_id")
  private List<ShoppingCartItem> shoppingCartItems;

  @Transient
  private Double totalAmount;

  public ShoppingCart() {
    super();
  }

  public ShoppingCart(User clientOwner) {
    super();
    this.clientOwner = clientOwner;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User getClientOwner() {
    return clientOwner;
  }

  public void setClientOwner(User clientOwner) {
    this.clientOwner = clientOwner;
  }

  public List<ShoppingCartItem> getShoppingCartItems() {
    return shoppingCartItems;
  }

  public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
    this.shoppingCartItems = shoppingCartItems;
  }

  public Double getTotalAmount() {
    this.totalAmount = 0.0;
    this.getShoppingCartItems().forEach(item -> totalAmount += item.getArticle().getPrice());
    return this.totalAmount;
  }


  @Override
  public String toString() {
    return "ShoppingCart{" +
            "id=" + id +
            ", client_id" + clientOwner.getId() +
            ", shoppingCartItems=" + shoppingCartItems +
            ", totalAmount=" + totalAmount +
            '}';
  }
}
