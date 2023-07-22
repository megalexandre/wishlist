package wishlist.application.endpoint.add_wishlist;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AddWishlistRequestTest {

    @Test
    void testConstructorAndGetters() {
        String product = "product";
        String customer = "customer";

        AddWishlistRequest request = new AddWishlistRequest(product, customer);

        assertThat(request.getProduct()).isEqualTo(product);
        assertThat(request.getCustomer()).isEqualTo(customer);
    }

}