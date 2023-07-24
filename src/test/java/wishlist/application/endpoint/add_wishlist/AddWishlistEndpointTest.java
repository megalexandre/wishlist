package wishlist.application.endpoint.add_wishlist;

import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;
import wishlist.domain.entity.WishlistFactory;
import wishlist.domain.usecases.SaveWishlistUseCase;
import static java.util.Objects.requireNonNull;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

class AddWishlistEndpointTest {

    @Test
    void shouldReturnTrueWith200_whenProductExists() {
        String customer = "customer";
        String product = "product";
        Collection<String> products = List.of("product");
        var factory = new WishlistFactory(20);

        var expectedResponse = factory.builder()
                .setId(randomUUID().toString())
                .setCustomer(customer)
                .setProducts(products)
                .build();

        var request = new AddWishlistRequest(product,customer);
        var saveWishlistUseCase = mock(SaveWishlistUseCase.class);
        when(saveWishlistUseCase.execute(any())).thenReturn(expectedResponse);

        var endpoint = new AddWishlistEndpoint(saveWishlistUseCase, factory);

        var response = endpoint.execute(request);

        assertNotNull(response);
        assertEquals(OK, response.getStatusCode());
        assertNotNull(requireNonNull(response.getBody()).id());
    }
}