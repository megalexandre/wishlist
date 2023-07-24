package wishlist.domain.usecases;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import wishlist.domain.repositoy.WishlistRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SearchProductsUseCaseTest {

    private final SearchProductsUseCase useCase;
    private final WishlistRepository repository;

    public SearchProductsUseCaseTest() {
        repository = mock(WishlistRepository.class);
        useCase = new SearchProductsUseCase(repository);
    }

    @Test
    void shouldReturnProducts_whenWishlistFound() {
        var customer = "customer";
        Optional<Collection<String>> products = Optional.of(List.of("product", "other product"));
        when(repository.findProductsByCustomer(any())).thenReturn(products);
        var result = useCase.execute(customer);

        assertEquals(result, products);
        verify(repository).findProductsByCustomer(customer);
    }

    @Test
    void shouldReturnOptionalEmpty_whenWishlistNotFound() {
        String customer = "NonExistingCustomer";
        when(repository.findProductsByCustomer(any())).thenReturn(Optional.empty());
        var result = useCase.execute(customer);

        assertFalse(result.isPresent());
        verify(repository).findProductsByCustomer(customer);
    }
}