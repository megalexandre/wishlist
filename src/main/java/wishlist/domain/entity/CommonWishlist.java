package wishlist.domain.entity;

import java.util.Collection;
import static java.util.UUID.randomUUID;

public class CommonWishlist implements Wishlist {

    private String id;
    private String customer;
    private Collection<String> products;

    private CommonWishlist() {
    }

    public CommonWishlist(Builder builder) {
        this.id = builder.id;
        this.customer = builder.customer;
        this.products = builder.products;
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

    public static class Builder {

        private String id;
        private String customer;
        private Collection<String> products;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setCustomer(String customer) {
            this.customer = customer;
            return this;
        }

        public Builder setProducts(Collection<String> products) {

            if(products.size() > 20){
                /**@TODO criar um exception especializada*/

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

