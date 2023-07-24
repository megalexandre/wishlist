package wishlist.application.endpoint.remove_wishlist_product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@NoArgsConstructor
@Getter
public class RemoveWishlistRequest {

    @NotEmpty
    @NotNull
    private String product;

    @NotEmpty
    @NotNull
    private String customer;

    public RemoveWishlistRequest(String product, String customer) {
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
