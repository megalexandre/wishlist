package wishlist.application;

import jakarta.validation.Valid;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wishlist.application.request.AddProductToCustomerWishlistRequest;
import wishlist.application.request.RemoveProductToCustomerWishlistRequest;
import wishlist.application.request.SearchProductToCustomerWishlistRequest;
import wishlist.application.response.WishlistResponse;
import wishlist.domain.usecases.RemoveWishlistUseCase;
import wishlist.domain.usecases.SaveWishlistUseCase;
import wishlist.domain.usecases.SearchProductsUseCase;
import wishlist.domain.usecases.SearchWishlistUseCase;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("wishlist")
public class WishlistController {
    Logger logger = LoggerFactory.getLogger(WishlistController.class);

    private final SaveWishlistUseCase addProductUseCase;
    private final RemoveWishlistUseCase removeWishlistUseCase;
    private final SearchWishlistUseCase searchWishlistUseCase;
    private final SearchProductsUseCase searchProductsUseCase;

    public WishlistController(
            SearchWishlistUseCase searchWishlistUseCase,
            RemoveWishlistUseCase removeWishlistUseCase,
            SaveWishlistUseCase saveWishlistUseCase,
            SearchProductsUseCase searchProductsUseCase
    ){
        this.searchWishlistUseCase = searchWishlistUseCase;
        this.addProductUseCase = saveWishlistUseCase;
        this.removeWishlistUseCase = removeWishlistUseCase;
        this.searchProductsUseCase = searchProductsUseCase;
    }

    @PostMapping
    public ResponseEntity<WishlistResponse> addProductWishlist(@RequestBody AddProductToCustomerWishlistRequest request){
        logger.info("Adding product: {} to customer: {}.", request.getCustomer(), request.getProduct());
        return new ResponseEntity<>(new WishlistResponse(addProductUseCase.execute(request.toWishlist())), OK);
    }

    @DeleteMapping
    public Boolean removeProductToCustomerWishlist(@RequestBody RemoveProductToCustomerWishlistRequest request){
        logger.info("removing product: {}.to customer: {}.", request.getCustomer(), request.getProduct());
        return removeWishlistUseCase.execute(request.toWishlist());
    }

    @GetMapping("/listProductsByCustomer/{customerId}")
    public Collection<String> list(@PathVariable String customerId){
        logger.info("listing all products wishlist by customer: {}", customerId);
        return new WishlistResponse(searchWishlistUseCase.execute(customerId)).getProducts();
    }

    @GetMapping("/searchProductInCustomerWishlist")
    public Boolean searchProductInCustomerWishlist(@Valid SearchProductToCustomerWishlistRequest request){
        logger.info("searching product {}. in wishlist customer {}", request.getProduct(), request.getCustomer());
        return searchProductsUseCase.execute(request.getCustomer()).isPresent();
    }

}
