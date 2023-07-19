package wishlist.domain.service;

import java.util.Collection;
import wishlist.application.request.ListCustomerWishlistRequest;
import wishlist.domain.entity.Product;
import wishlist.domain.entity.Wishlist;

public interface WishlistService {

    Collection<Product> list(ListCustomerWishlistRequest listCustomerWishlistRequest);
    void save(Wishlist wishlist);
    Boolean remove(Wishlist wishlist);
    Boolean searchProductInCustomerWishlist(Wishlist wishlist);

}
