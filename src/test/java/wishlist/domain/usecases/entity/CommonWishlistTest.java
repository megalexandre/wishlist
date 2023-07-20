package wishlist.domain.usecases.entity;

import java.util.Collections;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonWishlistTest {

    @Test
    void shouldAddProductWhenCollectionsIsEmpty() {
        var wishList = new CommonWishlist("id", "any customer", emptyList());
        assertTrue(wishList.canAddProduct(), "must be possible add a product item on a empty list");
    }

    @Test
    void shouldAddProductWhenCollectionsIsNull() {
        var wishList = new CommonWishlist("id", "any customer",null);
        assertTrue(wishList.canAddProduct(),
                "must be possible add a product item on a null list"
        );
    }

    @Test
    void shouldDoNotAddProductWhenCollectionsSizeIs20orMore() {
        var maxSizeOfProducts =  Collections.nCopies(20, "foo");

        var wishList = new CommonWishlist("id", "any customer", maxSizeOfProducts);
        assertFalse(wishList.canAddProduct(),"must be product equal or less then 20");
    }




}