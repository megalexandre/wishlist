package wishlist.application.endpoint.is_product_present;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wishlist.application.presenter.isproductpresent.IsProductPresentResponse;
import wishlist.domain.usecases.SearchWishlistUseCase;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("wishlist")
public class IsProductPresentWishlistEndpoint {
    Logger logger = LoggerFactory.getLogger(IsProductPresentWishlistEndpoint.class);

    private final SearchWishlistUseCase useCase;

    public IsProductPresentWishlistEndpoint(SearchWishlistUseCase useCase){
        this.useCase = useCase;
    }

    @GetMapping("/isProductInCustomerWishlist")
    public ResponseEntity<IsProductPresentResponse> isProductPresent(
        @Valid
        @NotEmpty @RequestParam String customer,
        @NotEmpty @RequestParam String product){
        logger.info("searching product {}. in wishlist customer {}", customer, product);

        var wishlist = useCase.execute(customer);
        var response = IsProductPresentResponse.from(wishlist, product);

        return new ResponseEntity<>(response, response.isProductPresent() ? OK : NOT_FOUND);
    }

}
