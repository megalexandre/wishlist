package wishlist.application.presenter.isproductpresent;

import java.util.List;
import java.util.Optional;
import wishlist.domain.entity.Wishlist;

public record IsProductPresentResponse(boolean isProductPresent) implements IsProductPresent {

    public static IsProductPresentResponse from(Optional<Wishlist> wishlist, String product) {
        return new IsProductPresentResponse(wishlist.map(Wishlist::getProducts)
                .orElse(List.of())
                .contains(product));
    }

}
