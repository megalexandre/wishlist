package wishlist.application.endpoint.is_product_present;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import wishlist.domain.usecases.SearchProductsUseCase;
import static com.mongodb.assertions.Assertions.assertFalse;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

class IsProductPresentWishlistEndpointTest {

    @Test
    void shouldReturnTrueWith200_whenProductExists() {
        String customer = "customer";
        Collection<String> products = List.of("product");

        var searchProductsUseCase = mock(SearchProductsUseCase.class);
        when(searchProductsUseCase.execute(customer)).thenReturn(Optional.of(products));

        var request = new IsProductPresentWishlistRequest("product" ,customer);
        var endpoint = new IsProductPresentWishlistEndpoint(searchProductsUseCase);

        var response = endpoint.isProductPresent(request);

        assertNotNull(response);
        assertEquals(OK, response.getStatusCode());
        assertTrue(requireNonNull(response.getBody()).isProductPresent());
    }

    @Test
    void shouldReturnFalseWith404_whenProductDoesNotExists() {
        String customer = "customer";
        var searchProductsUseCase = mock(SearchProductsUseCase.class);
        when(searchProductsUseCase.execute(customer)).thenReturn(Optional.empty());

        var request = new IsProductPresentWishlistRequest("product" , customer);
        var endpoint = new IsProductPresentWishlistEndpoint(searchProductsUseCase);

        var response = endpoint.isProductPresent(request);

        assertNotNull(response);
        assertEquals(NOT_FOUND, response.getStatusCode());
        assertFalse(requireNonNull(response.getBody()).isProductPresent());
    }
}