package wishlist.resouce.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import wishlist.resouce.model.WishlistModel;

public interface WishlistRepositoryData extends MongoRepository<WishlistModel, String> {
    Optional<WishlistModel> findByCustomer(String customer);
}

