package wishlist.application.endpoints.removeWishlistProduct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wishlist.domain.usecases.RemoveWishlistUseCase;

@RestController
@RequestMapping("wishlist")
public class RemoveWishlistEndpoint {
    Logger logger = LoggerFactory.getLogger(RemoveWishlistEndpoint.class);

    private final RemoveWishlistUseCase useCase;

    public RemoveWishlistEndpoint(RemoveWishlistUseCase useCase){
        this.useCase = useCase;
    }

    @DeleteMapping
    public ResponseEntity<Boolean> removeProductToCustomerWishlist(@RequestBody RemoveWishlistRequest request){
        logger.info("removing product: {}.to customer: {}.", request.getCustomer(), request.getProduct());
        return ResponseEntity.ok(useCase.execute(request.toWishlist()));
    }

}
