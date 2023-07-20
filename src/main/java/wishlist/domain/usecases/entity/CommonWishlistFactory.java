package wishlist.domain.usecases.entity;

import java.util.Collection;

public class CommonWishlistFactory implements WishlistFactory {

    @Override
    public Wishlist create(String id, String customer, Collection<String> products) {
        return new CommonWishlist(id, customer, products);
    }

}
