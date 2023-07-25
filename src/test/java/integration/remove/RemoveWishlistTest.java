package integration.remove;

import integration.BaseIntegrationTest;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import wishlist.resouce.model.WishlistModel;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RemoveWishlistTest extends BaseIntegrationTest {

    @Test
    void whenCustomerDoNotExists_returnSuccess() throws Exception {
        var request  = "{\"customer\":\"customer\",\"product\":\"product\"}";

        mockMvc.perform(MockMvcRequestBuilders
            .delete("/wishlist")
            .content(request)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful());
    }

    @Test
    void whenCustomer_hasItems_deleteThen_returnSuccess() throws Exception {
        var request  = "{\"customer\":\"customer\",\"product\":\"product\"}";

        var model = new WishlistModel(factory.builder()
            .setId(UUID.randomUUID().toString())
            .setCustomer("customer")
            .setProducts(List.of("product", "other product"))
            .build()
        );
        dataRepository.save(model);


        mockMvc.perform(MockMvcRequestBuilders
            .delete("/wishlist")
            .content(request)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful());
    }

}
