package wishlist.application.endpoint.remove_wishlist_product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wishlist.application.presenter.removeproduct.RemoveProductPresenterResponse;
import wishlist.domain.entity.WishlistFactory;
import wishlist.domain.usecases.RemoveWishlistUseCase;

@RestController
@RequestMapping("wishlist")
public class RemoveWishlistEndpoint {
    Logger logger = LoggerFactory.getLogger(RemoveWishlistEndpoint.class);

    private final RemoveWishlistUseCase useCase;
    private final WishlistFactory wishlistFactory;

    public RemoveWishlistEndpoint(
        RemoveWishlistUseCase useCase,
        WishlistFactory wishlistFactory
        ){
        this.useCase = useCase;
        this.wishlistFactory = wishlistFactory;
    }

    @DeleteMapping
    public ResponseEntity<RemoveProductPresenterResponse> removeProductToCustomerWishlist(@RequestBody RemoveWishlistRequest request){
        logger.info("removing product: {}.to customer: {}.", request.getCustomer(), request.getProduct());

        var wishlist = wishlistFactory.builder()
            .setCustomer(request.getCustomer())
            .setProduct(request.getProduct())
            .build();

        return ResponseEntity.ok(new RemoveProductPresenterResponse(useCase.execute(wishlist)));
    }

}
