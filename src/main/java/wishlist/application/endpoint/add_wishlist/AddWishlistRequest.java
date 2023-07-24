package wishlist.application.endpoint.add_wishlist;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import wishlist.domain.entity.CommonWishlist;
import wishlist.domain.entity.Wishlist;

@Validated
@NoArgsConstructor
@Getter
public class AddWishlistRequest {

    @NotEmpty(message = "Product cant be empty")
    @NotNull(message = "Product is not optional")
    private String product;

    @NotEmpty(message = "Customer cant be empty")
    @NotNull(message = "Customer is not optional")
    private String customer;

    public AddWishlistRequest(String product, String customer) {
        this.product = product;
        this.customer = customer;
    }

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
