package wishlist.application.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import wishlist.domain.entity.Wishlist;

@Validated
public class RemoveProductToCustomerWishlistRequest {

    @NotNull
    private String product;

    @NotNull
    private String customer;

    public RemoveProductToCustomerWishlistRequest(String product, String customer) {
        this.product = product;
        this.customer = customer;
    }

    public Wishlist toWishlist() {
        return Wishlist.builder()
            .products(List.of(product))
            .customer(customer)
            .build();
    }

    public String getProduct() {
        return product;
    }

    public String getCustomer() {
        return customer;
    }
}
