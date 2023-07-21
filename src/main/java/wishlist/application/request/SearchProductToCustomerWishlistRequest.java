package wishlist.application.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import wishlist.domain.entity.CommonWishlist;
import wishlist.domain.entity.Wishlist;

@Validated
public class SearchProductToCustomerWishlistRequest {

    @NotNull
    private String product;

    @NotNull
    private String customer;

    public SearchProductToCustomerWishlistRequest(String product, String customer) {
        this.product = product;
        this.customer = customer;
    }

    public Wishlist toWishlist() {
        return new CommonWishlist.WishlistBuilder()
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
