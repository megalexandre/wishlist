package wishlist.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wishlist.domain.service.WishlistService;

@RestController
@RequestMapping("wishlist")
public class WishlistController {
    Logger logger = LoggerFactory.getLogger(WishlistController.class);

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService){
        this.wishlistService = wishlistService;
    }

    /*
    @PostMapping("/add")
    public ResponseEntity<Boolean> addProductToCustomerWishlistRequest(@Valid AddProductToCustomerWishlistRequest request){
        logger.info("Adding product: {}.to customer: {}.", request.getCustomerId(), request.getProductId());
        return new ResponseEntity<>(wishlistService.addProductToCustomerWishlistRequest(request.toWishlist()), OK);
    }

    @DeleteMapping("/remove")
    public Boolean removeProductToCustomerWishlist(@Valid RemoveProductToCustomerWishlistRequest request){
        logger.info("removing product: {}.to customer: {}.", request.getCustomerId(), request.getProductId());
        return wishlistService.remove(request.toWishlist());
    }

    @GetMapping("/list/{customerId}")
    public ProductResponse list(@Valid @PathVariable String customerId){
        logger.info("listing all products wishlist by customer: {}", customerId);
        return new ProductResponse(wishlistService.list(new ListCustomerWishlistRequest(customerId)));
    }

    @GetMapping("/searchProductInCustomerWishlist")
    public boolean searchProductInCustomerWishlist(@Valid SearchProductToCustomerWishlistRequest request){
        logger.info("searching product {}. in wishlist customer {}", request.getProductId(), request.getCustomerId());
        return wishlistService.searchProductInCustomerWishlist(request.toWishlist());
    }
    */

}
