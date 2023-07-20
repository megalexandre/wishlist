package wishlist.resouce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import wishlist.domain.entity.Wishlist;
import wishlist.resouce.model.WishlistModel;

public interface WishlistRepositoryJpa extends MongoRepository<WishlistModel, String> {}

