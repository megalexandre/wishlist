package wishlist.application.endpoint.is_product_present;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import wishlist.domain.entity.WishlistFactory;
import wishlist.domain.usecases.SearchWishlistUseCase;
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
        var factory = new WishlistFactory(20);
        var wishlist = factory.builder()
            .setId(UUID.randomUUID().toString())
            .setCustomer(customer)
            .setProducts(products)
            .build();

        var useCase = mock(SearchWishlistUseCase.class);
        when(useCase.execute(customer)).thenReturn(Optional.of(wishlist));

        var endpoint = new IsProductPresentWishlistEndpoint(useCase);
        var response = endpoint.isProductPresent("customer", "product");

        assertNotNull(response);
        assertEquals(OK, response.getStatusCode());
        assertTrue(requireNonNull(response.getBody()).isProductPresent());
    }

    @Test
    void shouldReturnFalseWith404_whenProductDoesNotExists() {
        String customer = "customer";
        var useCase = mock(SearchWishlistUseCase.class);
        when(useCase.execute(customer)).thenReturn(Optional.empty());

        var endpoint = new IsProductPresentWishlistEndpoint(useCase);
        var response = endpoint.isProductPresent("product" , customer);

        assertNotNull(response);
        assertEquals(NOT_FOUND, response.getStatusCode());
        assertFalse(requireNonNull(response.getBody()).isProductPresent());
    }
}