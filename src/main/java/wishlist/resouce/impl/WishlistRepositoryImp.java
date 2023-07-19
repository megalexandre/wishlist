package wishlist.resouce.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import wishlist.domain.entity.Wishlist;
import wishlist.resouce.WishlistRepository;

@Repository
public class WishlistRepositoryImp implements WishlistRepository {

    @Override
    public void save(Wishlist wishlist) {

    }

    @Override
    public Boolean remove(String uuid) {
        return true;
    }

    @Override
    public List<Wishlist> listAll(String clientId) {
        return null;
    }

    @Override
    public Boolean clientHasItem(String client, String Id) {
        return null;
    }
}
