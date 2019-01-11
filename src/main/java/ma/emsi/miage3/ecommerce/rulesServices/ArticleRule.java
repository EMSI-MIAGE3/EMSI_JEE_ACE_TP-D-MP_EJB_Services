package ma.emsi.miage3.ecommerce.rulesServices;

import ma.emsi.miage3.ecommerce.models.Article;

import javax.ejb.Local;

@Local
public interface ArticleRule {
  boolean isUniqueName(String name, Integer articleID);

  boolean isUniqueReference(String reference, Integer articleID);

  boolean isValidQuantity(Article article);

  boolean isValidQuantity(Integer articleID, Integer quantity);
}
