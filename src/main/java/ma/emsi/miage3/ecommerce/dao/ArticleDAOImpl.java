package ma.emsi.miage3.ecommerce.dao;

import ma.emsi.miage3.ecommerce.models.Article;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionManagement
public class ArticleDAOImpl implements ArticleDAO{

  @PersistenceContext(unitName = "unite1")
  private EntityManager entityManager;

  @Override
  public Article add(Article article) {
    try {
      entityManager.merge(article);
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
    Article update = this.get(article.getId());
    update.map(article);
    return update = this.add(update);
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
      return (List<Article>) entityManager.createQuery("select a from Article a").getResultList();
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
  public List<Article> searchInShoppingCarts(Integer articleID) {
    try {
      return (List<Article>) entityManager.createQuery("select a from Article a, ShoppingCartItem s where a.id = " + articleID + " and a.id = s.article.id").getResultList();
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<Article> searchInOrders(Integer articleID) {
    try {
      return (List<Article>) entityManager.createQuery("select a from Article a, OrderItem o where a.id = " + articleID + " and a.id = o.article.id").getResultList();
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<Article> searchByReference(String referencePredicat) {
    try {
      return (List<Article>) entityManager.createQuery("select a from Article a where a.reference LIKE '%" + referencePredicat + "%'").getResultList();
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<Article> searchByName(String namePredicat) {
    try {
      return (List<Article>) entityManager.createQuery("select a from Article a where a.name LIKE '%" + namePredicat + "%'").getResultList();
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<Article> searchByDescription(String descriptionPredicat) {
    try {
      return (List<Article>) entityManager.createQuery("select a from Article a where a.description LIKE '%" + descriptionPredicat + "%'").getResultList();
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<Article> searchByNameAndDescription(String namePredicat, String descriptionPredicat) {
    try {
      return (List<Article>) entityManager.createQuery("select a from Article a where a.name LIKE '%" + namePredicat + "%' and a.description LIKE '%" + descriptionPredicat + "%'").getResultList();
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<Article> searchByNameOrDescription(String namePredicat, String descriptionPredicat) {
    try {
      return (List<Article>) entityManager.createQuery("select a from Article a where a.name LIKE '%" + namePredicat + "%' or a.description LIKE '%" + descriptionPredicat + "%'").getResultList();
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
}
