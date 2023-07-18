package wishlist.domain.service;

import java.util.List;
import wishlist.domain.entity.Wishlist;

public interface WishlistService{

    void save(Wishlist wishlist);
    void remove(String uuid);
    List<Wishlist> listAll(String clientId);
    Boolean clientHasItem(String client, String Id);

}
