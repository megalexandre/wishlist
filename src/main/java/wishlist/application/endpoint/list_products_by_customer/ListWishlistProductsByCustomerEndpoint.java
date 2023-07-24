package wishlist.application.endpoint.list_products_by_customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/listProductsByCustomer/{customer}")
    public ResponseEntity<ProductsPresenter> list(@PathVariable String customer){
        logger.info("listing all products by customer: {}", customer);
        var products = useCase.execute(new ListProductsByCustomerRequest(customer).getCustomer());
        return new ResponseEntity<>(ProductsPresenterResponse.from(products), products.isPresent() ? OK: NOT_FOUND);
    }

}
