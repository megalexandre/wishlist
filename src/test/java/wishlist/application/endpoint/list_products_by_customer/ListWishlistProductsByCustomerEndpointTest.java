package wishlist.application.endpoint.list_products_by_customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.entity.WishlistFactory;
import wishlist.domain.usecases.SearchWishlistUseCase;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

class ListWishlistProductsByCustomerEndpointTest {

    @Test
    void shouldReturnProductsWith200_whenProductExists() {
        var id = UUID.randomUUID().toString();
        var customer = "customer";
        var products = List.of("product one", "product two");
        var factory = new WishlistFactory(20);

        var wishlist = Optional.of(factory.builder()
                .setId(id)
                .setCustomer(customer)
                .setProducts(products)
                .build());

        var searchWishlistUseCase = mock(SearchWishlistUseCase.class);
        when(searchWishlistUseCase.execute(customer)).thenReturn(wishlist);

        var endpoint = new ListWishlistProductsByCustomerEndpoint(searchWishlistUseCase);

        var response = endpoint.list(customer);

        assertNotNull(response);
        assertEquals(OK, response.getStatusCode());
        assertEquals(requireNonNull(response.getBody()).products(), products);
    }

    @Test
    void shouldReturnNullWith404_whenProductDoesNotExists() {
        var customer = "customer";

        Optional<Wishlist> wishlist = Optional.empty();

        var searchWishlistUseCase = mock(SearchWishlistUseCase.class);
        when(searchWishlistUseCase.execute(customer)).thenReturn(wishlist);

        var endpoint = new ListWishlistProductsByCustomerEndpoint(searchWishlistUseCase);

        var response = endpoint.list(customer);

        assertNotNull(response);
        assertEquals(NOT_FOUND, response.getStatusCode());
        Assertions.assertThat(requireNonNull(response.getBody()).products()).isEmpty();
    }

}