package wishlist.resouce.repository.impl;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import wishlist.domain.entity.CommonWishlist;
import wishlist.resouce.model.WishlistModel;
import wishlist.resouce.repository.WishlistRepositoryData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WishlistRepositoryImpTest {

    private WishlistRepositoryData mockData;
    private WishlistRepositoryImp wishlistRepository;


    public WishlistRepositoryImpTest() {
        mockData = mock(WishlistRepositoryData.class);
        wishlistRepository = new WishlistRepositoryImp(mockData);
    }

    @Test
    void testSave() {
        var wishlist = new CommonWishlist.Builder()
                .setId("123")
                .setCustomer("customer")
                .setProducts(List.of("product"))
                .build();

        when(mockData.save(any())).thenReturn(new WishlistModel(wishlist));
        var savedWishlist = wishlistRepository.save(wishlist);

        assertEquals(wishlist.getId(), savedWishlist.getId());
        assertEquals(wishlist.getCustomer(), savedWishlist.getCustomer());
        assertEquals(wishlist.getProducts(), savedWishlist.getProducts());
    }


    @Test
    void testFindByCustomer_ExistingCustomer() {
        var customer = "customer";
        var wishlistModel = new WishlistModel(
            new CommonWishlist.Builder()
                    .setId("123")
                    .setCustomer("customer")
                    .setProducts(List.of("product"))
                    .build());

        when(mockData.findByCustomer(customer)).thenReturn(Optional.of(wishlistModel));
        var found = wishlistRepository.findByCustomer(customer);

        assertTrue(found.isPresent());
        assertEquals(wishlistModel.getId(), found.get().getId());
        assertEquals(wishlistModel.getCustomer(), found.get().getCustomer());
        assertEquals(wishlistModel.getProducts(), found.get().getProducts());
    }


    @Test
    void testFindByCustomer_NonExistingCustomer() {
        String customer = "NonExistingCustomer";

        when(mockData.findByCustomer(customer)).thenReturn(Optional.empty());
        var foundWishlist = wishlistRepository.findByCustomer(customer);

        assertFalse(foundWishlist.isPresent());
    }

    @Test
    void testFindProductsByCustomer_ExistingCustomer() {
        String customer = "customer";
        WishlistModel wishlistModel = new WishlistModel(
            new CommonWishlist.Builder()
                    .setId("123")
                    .setCustomer("customer")
                    .setProducts(List.of("product"))
                    .build());

        when(mockData.findByCustomer(customer)).thenReturn(Optional.of(wishlistModel));
        var found = wishlistRepository.findProductsByCustomer(customer);
        assertTrue(found.isPresent());
        assertEquals(List.of("product"), found.get());
    }

    @Test
    void testFindProductsByCustomer_NonExistingCustomer() {
        String customer = "NonExistingCustomer";

        when(mockData.findByCustomer(customer)).thenReturn(Optional.empty());
        var foundProducts = wishlistRepository.findProductsByCustomer(customer);

        assertFalse(foundProducts.isPresent());
    }
}