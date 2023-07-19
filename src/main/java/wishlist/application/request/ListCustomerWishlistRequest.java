package wishlist.application.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import wishlist.domain.entity.Customer;
import wishlist.domain.entity.Wishlist;

@Validated
public class ListCustomerWishlistRequest {

    @NotNull
    private String customerId;

    public ListCustomerWishlistRequest(String customerId) {
        this.customerId = customerId;
    }

    public Wishlist toWishlist() {
        return Wishlist.builder()
            .customer(new Customer(customerId))
            .build();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

}
