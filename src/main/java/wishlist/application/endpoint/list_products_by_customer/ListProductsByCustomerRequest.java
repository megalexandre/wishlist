package wishlist.application.endpoint.list_products_by_customer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Validated
@AllArgsConstructor
@Getter
public class ListProductsByCustomerRequest {

    @NotEmpty
    @NotNull
    private String customer;

}
