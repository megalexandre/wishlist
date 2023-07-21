package wishlist.application.response;

import java.util.Collection;
import java.util.Optional;
import wishlist.domain.entity.Wishlist;

public class WishlistResponse {

    private String id;
    private String customer;
    private Collection<String> products;

    public WishlistResponse(Wishlist wishlist) {
        this.id = wishlist.getId();
        this.customer = wishlist.getCustomer();
        this.products = wishlist.getProducts();
    }

    public WishlistResponse(Optional<Wishlist> wishlist) {
        if(wishlist.isPresent()){
            this.id = wishlist.get().getId();
            this.customer = wishlist.get().getCustomer();
            this.products = wishlist.get().getProducts();
        }
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
