package wishlist.domain.entity;

import java.util.Collection;

public class Wishlist {

    private String id;
    private Customer customer;
    private Collection<Product> products;

    public Wishlist(String id, Customer customer, Collection<Product> products) {
        this.id = id;
        this.customer = customer;
        this.products = products;
    }

    public static WishlistBuilder builder() {
        return new WishlistBuilder();
    }

    public static class WishlistBuilder {
        private String id;
        private Customer customer;
        private Collection<Product> products;

        WishlistBuilder() {
        }

        public WishlistBuilder id(final String id) {
            this.id = id;
            return this;
        }

        public WishlistBuilder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public WishlistBuilder products ( Collection<Product> products) {
            this.products = products;
            return this;
        }

        public Wishlist build() {
            return new Wishlist(id, customer, products);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }
}
