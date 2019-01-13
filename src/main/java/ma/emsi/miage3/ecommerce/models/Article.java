package ma.emsi.miage3.ecommerce.models;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "ARTICLES", schema = "EMSI_ECOMMERCE")
public class Article implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, unique = true)
  private String reference;

  @Column(nullable = false, unique = true)
  private String name;

  private String description;

  @Column(nullable = false)
  private Integer stockQuantity;

  @Column(nullable = false)
  private double price;

  @Column
  private String pictureURI;



  public Article() {
    super();
  }

  public Article(String reference, String name, String description, Integer stockQuantity, double price) {
    super();
    this.reference = reference;
    this.name = name;
    this.description = description;
    this.stockQuantity = stockQuantity;
    this.price = price;
  }

  public Article(String reference, String name, String description, Integer stockQuantity, double price, String pictureURI) {
    super();
    this.reference = reference;
    this.name = name;
    this.description = description;
    this.stockQuantity = stockQuantity;
    this.price = price;
    this.pictureURI = pictureURI;
  }

  public Article map(Article article){
    this.reference = article.reference;
    this.name = article.name;
    this.description = article.description;
    this.stockQuantity = article.stockQuantity;
    this.price = article.price;
    this.pictureURI = article.pictureURI;
    return this;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getStockQuantity() {
    return stockQuantity;
  }

  public void setStockQuantity(Integer stockQuantity) {
    this.stockQuantity = stockQuantity;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getPictureURI() {
    return pictureURI;
  }

  public void setPictureURI(String pictureURI) {
    this.pictureURI = pictureURI;
  }

  @Override
  public String toString() {
    return "Article{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", stockQuantity=" + stockQuantity +
            ", price=" + price +
            ", pictureURI='" + pictureURI + '\'' +
            '}';
  }
}
