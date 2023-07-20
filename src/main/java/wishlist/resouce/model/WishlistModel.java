package wishlist.resouce.model;

import java.util.Collection;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("wishlist")
public class WishlistModel {

    @Id
    private String id;
    private String customer;
    private Collection<String> products;

}
