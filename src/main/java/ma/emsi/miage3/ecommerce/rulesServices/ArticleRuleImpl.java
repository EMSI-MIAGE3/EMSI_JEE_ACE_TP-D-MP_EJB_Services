package ma.emsi.miage3.ecommerce.rulesServices;

import ma.emsi.miage3.ecommerce.dao.ArticleDAO;
import ma.emsi.miage3.ecommerce.models.Article;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ArticleRuleImpl implements ArticleRule {

  @EJB
  ArticleDAO articleDAO;

  @Override
  public boolean isUniqueName(String name, Integer articleID){
    System.out.println(articleDAO);
    Article article = articleDAO.findByName(name);
    return (  (article == null)   ||    (articleID != null && article.getId() != articleID)  );
  }

  @Override
  public boolean isUniqueReference(String reference, Integer articleID){
    System.out.println(articleDAO);
    Article article = articleDAO.findByReference(reference);
    return (  (article == null)   ||    (articleID != null && article.getId() != articleID)  );
  }

  @Override
  public boolean isValidQuantity(Article article){
    System.out.println(articleDAO);
    return (article != null && article.getStockQuantity() >= 0);
  }

  @Override
  public boolean isValidQuantity(Integer articleID, Integer quantity){
    System.out.println(articleDAO);
    Article article = articleDAO.get(articleID);
    return (article != null && (article.getStockQuantity() - quantity) >= 0);
  }

}
