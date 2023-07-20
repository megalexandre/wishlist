package wishlist.domain.usecases.entity;

import java.util.Collection;

public interface WishlistFactory {

    Wishlist create(String id, String customer, Collection<String> products);

}
