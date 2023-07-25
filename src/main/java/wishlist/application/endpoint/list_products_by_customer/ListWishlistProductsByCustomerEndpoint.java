package wishlist.application.endpoint.list_products_by_customer;

import jakarta.validation.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wishlist.application.presenter.products.ProductsPresenter;
import wishlist.application.presenter.products.ProductsPresenterResponse;
import wishlist.domain.usecases.SearchWishlistUseCase;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("wishlist")
public class ListWishlistProductsByCustomerEndpoint {
    Logger logger = LoggerFactory.getLogger(ListWishlistProductsByCustomerEndpoint.class);

    private final SearchWishlistUseCase useCase;

    public ListWishlistProductsByCustomerEndpoint(SearchWishlistUseCase searchWishlistUseCase){
        this.useCase = searchWishlistUseCase;
    }

    @GetMapping("/listProductsByCustomer")
    public ResponseEntity<ProductsPresenter> list(@NotEmpty @RequestParam String customer){
        logger.info("listing all products by customer: {}", customer);
        var products = useCase.execute(customer);

        return new ResponseEntity<>(ProductsPresenterResponse.from(products), products.isPresent() ? OK: NOT_FOUND);
    }

}
