package wishlist.domain.usecases;

import java.util.Collection;
import java.util.Optional;
import org.springframework.stereotype.Service;
import wishlist.domain.repositoy.WishlistRepository;

@Service
public class SearchProductsUseCase implements UseCase<String, Optional<Collection<String>>> {

    private final WishlistRepository wishlistRepository;

    public SearchProductsUseCase(WishlistRepository wishlistRepository){
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Optional<Collection<String>> execute(String customer) {
        return wishlistRepository.findProductsByCustomer(customer);
    }

}
