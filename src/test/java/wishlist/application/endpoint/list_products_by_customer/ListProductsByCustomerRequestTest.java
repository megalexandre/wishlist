package wishlist.application.endpoint.list_products_by_customer;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ListProductsByCustomerRequestTest {

    @Test
    void testConstructorAndGetters() {
        String customer = "customer";

        ListProductsByCustomerRequest request = new ListProductsByCustomerRequest(customer);

        assertThat(request.getCustomer()).isEqualTo(customer);
    }
}