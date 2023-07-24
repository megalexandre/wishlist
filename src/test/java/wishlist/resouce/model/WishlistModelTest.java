package wishlist.resouce.model;

import java.util.List;
import org.junit.jupiter.api.Test;
import wishlist.domain.entity.CommonWishlist;
import wishlist.domain.entity.Wishlist;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WishlistModelTest {

    @Test
    void shouldConstructorSentParams() {
        Wishlist wishlist = new CommonWishlist.Builder()
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
    void shouldToWishlistCreateWishListWithParams() {
        Wishlist wishlist = new CommonWishlist.Builder()
                .setId("id")
                .setCustomer("customer")
                .setProducts(List.of("Product"))
                .build();

        var testedObject = new WishlistModel(wishlist).toWishlist();

        assertEquals(wishlist.getId(), testedObject.getId());
        assertEquals(wishlist.getCustomer(), testedObject.getCustomer());
        assertEquals(wishlist.getProducts(), testedObject.getProducts());
    }


}