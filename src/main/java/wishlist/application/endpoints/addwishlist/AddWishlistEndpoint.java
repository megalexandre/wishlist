package wishlist.application.endpoints.addwishlist;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wishlist.application.response.WishlistResponse;
import wishlist.domain.usecases.SaveWishlistUseCase;

@RestController
@RequestMapping("wishlist")
public class AddWishlistEndpoint {
    Logger logger = LoggerFactory.getLogger(AddWishlistEndpoint.class);

    private final SaveWishlistUseCase useCase;

    public AddWishlistEndpoint(SaveWishlistUseCase useCase){
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<WishlistResponse> execute(@RequestBody @Valid AddWishlistRequest request){
        logger.info("Adding product: {} to customer: {}.", request.getCustomer(), request.getProduct());
        return ResponseEntity.ok().body(new WishlistResponse(useCase.execute(request.toWishlist())));
    }

}
