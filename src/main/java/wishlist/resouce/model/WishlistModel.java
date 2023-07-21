package wishlist.resouce.model;

import java.util.Collection;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import wishlist.domain.entity.CommonWishlist;
import wishlist.domain.entity.Wishlist;

@Document("wishlist")
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

    public Wishlist toWishlist() {
        return new CommonWishlist.Builder()
            .setId(id)
            .setCustomer(customer)
            .setProducts(products)
            .build();
    }

    public String getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public Collection<String> getProducts() {
        return products;
    }
}
