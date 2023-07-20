package wishlist.application.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import wishlist.domain.entity.Wishlist;

@Validated
public class ListCustomerWishlistRequest {

    @NotNull
    private String customer;

    public ListCustomerWishlistRequest(String customer) {
        this.customer = customer;
    }

    public Wishlist toWishlist() {
        return Wishlist.builder()
            .customer(customer)
            .build();
    }

    public String customer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

}
