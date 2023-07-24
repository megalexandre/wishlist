package integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AddWishlistTest extends BaseIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void saveNewWishlist() throws Exception {
        var request  = "{\"customer\":\"customerExample\",\"product\":\"my amazing product\"}";

        mockMvc
            .perform(MockMvcRequestBuilders
                .post("/wishlist")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    void rejectWishlistWitNoCustomer() throws Exception {
        var request  = "{\"product\":\"my amazing product\"}";

        mockMvc
            .perform(MockMvcRequestBuilders
                .post("/wishlist")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void rejectWishlistWitNoProducts() throws Exception {
        var request  = "{\"customer\":\"customerExample\"}";

        mockMvc
            .perform(MockMvcRequestBuilders
                .post("/wishlist")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }




}
