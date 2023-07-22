package wishlist.application.presenter.products;

import java.util.Collection;
import java.util.Optional;
import wishlist.domain.entity.Wishlist;

public record ProductsPresenterResponse(Collection<String> products) implements ProductsPresenter {

    public static ProductsPresenterResponse from(Optional<Wishlist> wishlist) {
        return new ProductsPresenterResponse(wishlist.map(Wishlist::getProducts).orElse(null));
    }

}
