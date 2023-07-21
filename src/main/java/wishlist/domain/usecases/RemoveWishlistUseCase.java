package wishlist.domain.usecases;

import org.springframework.stereotype.Service;
import wishlist.domain.entity.CommonWishlist;
import wishlist.domain.entity.Wishlist;

@Service
public class RemoveWishlistUseCase implements UseCase<Wishlist, Boolean> {

    private final SaveWishlistUseCase saveWishlistUseCase;
    private final SearchProductsUseCase searchProductsUseCase;

    public RemoveWishlistUseCase(
        SearchProductsUseCase searchProductsUseCase,
        SaveWishlistUseCase saveWishlistUseCase
    ){
        this.searchProductsUseCase = searchProductsUseCase;
        this.saveWishlistUseCase = saveWishlistUseCase;
    }

    @Override
    public Boolean execute(Wishlist wishlist) {
        var optionalProducts = searchProductsUseCase.execute(wishlist.getCustomer());

        if(optionalProducts.isEmpty()){
            return false;
        }

        var productToRemove = wishlist.getProducts().stream().findFirst().orElseThrow();

        var newWishlist = new CommonWishlist.WishlistBuilder()
            .setId(wishlist.getId())
            .setCustomer(wishlist.getCustomer())
            .setProducts(optionalProducts.get().stream().filter(p -> p.equals(productToRemove)).toList())
        .build();

        saveWishlistUseCase.execute(newWishlist);

        return true;
    }

}
