package wishlist.domain.usecases;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.entity.WishlistFactory;
import wishlist.domain.repositoy.WishlistRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SaveWishlistUseCaseTest {

    private final SaveWishlistUseCase saveWishlistUseCase;
    private final WishlistRepository wishlistRepository;
    private final SearchWishlistUseCase searchWishlistUseCase;
    private final WishlistFactory factory = new WishlistFactory(20);

    public SaveWishlistUseCaseTest() {
        wishlistRepository = mock(WishlistRepository.class);
        searchWishlistUseCase = mock(SearchWishlistUseCase.class);
        saveWishlistUseCase = new SaveWishlistUseCase(searchWishlistUseCase, wishlistRepository, factory);
    }

    @Test
    void whenReceiverNewWishlist_shouldCreateId() {
        var wishlist = factory.builder()
            .setCustomer("customer")
            .setProducts(List.of("product1"))
            .build();

        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(wishlist);
        when(searchWishlistUseCase.execute(any(String.class))).thenReturn(Optional.empty());

        Wishlist result = saveWishlistUseCase.execute(wishlist);

        assertNotNull(result.getId());
        verify(wishlistRepository, times(1)).save(any(Wishlist.class));
        verify(searchWishlistUseCase, times(1)).execute(anyString());
    }

    @Test
    void whenReceiverAExistingWishlist_shouldDoNotCreateId() {
        var oldId = UUID.randomUUID().toString();
        var wishlist = factory.builder()
                .setId(oldId)
                .setCustomer("customer")
                .setProducts(List.of("product1"))
                .build();

        when(searchWishlistUseCase.execute(any(String.class))).thenReturn(Optional.of(wishlist));
        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(wishlist);

        Wishlist result = saveWishlistUseCase.execute(wishlist);

        assertEquals(result.getId(), oldId, "must ID be the same");
        verify(wishlistRepository, times(1)).save(any(Wishlist.class));
        verify(searchWishlistUseCase, times(1)).execute(anyString());
    }

    @Test
    void shouldDoNotDuplicateProduct() {
        var wishlist = factory.builder()
            .setId(UUID.randomUUID().toString())
            .setCustomer("customer")
            .setProducts(List.of("product"))
            .build();

        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(wishlist);
        when(searchWishlistUseCase.execute(any(String.class))).thenReturn(Optional.of(wishlist));

        Wishlist result = saveWishlistUseCase.execute(wishlist);

        ArgumentCaptor<Wishlist> wishlistCaptor = ArgumentCaptor.forClass(Wishlist.class);
        verify(wishlistRepository).save(wishlistCaptor.capture());
        Wishlist capturedWishlist = wishlistCaptor.getValue();

        assertEquals(result.getProducts(), capturedWishlist.getProducts());
    }

}