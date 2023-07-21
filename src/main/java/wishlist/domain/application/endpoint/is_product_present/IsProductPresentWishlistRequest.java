package wishlist.domain.application.endpoint.is_product_present;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@NoArgsConstructor
@Getter
public class IsProductPresentWishlistRequest {

    @NotEmpty
    @NotNull
    @Getter
    private String product;

    @NotEmpty
    @NotNull
    @Getter
    private String customer;

    public IsProductPresentWishlistRequest(String product, String customer) {
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
