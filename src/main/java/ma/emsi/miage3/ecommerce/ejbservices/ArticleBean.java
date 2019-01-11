package ma.emsi.miage3.ecommerce.ejbservices;

import ma.emsi.miage3.ecommerce.dao.ArticleDAO;
import ma.emsi.miage3.ecommerce.models.Article;
import ma.emsi.miage3.ecommerce.rulesServices.ArticleRule;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ArticleBean implements ArticleRemote {

  @EJB
  ArticleDAO articleDAO;

  @EJB
  ArticleRule rule;

  @Override
  public List<Article> getAllArticles() {
    return articleDAO.getAll();
  }

  @Override
  public Article getArticle(Integer articleID) {
    return articleDAO.get(articleID);
  }

  private Article mergeArticle(Article article){
    if(
            rule.isUniqueName(article.getName(), article.getId()) &&
                    rule.isUniqueReference(article.getReference(), article.getId()) &&
                    rule.isValidQuantity(article)
    ){
      System.out.println(article);
      return articleDAO.add(article);
    }
    else {
      return null;
    }
  }

  @Override
  public Article addArticle(Article article) {
    return this.mergeArticle(article);
  }

  @Override
  public Article updateArticle(Article article) {
    return this.mergeArticle(article);
  }

  @Override
  public boolean deleteArticle(Integer articleID) {
    return articleDAO.delete(articleID);
  }

  @Override
  public List<Article> searchArticlesByReference(String referencePredicat) {
    return articleDAO.searchByReference(referencePredicat);
  }

  @Override
  public List<Article> searchArticlesByName(String namePredicat) {
    return articleDAO.searchByName(namePredicat);
  }

  @Override
  public List<Article> searchByArticlesDescription(String descriptionPredicat) {
    return articleDAO.searchByDescription(descriptionPredicat);
  }
}
