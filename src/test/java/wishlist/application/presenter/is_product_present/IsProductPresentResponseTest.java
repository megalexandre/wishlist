package wishlist.application.presenter.is_product_present;


import java.util.Optional;
import org.junit.jupiter.api.Test;
import wishlist.application.presenter.isproductpresent.IsProductPresentResponse;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsProductPresentResponseTest {

    @Test
    void shouldReturnTrue_whenProductExists() {
        var response = IsProductPresentResponse.from(Optional.of(true));
        assertTrue(response.isProductPresent());
    }

    @Test
    void shouldReturnFalse_whenProductDoesExists() {
        var response = IsProductPresentResponse.from(Optional.empty());
        assertFalse(response.isProductPresent());
    }
}