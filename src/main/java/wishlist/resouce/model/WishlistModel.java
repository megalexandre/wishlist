package wishlist.resouce.model;

import java.util.Collection;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import wishlist.domain.entity.Wishlist;

@Document("wishlist")
@Getter
public class WishlistModel {

    @Id
    private String id;
    private String customer;
    private Collection<String> products;

    public WishlistModel() {
    }

    public WishlistModel(Wishlist wishlist){
        this.id = wishlist.getId();
        this.customer = wishlist.getCustomer();
        this.products = wishlist.getProducts();
    }

}
