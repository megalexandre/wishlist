package wishlist.application.endpoint.add_wishlist;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wishlist.application.presenter.addwishlist.AddWishListPresenter;
import wishlist.application.presenter.addwishlist.AddWishlistPresenterResponse;
import wishlist.domain.entity.WishlistFactory;
import wishlist.domain.usecases.SaveWishlistUseCase;

@RestController
@RequestMapping("wishlist")
public class AddWishlistEndpoint {
    Logger logger = LoggerFactory.getLogger(AddWishlistEndpoint.class);

    private final SaveWishlistUseCase useCase;
    private final WishlistFactory wishlistFactory;

    public AddWishlistEndpoint(SaveWishlistUseCase useCase, WishlistFactory wishlistFactory){
        this.useCase = useCase;
        this.wishlistFactory = wishlistFactory;
    }

    @PostMapping
    public ResponseEntity<AddWishListPresenter> execute(@RequestBody @Valid AddWishlistRequest request){
        logger.info("Adding product: {} to customer: {}.", request.getCustomer(), request.getProduct());
        var wishlist = wishlistFactory.builder()
            .setCustomer(request.getCustomer())
            .setProduct(request.getProduct())
        .build();

        return ResponseEntity.ok(AddWishlistPresenterResponse.fromWishList(useCase.execute(wishlist)));
    }

}
