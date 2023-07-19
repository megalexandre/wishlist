package wishlist.application.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import wishlist.domain.entity.Customer;
import wishlist.domain.entity.Product;
import wishlist.domain.entity.Wishlist;

@Validated
public class SearchProductToCustomerWishlistRequest {

    @NotNull
    private String productId;

    @NotNull
    private String customerId;

    public SearchProductToCustomerWishlistRequest(String productId, String customerId) {
        this.productId = productId;
        this.customerId = customerId;
    }

    public Wishlist toWishlist() {
        return Wishlist.builder()
            .products(List.of(new Product(productId)))
            .customer(new Customer(customerId))
            .build();
    }

    public String getProductId() {
        return productId;
    }

    public String getCustomerId() {
        return customerId;
    }
}
