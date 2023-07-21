package wishlist.domain.application.endpoint.is_product_present;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wishlist.domain.usecases.SearchProductsUseCase;
import wishlist.domain.application.presenter.isproductpresent.IsProductPresentResponse;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("wishlist")
public class IsProductPresentWishlistEndpoint {
    Logger logger = LoggerFactory.getLogger(IsProductPresentWishlistEndpoint.class);

    private final SearchProductsUseCase searchProductsUseCase;

    public IsProductPresentWishlistEndpoint(SearchProductsUseCase searchProductsUseCase){
        this.searchProductsUseCase = searchProductsUseCase;
    }

    @GetMapping("/searchProductInCustomerWishlist")
    public ResponseEntity<IsProductPresentResponse> isProductPresent(@Valid @RequestBody IsProductPresentWishlistRequest request){
        logger.info("searching product {}. in wishlist customer {}", request.getProduct(), request.getCustomer());
        var products = searchProductsUseCase.execute(request.getCustomer());
        return new ResponseEntity<>(new IsProductPresentResponse(products.isPresent()), products.isPresent() ? OK: NOT_FOUND);
    }

}
