package ma.emsi.miage3.ecommerce.ejbservices;

import ma.emsi.miage3.ecommerce.dao.GenericDAO;
import ma.emsi.miage3.ecommerce.dao.GenericDAOImpl;
import ma.emsi.miage3.ecommerce.dao.UserDAO;
import ma.emsi.miage3.ecommerce.models.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
public class ShopBean implements ShopRemote {
  @EJB
  GenericDAO genericDAO;
  @EJB
  UserDAO userDAO;

  @Override
  public boolean addItemToShoppingCart(ShoppingCartItem shoppingCartItem) {
    try {
      ShoppingCartItem shoppingCartItemX = null;
      try {
        User user = userDAO.get(shoppingCartItem.getShoppingCart().getClientOwner().getId());
        ShoppingCartItem finalShoppingCartItem = shoppingCartItem;
        shoppingCartItemX = user.getShoppingCart().getShoppingCartItems()
                .stream().filter(sci -> sci.getArticle().getId().equals(finalShoppingCartItem.getArticle().getId())).findFirst().orElse(null);
      }catch (Exception e){ }
      if (shoppingCartItemX != null){
        shoppingCartItemX.setQuantity(shoppingCartItemX.getQuantity() + shoppingCartItem.getQuantity());
        shoppingCartItem = shoppingCartItemX;
      }
      genericDAO.add(shoppingCartItem);
      return true;
    }
    catch (Exception e){
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean updateItemInShoppingCart(ShoppingCartItem shoppingCartItem) {
    return this.addItemToShoppingCart(shoppingCartItem);
  }

  @Override
  public boolean removeItemFromShoppingCart(Integer shoppingCartItemID) {
    try {
      ShoppingCartItem shoppingCartItem = (ShoppingCartItem)genericDAO.findByID(ShoppingCartItem.class, shoppingCartItemID);
      genericDAO.delete(shoppingCartItem);
      return true;
    }
    catch (Exception e){
      e.printStackTrace();
      return false;
    }
  }

  @Override
  @Transactional
  public boolean validateOrder(User user) {

    Order order = (Order) genericDAO.add(new Order(user));
    user.getShoppingCart().getShoppingCartItems()
                          .forEach(
                                    sci -> {
                                      OrderItem orderItem = new OrderItem(order, sci.getArticle(), sci.getQuantity(), sci.getArticle().getPrice());
                                      genericDAO.add(orderItem);
                                      ShoppingCartItem shoppingCartItem = (ShoppingCartItem) genericDAO.findByID(ShoppingCartItem.class, sci.getId());
                                      genericDAO.getEntityManager().remove(shoppingCartItem);
                                    });
    return false;
  }
}
