package wishlist.resouce.repository.impl;

import java.util.Collection;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.repositoy.WishlistRepository;
import wishlist.resouce.model.WishlistModel;
import wishlist.resouce.repository.WishlistRepositoryData;

@Repository
public class WishlistRepositoryImp implements WishlistRepository {

    private final WishlistRepositoryData wishlistRepositoryData;

    public WishlistRepositoryImp(WishlistRepositoryData wishlistRepositoryData){
        this.wishlistRepositoryData = wishlistRepositoryData;
    }

    @Override
    public Wishlist save(Wishlist wishlist) {
        return wishlistRepositoryData.save(new WishlistModel(wishlist)).toWishlist();
    }

    @Override
    public Optional<Wishlist> findByCustomer(String customer) {
        return wishlistRepositoryData.findByCustomer(customer).map(WishlistModel::toWishlist);
    }

    @Override
    public Optional<Collection<String>> findProductsByCustomer(String customer) {
        return wishlistRepositoryData.findByCustomer(customer).map(WishlistModel::getProducts);
    }

}
