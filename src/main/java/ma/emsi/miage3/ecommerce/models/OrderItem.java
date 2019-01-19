package ma.emsi.miage3.ecommerce.models;

import ma.emsi.miage3.ecommerce.dao.ArticleDAO;
import ma.emsi.miage3.ecommerce.ejbservices.ArticleRemote;
import ma.emsi.miage3.ecommerce.rulesServices.ArticleRule;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ORDER_ITEMS", schema = "EMSI_ECOMMERCE")
public class OrderItem implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "article_id", nullable = false)
  private Article article;

  @Column(nullable = false)
  private Integer quantity;

  @Column(nullable = false)
  private Double price;


  public OrderItem() {
    super();
  }

  public OrderItem(Order order, Article article, Integer quantity, Double price) {
    super();
    this.order = order;
    this.article = article;
    this.quantity = quantity;
    this.price = price;
  }

  public OrderItem(Article article, Integer quantity, Double price) {
    super();
    this.article = article;
    this.quantity = quantity;
    this.price = price;
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

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "OrderItem{" +
            "id=" + id +
            ", order_id=" + order.getId() +
            ", article=" + article +
            ", quantity=" + quantity +
            ", price=" + price +
            '}';
  }
}