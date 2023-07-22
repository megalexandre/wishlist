package wishlist.application.endpoint.list_products_by_customer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@NoArgsConstructor
@Getter
public class ListProductsByCustomerRequest {

    @NotEmpty
    @NotNull
    private String customer;

    public ListProductsByCustomerRequest(String customer) {
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }
}
