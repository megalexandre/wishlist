package wishlist.domain.service;

import java.util.Collection;
import wishlist.application.request.ListCustomerWishlistRequest;
import wishlist.domain.entity.Wishlist;

public interface WishlistService {

    Collection<String> list(ListCustomerWishlistRequest listCustomerWishlistRequest);
    Wishlist addProductToCustomerWishlistRequest(Wishlist wishlist);
    Boolean searchProductInCustomerWishlist(Wishlist wishlist);
    Boolean remove(Wishlist wishlist);

}
