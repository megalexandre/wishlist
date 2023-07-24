package wishlist.domain.usecases;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.repositoy.WishlistRepository;

@Service
public class SearchWishlistUseCase implements UseCase<String, Optional<Wishlist>> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final WishlistRepository wishlistRepository;

    public SearchWishlistUseCase(WishlistRepository wishlistRepository){
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Optional<Wishlist> execute(String customer) {
        logger.info("Searching wishlist to customer: {}", customer);
        return wishlistRepository.findByCustomer(customer);
    }

}
