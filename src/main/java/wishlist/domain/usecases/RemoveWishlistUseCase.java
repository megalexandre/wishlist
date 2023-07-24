package wishlist.domain.usecases;

import java.util.ArrayList;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.entity.WishlistFactory;

@Service
public class RemoveWishlistUseCase implements UseCase<Wishlist, Boolean> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SearchProductsUseCase searchProductsUseCase;
    private final SaveWishlistUseCase saveWishlistUseCase;
    private final WishlistFactory wishlistFactory;

    public RemoveWishlistUseCase(
        SearchProductsUseCase searchProductsUseCase,
        SaveWishlistUseCase saveWishlistUseCase,
        WishlistFactory wishlistFactory
    ){
        this.searchProductsUseCase = searchProductsUseCase;
        this.saveWishlistUseCase = saveWishlistUseCase;
        this.wishlistFactory = wishlistFactory;
    }

    @Override
    public Boolean execute(Wishlist wishlist) {
        var optionalProducts = searchProductsUseCase.execute(wishlist.getCustomer());

        if(optionalProducts.map(Collection::isEmpty).orElse(true)){
            logger.info("customer: {} has no items", wishlist.getCustomer());
            return false;
        }

        var productToRemove = wishlist.getProducts().iterator().next();

        Collection<String> newProducts = new ArrayList<>(optionalProducts.get());
        newProducts.remove(productToRemove);

        var newWishlist = wishlistFactory.builder()
            .setId(wishlist.getId())
            .setCustomer(wishlist.getCustomer())
            .setProducts(newProducts)
            .build();

        saveWishlistUseCase.execute(newWishlist).getId();
        logger.info(
            "customer: {} has new items list {}",
            newWishlist.getCustomer(),
            newWishlist.getProducts());

        return true;
    }

}
