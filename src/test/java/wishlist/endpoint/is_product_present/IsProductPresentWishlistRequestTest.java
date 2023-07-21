package wishlist.endpoint.is_product_present;

import org.junit.jupiter.api.Test;
import wishlist.domain.application.endpoint.is_product_present.IsProductPresentWishlistRequest;
import static org.assertj.core.api.Assertions.assertThat;

class IsProductPresentWishlistRequestTest {

    @Test
    void testConstructorAndGetters() {
        String product = "product";
        String customer = "customer";

        IsProductPresentWishlistRequest request = new IsProductPresentWishlistRequest(product, customer);

        assertThat(request.getProduct()).isEqualTo(product);
        assertThat(request.getCustomer()).isEqualTo(customer);
    }

}