package wishlist.application.endpoint.is_product_present;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Validated
@AllArgsConstructor
@Getter
public class IsProductPresentWishlistRequest {

    @NotEmpty
    @NotNull
    private String product;

    @NotEmpty
    @NotNull
    private String customer;

}
