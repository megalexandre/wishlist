package wishlist.application.endpoints.listProductsByCustomer;

import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wishlist.application.response.WishlistResponse;
import wishlist.domain.usecases.SearchWishlistUseCase;

@RestController
@RequestMapping("wishlist")
public class ListWishlistProductsByCustomer {
    Logger logger = LoggerFactory.getLogger(ListWishlistProductsByCustomer.class);

    private final SearchWishlistUseCase useCase;

    public ListWishlistProductsByCustomer(SearchWishlistUseCase searchWishlistUseCase){
        this.useCase = searchWishlistUseCase;
    }

    @GetMapping("/listProductsByCustomer/{customerId}")
    public Collection<String> list(@PathVariable String customer){
        logger.info("listing all products wishlist by customer: {}", customer);
        return new WishlistResponse(useCase.execute(customer)).getProducts();
    }

}
