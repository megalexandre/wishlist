package wishlist.domain.repositoy;

import java.util.Collection;
import java.util.Optional;
import wishlist.domain.entity.Wishlist;

public interface WishlistRepository  {

    Wishlist save(Wishlist wishlist);
    Optional<Wishlist> findByCustomer(String customer);
    Optional<Collection<String>> findProductsByCustomer(String customer);

}

