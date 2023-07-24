package integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    void registrationWorksThroughAllLayers() throws Exception {

        mockMvc.perform(
            get("wishlist/listProductsByCustomer/a")
            .contentType("application/json"))
            .andExpect(status().isOk());

    }

}
