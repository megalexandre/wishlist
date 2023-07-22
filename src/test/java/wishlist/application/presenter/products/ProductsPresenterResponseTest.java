package wishlist.application.presenter.products;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import wishlist.domain.entity.CommonWishlist;
import wishlist.domain.entity.Wishlist;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductsPresenterResponseTest {

    @Test
    void whenReceiverProducts_shouldReturnProducts() {
        var optional = Optional.of(new CommonWishlist.Builder()
            .setId(randomUUID().toString())
            .setCustomer("customer")
            .setProducts(List.of("products"))
            .build());

        var response = ProductsPresenterResponse.from(optional);

        assertEquals(response.products(), List.of("products"));
    }

    @Test
    void whenReceiverOptionalEmpty_shouldReturnNull() {
        Optional<Wishlist> emptyWishlist = Optional.empty();
        var response = ProductsPresenterResponse.from(emptyWishlist);
        Assertions.assertNull(response.products(), "Response products should be null");
    }

}