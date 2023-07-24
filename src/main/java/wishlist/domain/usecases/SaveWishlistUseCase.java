package wishlist.domain.usecases;

import java.util.Collection;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.entity.WishlistFactory;
import wishlist.domain.repositoy.WishlistRepository;

@Service
public class SaveWishlistUseCase implements UseCase<Wishlist, Wishlist>{

    private final WishlistRepository wishlistRepository;
    private final SearchWishlistUseCase searchWishlistUseCase;
    private final WishlistFactory wishlistFactory;

     public SaveWishlistUseCase(
        SearchWishlistUseCase searchWishlistUseCase,
        WishlistRepository wishlistRepository,
        WishlistFactory wishlistFactory
    ){
        this.searchWishlistUseCase = searchWishlistUseCase;
        this.wishlistRepository = wishlistRepository;
        this.wishlistFactory = wishlistFactory;
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

        return wishlistFactory.builder()
            .setId(previous.get().getId())
            .setCustomer(previous.get().getCustomer())
            .setProducts(
                Stream.of(wishlist.getProducts(), previous.get().getProducts())
                .flatMap(Collection::stream).distinct().toList())
            .build();
    }

}
