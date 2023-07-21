package wishlist.resouce.repository.impl;

import java.util.Collection;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import wishlist.domain.entity.Wishlist;
import wishlist.resouce.model.WishlistModel;
import wishlist.resouce.repository.WishlistRepository;
import wishlist.resouce.repository.WishlistRepositoryJpa;

@Repository
public class WishlistRepositoryImp implements WishlistRepository {

    private final WishlistRepositoryJpa wishlistRepositoryJpa;

    public WishlistRepositoryImp(WishlistRepositoryJpa wishlistRepositoryJpa){
        this.wishlistRepositoryJpa = wishlistRepositoryJpa;
    }

    @Override
    public Wishlist save(Wishlist wishlist) {
        return wishlistRepositoryJpa.save(new WishlistModel(wishlist)).toWishlist();
    }

    @Override
    public Optional<Wishlist> findByCustomer(String customer) {
        return wishlistRepositoryJpa.findByCustomer(customer)
                .map(WishlistModel::toWishlist);
    }

    @Override
    public Optional<Collection<String>> findProductsByCustomer(String customer) {
        return wishlistRepositoryJpa.findByCustomer(customer).map(it -> it.getProducts());
    }

}
