package wishlist.application;

import jakarta.validation.Valid;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wishlist.application.request.AddProductToCustomerWishlistRequest;
import wishlist.application.request.ListCustomerWishlistRequest;
import wishlist.application.request.RemoveProductToCustomerWishlistRequest;
import wishlist.application.request.SearchProductToCustomerWishlistRequest;
import wishlist.domain.entity.Product;
import wishlist.domain.service.WishlistService;

@RestController
@RequestMapping("wishlist")
public class WishlistController {
    Logger logger = LoggerFactory.getLogger(WishlistController.class);

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService){
        this.wishlistService = wishlistService;
    }

    /*Deveriamos previnir duplicatas?*/
    @PostMapping("/add")
    public void add(@Valid AddProductToCustomerWishlistRequest request){
        logger.info("Adding product: {}.to customer: {}.", request.getCustomerId(), request.getProductId());
        wishlistService.save(request.toWishlist());
    }

    @DeleteMapping("/remove")
    public void remove(@Valid RemoveProductToCustomerWishlistRequest request){
        logger.info("removing product: {}.to customer: {}.", request.getCustomerId(), request.getProductId());
        wishlistService.remove(request.toWishlist());
    }

    @GetMapping("/list")
    public Collection<Product> list(@Valid ListCustomerWishlistRequest request){
        logger.info("listing all products wishlist by customer: {}", request.getCustomerId());
        return wishlistService.list(request);
    }

    @GetMapping("/searchProductInCustomerWishlist}")
    public boolean searchProductInCustomerWishlist(@Valid SearchProductToCustomerWishlistRequest request){
        logger.info("searching product {}. in wishlist customer {}", request.getProductId(), request.getCustomerId());
        return wishlistService.searchProductInCustomerWishlist(request.toWishlist());
    }

}
