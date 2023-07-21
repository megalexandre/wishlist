package wishlist.domain.application.presenter.products;

import java.util.Collection;
import java.util.Optional;
import wishlist.domain.entity.Wishlist;

public class ProductsPresenterResponse implements ProductsPresenter{

    private final Collection<String> products;

    public ProductsPresenterResponse(Optional<Wishlist> wishlist) {
        this.products = wishlist.map(Wishlist::getProducts).orElse(null);
    }

    @Override
    public Collection<String> getProducts() {
        return products;
    }
}
