package wishlist.domain.usecases.entity;

import java.util.Collections;
import org.junit.jupiter.api.Test;
import wishlist.domain.entity.CommonWishlist;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonWishlistTest {

    /*
    @Test
    void shouldAddProduct_whenCollectionsIsEmpty() {
        var wishList = new CommonWishlist("id", "any customer", emptyList());
        assertTrue(wishList.canAddProduct(), "must be possible add a product item on a empty list");
    }

    @Test
    void shouldAddProduct_whenCollectionsIsNull() {
        var wishList = new CommonWishlist("id", "any customer",null);
        assertTrue(wishList.canAddProduct(),
                "must be possible add a product item on a null list"
        );
    }

    @Test
    void shouldDoNotAddProduct_whenCollectionSizeIs20orMore() {
        var maxSizeOfProducts =  Collections.nCopies(20, "foo");

        var wishList = new CommonWishlist("id", "any customer", maxSizeOfProducts);
        assertFalse(wishList.canAddProduct(),"must be product equal or less then 20");
    }
    */

}