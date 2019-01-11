package ma.emsi.miage3.ecommerce.ejbservices;

import ma.emsi.miage3.ecommerce.models.Article;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ArticleRemote {
  public List<Article> getAllArticles();
  public Article getArticle(Integer articleID);
  public Article addArticle(Article article);
  public Article updateArticle(Article article);
  public boolean deleteArticle(Integer articleID);
  public List<Article> searchArticlesByReference(String referencePredicat);
  public List<Article> searchArticlesByName(String namePredicat);
  public List<Article> searchByArticlesDescription(String descriptionPredicat);
}
