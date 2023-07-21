package wishlist.domain.application.endpoint.list_products_by_customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wishlist.domain.usecases.SearchWishlistUseCase;
import wishlist.domain.application.presenter.products.ProductsPresenter;
import wishlist.domain.application.presenter.products.ProductsPresenterResponse;

@RestController
@RequestMapping("wishlist")
public class ListWishlistProductsByCustomer {
    Logger logger = LoggerFactory.getLogger(ListWishlistProductsByCustomer.class);

    private final SearchWishlistUseCase useCase;

    public ListWishlistProductsByCustomer(SearchWishlistUseCase searchWishlistUseCase){
        this.useCase = searchWishlistUseCase;
    }

    @GetMapping("/listProductsByCustomer/{customer}")
    public ResponseEntity<ProductsPresenter> list(@PathVariable String customer){
        logger.info("listing all products by customer: {}", customer);
        return ResponseEntity.ok(new ProductsPresenterResponse(
                        useCase.execute(new ListWishlistProductsByCustomerRequest(customer).getCustomer())));
    }

}
