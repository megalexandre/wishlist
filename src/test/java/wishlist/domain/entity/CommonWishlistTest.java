package wishlist.domain.entity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import wishlist.domain.exception.MaximumProductLimitExceeded;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonWishlistTest {

    @Test
    void whenHasNoId_shouldCreate() {
        var wishlist = new CommonWishlist.Builder()
            .build();

        wishlist.createId();
        assertNotNull(wishlist.getId());
    }

    @Test
    void whenHasId_shouldDoNoCreate() {
        var originalId = "DO_NOT_OVERRIDE_ME";
        var wishlist = new CommonWishlist.Builder()
            .setId("DO_NOT_OVERRIDE_ME")
            .build();

        wishlist.createId();
        assertThat(wishlist.getId()).isEqualTo(originalId);
    }

    @Test
    void whenProductsSizeOverSize_shouldThrowMaximumProductLimitExceeded() {
        var products = Collections.nCopies(21, "product");

        Exception exception = assertThrows(MaximumProductLimitExceeded.class, () -> {
             new CommonWishlist.Builder()
                    .setId(randomUUID().toString())
                    .setCustomer("customer")
                    .setProducts(products)
                    .build();
        });

        assertEquals("maximum product limit exceeded", exception.getMessage() );
    }

    @Test
    void whenProductsSizeItIsOk_shouldDoNotThrow() {
        var products = Collections.nCopies(20, "product");

        assertDoesNotThrow(() ->
            new CommonWishlist.Builder()
                .setId(randomUUID().toString())
                .setCustomer("customer")
                .setProducts(products)
                .build()
        );

    }

    @Test
    void testConstructorIsPrivate() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        var constructor =
                Arrays.stream(CommonWishlist.class.getDeclaredConstructors())
                        .filter(t -> t.getParameterCount() == 0 ).findFirst().get();

        constructor.setAccessible(true);
        constructor.newInstance();

        assertTrue(Modifier.isPrivate(constructor.getModifiers()),"should have a private constructor to prevent");
    }

}