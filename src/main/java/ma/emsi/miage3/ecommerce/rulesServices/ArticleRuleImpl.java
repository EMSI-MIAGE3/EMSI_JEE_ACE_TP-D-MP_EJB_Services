package ma.emsi.miage3.ecommerce.rulesServices;

import ma.emsi.miage3.ecommerce.dao.ArticleDAO;
import ma.emsi.miage3.ecommerce.models.Article;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ArticleRuleImpl implements ArticleRule {

  @EJB
  ArticleDAO articleDAO;

  @Override
  public boolean isUniqueName(String name, Integer articleID){
    Article article = articleDAO.findByName(name);
    return (  (article == null)   ||    (articleID != null && article.getId().equals(articleID))  );
  }

  @Override
  public boolean isUniqueReference(String reference, Integer articleID){
    Article article = articleDAO.findByReference(reference);
    return (  (article == null)   ||    (articleID != null && article.getId().equals(articleID))  );
  }

  @Override
  public boolean isValidQuantity(Article article){
    return (article != null && article.getStockQuantity() >= 0);
  }

  @Override
  public boolean isValidQuantity(Integer articleID, Integer quantity){
    Article article = articleDAO.get(articleID);
    return (article != null && (article.getStockQuantity() - quantity) >= 0);
  }

  @Override
  public boolean isExistInShoppingCart(Integer articleID) {
    return articleDAO.searchInShoppingCarts(articleID).size() > 0;
  }

  @Override
  public boolean isExistInOrder(Integer articleID) {
    return articleDAO.searchInOrders(articleID).size() > 0;
  }

}
