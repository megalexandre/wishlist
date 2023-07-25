package integration.find;

import integration.BaseIntegrationTest;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import wishlist.domain.entity.WishlistFactory;
import wishlist.resouce.model.WishlistModel;
import wishlist.resouce.repository.WishlistRepositoryData;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class IsProductPresentTest extends BaseIntegrationTest {

    @Autowired
    private WishlistRepositoryData dataRepository;

    @Autowired
    private WishlistFactory factory;

    @BeforeEach
    public void resetDateRepository() {
        dataRepository.deleteAll();
    }

    @Test
    void whenProductExist_returnSuccess() throws Exception {
        var validProduct = "this_product_exists";
        var customer = "customer";
        var wishlistModel = new WishlistModel(factory.builder()
                .setId(UUID.randomUUID().toString())
                .setCustomer(customer)
                .setProduct(validProduct)
                .build()
        );
        dataRepository.save(wishlistModel);

        mockMvc.perform(MockMvcRequestBuilders
            .get("/wishlist/isProductInCustomerWishlist")
                .param("customer", customer)
                .param("product",validProduct)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful());
    }

    @Test
    void whenProductDoesNotExist_returnNotFound() throws Exception {
        var validProduct = "this_product_exists";
        var customer = "customer";

        mockMvc.perform(MockMvcRequestBuilders
            .get("/wishlist/isProductInCustomerWishlist")
                    .param("customer", customer)
                    .param("product",validProduct)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }
}
