package wishlist.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wishlist.application.request.AddProductToCustomerWishlistRequest;
import wishlist.application.request.RemoveProductToCustomerWishlistRequest;
import wishlist.domain.service.WishlistService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class WishlistControllerTest {

    @InjectMocks
    private WishlistController wishlistController;

    @Mock
    private WishlistService wishlistService;

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