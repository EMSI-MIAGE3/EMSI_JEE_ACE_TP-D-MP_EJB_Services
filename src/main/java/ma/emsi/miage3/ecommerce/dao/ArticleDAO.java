package ma.emsi.miage3.ecommerce.dao;


import ma.emsi.miage3.ecommerce.models.Article;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ArticleDAO {
  public Article add(Article article);
  public boolean delete(Integer articleID);
  public Article update(Article article);
  public Article get(Integer articleID);
  public List<Article> getAll();
  public Article findByReference(String reference);
  public Article findByName(String name);
  public List<Article> searchByReference(String referencePredicat);
  public List<Article> searchByName(String namePredicat);
  public List<Article> searchByDescription(String descriptionPredicat);
}
