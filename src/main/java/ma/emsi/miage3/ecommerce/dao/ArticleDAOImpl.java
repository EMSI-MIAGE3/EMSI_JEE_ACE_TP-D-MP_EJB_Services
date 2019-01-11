package ma.emsi.miage3.ecommerce.dao;

import ma.emsi.miage3.ecommerce.models.Article;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ArticleDAOImpl implements ArticleDAO{

  @PersistenceContext(unitName = "unite1")
  private EntityManager entityManager;

  @Override
  public Article add(Article article) {
    try {
      System.out.println(article);
      entityManager.persist(article);
      return article;
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public boolean delete(Integer articleID) {
    try {
      System.out.println(articleID);
      entityManager.remove(this.get(articleID));
      return true;
    }
    catch (Exception e){
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public Article update(Article article) {
    return this.add(article);
  }

  @Override
  public Article get(Integer articleID) {
    try {
      return entityManager.find(Article.class, articleID);
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<Article> getAll() {
    try {
      return entityManager.createQuery("select a from Article a").getResultList();
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Article findByReference(String reference) {
    try {
      return (Article) entityManager.createQuery("select a from Article a where a.reference = '" + reference + "'").getResultList().stream().findFirst().orElse(null);
    }
    catch(NoResultException e){
      e.printStackTrace();
      return null;
    }
    catch(NonUniqueResultException e){
      e.printStackTrace();
      return null;
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Article findByName(String name) {
    try {
      return (Article) entityManager.createQuery("select a from Article a where a.name = '" + name + "'").getResultList().stream().findFirst().orElse(null);
    }
    catch(NoResultException e){
      e.printStackTrace();
      return null;
    }
    catch(NonUniqueResultException e){
      e.printStackTrace();
      return null;
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<Article> searchByReference(String referencePredicat) {
    try {
      return entityManager.createQuery("select a from Article a where a.reference LIKE '%" + referencePredicat + "%'").getResultList();
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<Article> searchByName(String namePredicat) {
    try {
      return entityManager.createQuery("select a from Article a where a.name LIKE '%" + namePredicat + "%'").getResultList();
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<Article> searchByDescription(String descriptionPredicat) {
    try {
      return entityManager.createQuery("select a from Article a where a.description LIKE '%" + descriptionPredicat + "%'").getResultList();
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
}
