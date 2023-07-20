package wishlist.resouce.repository;

import java.util.List;
import wishlist.domain.entity.Wishlist;

public interface WishlistRepository {

    Wishlist save(Wishlist wishlist);
    void remove(String productId);
    List<Wishlist> listAll(String clientId);
    Boolean clientHasItem(String client, String Id);

}

