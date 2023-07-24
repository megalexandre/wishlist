package wishlist.resouce.repository.impl;

import java.util.Collection;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.entity.WishlistFactory;
import wishlist.domain.repositoy.WishlistRepository;
import wishlist.resouce.model.WishlistModel;
import wishlist.resouce.repository.WishlistRepositoryData;

@Repository
public class WishlistRepositoryImp implements WishlistRepository {

    private final WishlistRepositoryData wishlistRepositoryData;
    private final WishlistFactory wishlistFactory;

    public WishlistRepositoryImp(
        WishlistRepositoryData wishlistRepositoryData,
        WishlistFactory wishlistFactory
    ){
        this.wishlistRepositoryData = wishlistRepositoryData;
        this.wishlistFactory = wishlistFactory;
    }

    @Override
    public Wishlist save(Wishlist wishlist) {
        var wishlistModel =  wishlistRepositoryData.save(new WishlistModel(wishlist));

        return wishlistFactory.builder()
            .setId(wishlistModel.getId())
            .setCustomer(wishlist.getCustomer())
            .setProducts(wishlist.getProducts())
            .build();
    }

    @Override
    public Optional<Wishlist> findByCustomer(String customer) {
        var optional = wishlistRepositoryData.findByCustomer(customer);

        return optional.map(model -> wishlistFactory.builder()
            .setId(model.getId())
            .setCustomer(model.getCustomer())
            .setProducts(model.getProducts())
            .build());
    }

    @Override
    public Optional<Collection<String>> findProductsByCustomer(String customer) {
        return wishlistRepositoryData.findByCustomer(customer).map(WishlistModel::getProducts);
    }

}
