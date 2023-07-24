package wishlist.application.endpoint.add_wishlist;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public class AddWishlistRequest {

    @NotEmpty(message = "Product cant be empty")
    @NotNull(message = "Product is not optional")
    private final String product;

    @NotEmpty(message = "Customer cant be empty")
    @NotNull(message = "Customer is not optional")
    private final String customer;

    public AddWishlistRequest(String product, String customer) {
        this.product = product;
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public String getCustomer() {
        return customer;
    }
}
