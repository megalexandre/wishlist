package wishlist.resouce;

import java.util.List;
import wishlist.domain.entity.Wishlist;

public interface WishlistRepository {

    void save(Wishlist wishlist);
    Boolean remove(String uuid);
    List<Wishlist> listAll(String clientId);
    Boolean clientHasItem(String client, String Id);

}
