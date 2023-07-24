package wishlist.domain.usecases;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.entity.WishlistFactory;
import wishlist.domain.repositoy.WishlistRepository;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SearchWishlistUseCaseTest {

    private final SearchWishlistUseCase searchWishlistUseCase;
    private final WishlistRepository wishlistRepository;
    private final WishlistFactory factory = new WishlistFactory(20);

    public SearchWishlistUseCaseTest() {
        wishlistRepository = mock(WishlistRepository.class);
        searchWishlistUseCase = new SearchWishlistUseCase(wishlistRepository);
    }

    @Test
    void shouldReturnCustomer_WishlistFound() {
        String customer = "customer";
        Wishlist expectedWishlist = factory.builder()
                .setId(randomUUID().toString())
                .setCustomer(customer)
                .setProducts(List.of("products"))
                .build();

        when(wishlistRepository.findByCustomer(customer)).thenReturn(Optional.of(expectedWishlist));
        Optional<Wishlist> result = searchWishlistUseCase.execute(customer);

        assertEquals(expectedWishlist, result.get());
        verify(wishlistRepository).findByCustomer(customer);
    }

    @Test
    void shouldReturnOptionalEmpty_whenWishlistNotFound() {
        String customer = "NonExistingCustomer";
        when(wishlistRepository.findByCustomer(customer)).thenReturn(Optional.empty());
        Optional<Wishlist> result = searchWishlistUseCase.execute(customer);

        assertFalse(result.isPresent());
        verify(wishlistRepository).findByCustomer(customer);
    }

}