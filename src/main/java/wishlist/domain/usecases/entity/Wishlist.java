package wishlist.domain.usecases.entity;

import java.util.Collection;

public interface Wishlist {

    String getId();
    String getCustomer();
    Collection<String> getProducts();
    Boolean canAddProduct();
}
