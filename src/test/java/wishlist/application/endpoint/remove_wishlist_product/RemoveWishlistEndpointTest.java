package wishlist.application.endpoint.remove_wishlist_product;

import org.junit.jupiter.api.Test;
import wishlist.domain.usecases.RemoveWishlistUseCase;
import static com.mongodb.assertions.Assertions.assertFalse;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

class RemoveWishlistEndpointTest {

    @Test
    void shouldReturnTrueWith200_whenProductWasRemoved() {
        var customer = "customer";
        var product = "product";

        var useCase = mock(RemoveWishlistUseCase.class);

        when(useCase.execute(any())).thenReturn(true);

        var request = new RemoveWishlistRequest(customer, product);
        var endpoint = new RemoveWishlistEndpoint(useCase);

        var response = endpoint.removeProductToCustomerWishlist(request);

        assertNotNull(response);
        assertEquals(OK, response.getStatusCode());
        assertTrue(requireNonNull(response.getBody().isRemoved()));
    }

    @Test
    void shouldReturnFalseWith200_whenProductDidNotExists() {
        var customer = "customer";
        var product = "product";

        var useCase = mock(RemoveWishlistUseCase.class);
        when(useCase.execute(any())).thenReturn(false);

        var request = new RemoveWishlistRequest(customer, product);
        var endpoint = new RemoveWishlistEndpoint(useCase);

        var response = endpoint.removeProductToCustomerWishlist(request);

        assertNotNull(response);
        assertEquals(OK, response.getStatusCode());
        assertFalse(requireNonNull(response.getBody()).isRemoved());
    }

}