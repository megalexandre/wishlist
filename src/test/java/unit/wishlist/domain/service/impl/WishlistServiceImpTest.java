package unit.wishlist.domain.service.impl;

import java.util.UUID;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wishlist.domain.service.WishlistService;
import wishlist.domain.service.impl.WishlistServiceImp;
import wishlist.resouce.WishlistRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WishlistServiceImpTest {

    @InjectMocks
    private WishlistServiceImp wishlistService;

    @Mock
    private WishlistRepository wishlistRepository;

    /*
    @Test
    void save() {
    }

    @Test
    void remove() {
    }

    @Test
    void listAll() {
    }
     */

    @Test
    void should_return_true_when_product_exists_in_wishlist() {
        var clientId = UUID.randomUUID().toString();
        var productId = UUID.randomUUID().toString();

        when(wishlistRepository.clientHasItem(clientId, productId )).thenReturn(true);

        wishlistService.clientHasItem(clientId, productId);
    }

    @Test
    void should_return_false_when_product_does_not_exists_in_wishlist() {
        var clientId = UUID.randomUUID().toString();
        var productId = UUID.randomUUID().toString();

        when(wishlistRepository.clientHasItem(clientId, productId )).thenReturn(false);
        wishlistService.clientHasItem(clientId, productId);
    }

}