package ma.emsi.miage3.ecommerce.ejbservices;

import ma.emsi.miage3.ecommerce.dao.GenericDAO;
import ma.emsi.miage3.ecommerce.models.Order;
import ma.emsi.miage3.ecommerce.models.ShoppingCart;
import ma.emsi.miage3.ecommerce.models.ShoppingCartItem;
import ma.emsi.miage3.ecommerce.models.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ShopBean implements ShopRemote {
  @EJB
  GenericDAO genericDAO;
  @Override
  public boolean addItemToShoppingCart(ShoppingCartItem shoppingCartItem) {
    try {
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
  public boolean validateOrder(Order order) {
    return false;
  }
}
