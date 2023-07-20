package wishlist.domain.usecases.entity;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonWishlist implements Wishlist{

    private String id;
    private String customer;
    private Collection<String> products;

    public CommonWishlist(String id, String customer, Collection<String> products) {
        this.id = id;
        this.customer = customer;
        this.products = products;
    }

    @Override
    public Boolean canAddProduct() {
        return products == null || products.size() < 20 ;
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
