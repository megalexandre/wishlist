package wishlist.resouce.repository.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import wishlist.domain.entity.Wishlist;
import wishlist.resouce.repository.WishlistRepository;
import wishlist.resouce.repository.WishlistRepositoryJpa;

@Repository
public class WishlistRepositoryImp implements WishlistRepository {

    private WishlistRepositoryJpa wishlistRepositoryJpa;

    public WishlistRepositoryImp(WishlistRepositoryJpa wishlistRepositoryJpa){
        this.wishlistRepositoryJpa = wishlistRepositoryJpa;
    }

    @Override
    public Wishlist save(Wishlist wishlist) {
        return null;
    }

    @Override
    public void remove(String id) {
        wishlistRepositoryJpa.deleteById(id);
    }

    @Override
    public List<Wishlist> listAll(String clientId) {
        return null;
    }

    @Override
    public Boolean clientHasItem(String client, String id) {
        return true;
    }
}
