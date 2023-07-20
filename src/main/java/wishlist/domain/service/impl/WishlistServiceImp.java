package wishlist.domain.service.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;
import wishlist.application.request.ListCustomerWishlistRequest;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.service.WishlistService;
import wishlist.resouce.repository.WishlistRepository;

@Service
public class WishlistServiceImp implements WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistServiceImp(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Collection<String> list(ListCustomerWishlistRequest listCustomerWishlistRequest) {
        return null;
    }

    @Override
    public Wishlist addProductToCustomerWishlistRequest(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Boolean remove(Wishlist wishlist) {
        return null;
    }

    @Override
    public Boolean searchProductInCustomerWishlist(Wishlist wishlist) {
        return null;
    }

}
