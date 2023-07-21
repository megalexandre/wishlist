package wishlist.domain.application.endpoint.add_wishlist;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wishlist.domain.usecases.SaveWishlistUseCase;
import wishlist.domain.application.presenter.addwishlist.AddWishListPresenter;
import wishlist.domain.application.presenter.addwishlist.AddWishlistPresenterResponse;

@RestController
@RequestMapping("wishlist")
public class AddWishlistEndpoint {
    Logger logger = LoggerFactory.getLogger(AddWishlistEndpoint.class);

    private final SaveWishlistUseCase useCase;

    public AddWishlistEndpoint(SaveWishlistUseCase useCase){
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<AddWishListPresenter> execute(@RequestBody @Valid AddWishlistRequest request){
        logger.info("Adding product: {} to customer: {}.", request.getCustomer(), request.getProduct());
        return ResponseEntity.ok(AddWishlistPresenterResponse.fromWishList(useCase.execute(request.toWishlist())));
    }

}
