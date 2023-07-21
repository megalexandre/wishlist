package wishlist.domain.entity;

import java.util.Collection;
import java.util.UUID;

public class CommonWishlist implements Wishlist{

    private String id;
    private String customer;
    private Collection<String> products;

    private CommonWishlist() {
    }

    public CommonWishlist(WishlistBuilder builder) {
        this.id = builder.id;
        this.customer = builder.customer;
        this.products = builder.products;
    }

    @Override
    public String createId() {
        if(id == null){
            this.id = UUID.randomUUID().toString();
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

    public static class WishlistBuilder{

        private String id;
        private String customer;
        private Collection<String> products;

        public WishlistBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public WishlistBuilder setCustomer(String customer) {
            this.customer = customer;
            return this;
        }

        public WishlistBuilder setProducts(Collection<String> products) {

            if(products.size() > 20){
                throw new RuntimeException("");
            }

            this.products = products;
            return this;
        }

        public Wishlist build(){
            return new CommonWishlist(this);
        }
    }

}

