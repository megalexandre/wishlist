package wishlist.application.endpoints.isProductPresentWishlistCustomer;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wishlist.domain.usecases.SearchProductsUseCase;

@RestController
@RequestMapping("wishlist")
public class IsProductPresentWishlistEndpoint {
    Logger logger = LoggerFactory.getLogger(IsProductPresentWishlistEndpoint.class);

    private final SearchProductsUseCase searchProductsUseCase;

    public IsProductPresentWishlistEndpoint(SearchProductsUseCase searchProductsUseCase){
        this.searchProductsUseCase = searchProductsUseCase;
    }

    @GetMapping("/searchProductInCustomerWishlist")
    public ResponseEntity<Boolean> searchProductInCustomerWishlist(@Valid IsProductPresentWishlistRequest request){
        logger.info("searching product {}. in wishlist customer {}", request.getProduct(), request.getCustomer());
        return ResponseEntity.ok(searchProductsUseCase.execute(request.getCustomer()).isPresent());
    }

}
