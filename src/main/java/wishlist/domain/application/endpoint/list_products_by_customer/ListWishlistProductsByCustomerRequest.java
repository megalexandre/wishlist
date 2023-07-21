package wishlist.domain.application.endpoint.list_products_by_customer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@NoArgsConstructor
@Getter
public class ListWishlistProductsByCustomerRequest {

    @NotEmpty
    @NotNull
    private String customer;

    public ListWishlistProductsByCustomerRequest(String customer) {
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }
}
