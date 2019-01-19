package ma.emsi.miage3.ecommerce.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GenericDAOImpl implements GenericDAO {

  @PersistenceContext(unitName = "unite1")
  private EntityManager entityManager;

  @Override
  public EntityManager getEntityManager() {
    return entityManager;
  }

  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Object add(Object object) {
    try {
      entityManager.merge(object);
      return object;
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public boolean delete(Object object) {
    try {
      entityManager.remove(object);
      return true;
    }
    catch (Exception e){
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public Object update(Object object) {
    return this.add(object);
  }

  @Override
  public Object findByID(Class entity, Integer id) {
    try {
      return entityManager.find(entity, id);
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
}
