package ma.emsi.miage3.ecommerce.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SHOPPING_CART_ITEMS", schema = "EMSI_ECOMMERCE")
public class ShoppingCartItem implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "shopping_cart_id", nullable = false)
  private ShoppingCart shoppingCart;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "article_id", nullable = false)
  private Article article;

  @Column(nullable = false)
  private Integer quantity;



  public ShoppingCartItem() {
    super();
  }

  public ShoppingCartItem(Article article, Integer quantity) {
    super();
    this.article = article;
    this.quantity = quantity;
  }



  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Article getArticle() {
    return article;
  }

  public void setArticle(Article article) {
    this.article = article;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public ShoppingCart getShoppingCart() {
    return shoppingCart;
  }

  public void setShoppingCart(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  @Override
  public String toString() {
    return "OrderItem{" +
            "id=" + id +
            ", shopping_cart_id=" + shoppingCart.getId() +
            ", article=" + article +
            ", quantity=" + quantity +
            '}';
  }
}
