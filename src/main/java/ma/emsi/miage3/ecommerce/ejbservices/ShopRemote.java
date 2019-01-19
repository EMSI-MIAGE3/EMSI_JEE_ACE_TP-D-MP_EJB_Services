package ma.emsi.miage3.ecommerce.ejbservices;

import ma.emsi.miage3.ecommerce.models.Order;
import ma.emsi.miage3.ecommerce.models.ShoppingCartItem;
import ma.emsi.miage3.ecommerce.models.User;

import javax.ejb.Remote;

@Remote
public interface ShopRemote {
  public boolean addItemToShoppingCart(ShoppingCartItem shoppingCartItem);
  public boolean updateItemInShoppingCart(ShoppingCartItem shoppingCartItem);
  public boolean removeItemFromShoppingCart(Integer shoppingCartItemID);
  public boolean validateOrder(User user);
}
