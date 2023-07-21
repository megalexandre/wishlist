package wishlist.domain.entity;

import java.util.Collection;

public interface Wishlist {

    String getId();
    String createId();
    String getCustomer();
    Collection<String> getProducts();

}
