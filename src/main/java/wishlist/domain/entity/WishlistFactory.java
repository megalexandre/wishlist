package wishlist.domain.entity;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import wishlist.domain.exception.MaximumProductLimitExceeded;

@Component
public class WishlistFactory {

    private final int maxProductListSize;

    public WishlistFactory(
        @Value("${wishlist.product.limit}")
        int maxProductListSize
    ){
        this.maxProductListSize = maxProductListSize;
    }

    public Builder builder(){
        return new Builder(maxProductListSize);
    }

    public static class Builder {

        private String id;
        private String customer;
        private Collection<String> products;
        private final int maximumProductLimit;

        Builder(int maximumProductLimit){
            this.maximumProductLimit = maximumProductLimit;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setCustomer(String customer) {
            this.customer = customer;
            return this;
        }

        public Builder setProducts(Collection<String> products) {

            if(products.size() > maximumProductLimit){
                throw new MaximumProductLimitExceeded("maximum product limit exceeded");
            }

            this.products = products;
            return this;
        }

        public Builder setProduct(String product) {

            if(products == null){
                products = new ArrayList<>();
            }

            if(this.products.size() + 1 > maximumProductLimit){
                throw new MaximumProductLimitExceeded("maximum product limit exceeded");
            }

            this.products.add(product);
            return this;
        }

        public Wishlist build(){
            return new CommonWishlist(this);
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

}
