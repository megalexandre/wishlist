package wishlist.resouce.model;

import java.util.List;
import org.junit.jupiter.api.Test;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.entity.WishlistFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class WishlistModelTest {
    private final WishlistFactory factory = new WishlistFactory(20);

    @Test
    void shouldConstructorSentParams() {
        Wishlist wishlist = factory.builder()
            .setId("id")
            .setCustomer("customer")
            .setProducts(List.of("Product"))
            .build();

        WishlistModel wishlistModel = new WishlistModel(wishlist);

        assertEquals(wishlist.getId(), wishlistModel.getId());
        assertEquals(wishlist.getCustomer(), wishlistModel.getCustomer());
        assertEquals(wishlist.getProducts(), wishlistModel.getProducts());
    }


    @Test
    void shouldHaveAnEmptyConstructorForSpringData() {
        var emptyWishlistModel = new WishlistModel();
        assertNull(emptyWishlistModel.getId());
    }


}