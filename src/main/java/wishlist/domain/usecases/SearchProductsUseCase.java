package wishlist.domain.usecases;

import java.util.Collection;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import wishlist.domain.repositoy.WishlistRepository;

@Service
public class SearchProductsUseCase implements UseCase<String, Optional<Collection<String>>> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final WishlistRepository wishlistRepository;

    public SearchProductsUseCase(WishlistRepository wishlistRepository){
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Optional<Collection<String>> execute(String customer) {
        logger.info("Searching products to customer: {}", customer);
        return wishlistRepository.findProductsByCustomer(customer);
    }

}
