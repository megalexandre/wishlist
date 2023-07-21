package wishlist.application;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import wishlist.application.endpoints.isProductPresentWishlistCustomer.IsProductPresentWishlistEndpoint;

@ExtendWith(MockitoExtension.class)
class ListWishlistProductsByCustomerTest {

    @InjectMocks
    private IsProductPresentWishlistEndpoint isProductPresentWishlistEndpoint;


    /*
    @Test
    void shouldReturnTrueWhenProductWasAdded(){
        var request = new AddProductToCustomerWishlistRequest("productId","customerId");

        when(wishlistService.addProductToCustomerWishlistRequest(any())).thenReturn(true);
        var response = wishlistController.addProductToCustomerWishlistRequest(request);

        assertEquals(OK, response.getStatusCode());
        assertTrue(response.getBody());
    }

    @Test
    void shouldReturnFalseWhenProductWasNotAdded(){
        var request = new AddProductToCustomerWishlistRequest("productId","customerId");

        when(wishlistService.addProductToCustomerWishlistRequest(any())).thenReturn(false);
        var response = wishlistController.addProductToCustomerWishlistRequest(request);

        assertEquals(OK, response.getStatusCode());
        assertFalse(response.getBody());
    }

    @Test
    void shouldReturnTrueWhenProductWasRemoved(){
        var request = new RemoveProductToCustomerWishlistRequest("productId","customerId");

        when(wishlistService.remove(any())).thenReturn(true);
        var response = wishlistController.removeProductToCustomerWishlist(request);

        assertTrue(response);
    }

    @Test
    void shouldReturnTrueWhenProductWasNotRemoved(){
        var request = new RemoveProductToCustomerWishlistRequest("productId","customerId");

        when(wishlistService.remove(any())).thenReturn(false);
        var response = wishlistController.removeProductToCustomerWishlist(request);

        assertFalse(response);
    }
    */

}