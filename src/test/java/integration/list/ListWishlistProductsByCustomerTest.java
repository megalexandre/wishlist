package integration.list;

import integration.BaseIntegrationTest;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import wishlist.resouce.model.WishlistModel;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ListWishlistProductsByCustomerTest extends BaseIntegrationTest {

    @Test
    void whenCustomerHasItems_returnThenSuccess() throws Exception {

        var model = new WishlistModel(factory.builder()
            .setId(UUID.randomUUID().toString())
            .setCustomer("customer")
            .setProducts(List.of("products", "other product"))
            .build()
        );
        dataRepository.save(model);


        mockMvc.perform(MockMvcRequestBuilders
            .get("/wishlist/listProductsByCustomer")
            .param("customer", "customer")
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful());
    }

    @Test
    void whenCustomerDoNotExists_returnNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get("/wishlist/listProductsByCustomer")
            .param("customer", "customer")
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }
}
