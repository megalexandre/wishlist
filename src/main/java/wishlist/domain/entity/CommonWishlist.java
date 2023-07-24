package wishlist.domain.entity;

import java.util.Collection;
import static java.util.UUID.randomUUID;

public class CommonWishlist implements Wishlist {

    private String id;
    private String customer;
    private Collection<String> products;

    // TO prevent unauthorized creation
    private CommonWishlist() {
    }

    public CommonWishlist(WishlistFactory.Builder builder) {
        this.id = builder.getId();
        this.customer = builder.getCustomer();
        this.products = builder.getProducts();
    }

    @Override
    public String createId() {
        if(id == null){
            this.id = randomUUID().toString();
        }
        return id;
    }

    @Override
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

