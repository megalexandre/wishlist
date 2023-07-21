package wishlist.domain.usecases;

import java.util.Optional;
import org.springframework.stereotype.Service;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.repositoy.WishlistRepository;

@Service
public class SearchWishlistUseCase implements UseCase<String, Optional<Wishlist>> {

    private final WishlistRepository wishlistRepository;

    public SearchWishlistUseCase(WishlistRepository wishlistRepository){
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Optional<Wishlist> execute(String customer) {
        return wishlistRepository.findByCustomer(customer);
    }

}
