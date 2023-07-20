package wishlist.domain.entity;

import java.util.Collection;

public class Wishlist {

    private String id;
    private String customer;
    private Collection<String> products;

    public Wishlist(String id, String customer, Collection<String> products) {
        this.id = id;
        this.customer = customer;
        this.products = products;
    }

    public static WishlistBuilder builder() {
        return new WishlistBuilder();
    }

    public static class WishlistBuilder {
        private String id;
        private String customer;
        private Collection<String> products;

        WishlistBuilder() {
        }

        public WishlistBuilder id(final String id) {
            this.id = id;
            return this;
        }

        public WishlistBuilder customer(String customer) {
            this.customer = customer;
            return this;
        }

        public WishlistBuilder products (Collection<String> products) {
            this.products = products;
            return this;
        }

        public Wishlist build() {
            return new Wishlist(id, customer, products);
        }
    }

}
