package wishlist.domain.usecases;

import java.util.Collection;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import wishlist.domain.entity.CommonWishlist;
import wishlist.domain.entity.Wishlist;
import wishlist.resouce.repository.WishlistRepository;

@Service
public class SaveWishlistUseCase implements UseCase<Wishlist, Wishlist>{

    private final WishlistRepository wishlistRepository;
    private final SearchWishlistUseCase searchWishlistUseCase;

    public SaveWishlistUseCase(
            SearchWishlistUseCase searchWishlistUseCase,
            WishlistRepository wishlistRepository){
        this.searchWishlistUseCase = searchWishlistUseCase;
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Wishlist execute(Wishlist wishList) {

        var newWishList = getPreviousProducts(wishList);

        if(newWishList.getId() == null){
            newWishList.createId();
        }

        return wishlistRepository.save(newWishList);
    }

    private Wishlist getPreviousProducts(Wishlist wishlist){
        var previous = searchWishlistUseCase.execute(wishlist.getCustomer());

        if(previous.isEmpty()){
            return wishlist;
        }

        return new CommonWishlist.WishlistBuilder()
            .setId(wishlist.getId())
            .setCustomer(wishlist.getCustomer())
            .setProducts(Stream.of(wishlist.getProducts(), previous.get().getProducts()).flatMap(Collection::stream).toList())
            .build();
    }




}
