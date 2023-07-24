package wishlist.domain.usecases;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wishlist.application.endpoint.add_wishlist.AddWishlistEndpoint;
import wishlist.domain.entity.CommonWishlist;
import wishlist.domain.entity.Wishlist;
import static com.mongodb.assertions.Assertions.assertFalse;
import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static java.util.UUID.randomUUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RemoveWishlistUseCaseTest {

    private final RemoveWishlistUseCase useCase;
    private final SaveWishlistUseCase saveWishlistUseCase;
    private final SearchProductsUseCase searchProductsUseCase;

    public RemoveWishlistUseCaseTest() {
        saveWishlistUseCase = mock(SaveWishlistUseCase.class);
        searchProductsUseCase = mock(SearchProductsUseCase.class);
        useCase = new RemoveWishlistUseCase(searchProductsUseCase, saveWishlistUseCase);
    }

    @Test
    void whenTheCustomerHasNoItems_shouldReturnFalse() {
        when(searchProductsUseCase.execute(any())).thenReturn(Optional.empty());

        var wishlist = new CommonWishlist.Builder()
                .setId(randomUUID().toString())
                .setCustomer("customer")
                .setProducts(List.of("products"))
                .build();

        var result = useCase.execute(wishlist);

        assertFalse(result);
        verify(saveWishlistUseCase, times(0)).execute(any());
    }

    @Test
    void whenTheCustomerProductsIsNull_shouldReturnFalse() {
        when(searchProductsUseCase.execute(any())).thenReturn(Optional.empty());

        var wishlist = new CommonWishlist.Builder()
                .setId(randomUUID().toString())
                .setCustomer("customer")
                .build();

        var result = useCase.execute(wishlist);

        assertFalse(result);
        verify(saveWishlistUseCase, times(0)).execute(any());
    }

    @Test
    void whenTheCustomerHasItems_shouldRemoveThen() {
        var id = UUID.randomUUID().toString();

        when(searchProductsUseCase.execute(any())).thenReturn(Optional.of(
                List.of("REMOVE_THIS_PRODUCT","DO_NOT_REMOVE_THIS_PRODUCT")));

        var wishlistSent = new CommonWishlist.Builder()
                .setId(id)
                .setCustomer("customer")
                .setProducts(List.of("REMOVE_THIS_PRODUCT"))
                .build();

        var savedWishlist = new CommonWishlist.Builder()
                .setId(id)
                .setCustomer("customer")
                .setProducts(List.of("DO_NOT_REMOVE_THIS_PRODUCT"))
                .build();

        when(saveWishlistUseCase.execute(any(Wishlist.class))).thenReturn(savedWishlist);

        var result = useCase.execute(wishlistSent);

        assertTrue(result);

        verify(saveWishlistUseCase, times(1))
                .execute(argThat(param ->
                    param.getCustomer().equals("customer") &&
                    param.getProducts().contains("DO_NOT_REMOVE_THIS_PRODUCT")
        ));
    }



}