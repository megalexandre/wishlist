package wishlist.application.presenter.add_wishlist;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import wishlist.application.presenter.addwishlist.AddWishlistPresenterResponse;
import wishlist.domain.entity.CommonWishlist;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AddWishlistPresenterResponseTest {

    @Test
    void testConstructorAndGetters() {
        String id = UUID.randomUUID().toString();

        var wishlist = new CommonWishlist.Builder()
                .setId(id)
                .setCustomer("customer")
                .setProducts(List.of("product"))
                .build();

        var addWishlistPresenterResponse = AddWishlistPresenterResponse.fromWishList(wishlist);
        assertEquals(addWishlistPresenterResponse.id(), id, "Must record use wishlist id");
    }

}