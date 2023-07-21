package wishlist.application.endpoints.isProductPresentWishlistCustomer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import wishlist.domain.entity.CommonWishlist;
import wishlist.domain.entity.Wishlist;

@Validated
@NoArgsConstructor
@AllArgsConstructor
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

    public Wishlist toWishlist() {
        return new CommonWishlist.Builder()
                .setCustomer(customer)
                .setProducts(List.of(product))
                .build();
    }

    public String getProduct() {
        return product;
    }

    public String getCustomer() {
        return customer;
    }
}
