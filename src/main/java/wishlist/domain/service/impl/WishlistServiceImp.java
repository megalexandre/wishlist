package wishlist.domain.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.service.WishlistService;
import wishlist.resouce.WishlistRepository;

@Service
public class WishlistServiceImp implements WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistServiceImp(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public void save(Wishlist wishlist) {
        wishlistRepository.save(wishlist);
    }

    @Override
    public void remove(String uuid) {
        wishlistRepository.remove(uuid);
    }

    @Override
    public List<Wishlist> listAll(String uuid) {
        return wishlistRepository.listAll(uuid);
    }

    @Override
    public Boolean clientHasItem(String clientId, String productId) {
        return wishlistRepository.clientHasItem(clientId, productId);
    }

}
