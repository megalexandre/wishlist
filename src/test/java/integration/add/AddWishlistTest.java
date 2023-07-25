package integration.add;

import integration.BaseIntegrationTest;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import wishlist.resouce.model.WishlistModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AddWishlistTest extends BaseIntegrationTest {

    @Test
    void whenReceiverAValidWishlist_saveThenSuccess() throws Exception {
        var request  = "{\"customer\":\"customerExample\",\"product\":\"my amazing product\"}";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/wishlist")
                .content(request)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    void whenReceiverWishlistWitNoCustomer_rejectWithBadRequest() throws Exception {
        var request  = "{\"product\":\"my amazing product\"}";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/wishlist")
                .content(request)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void whenReceiverWishlistWitNoProducts_rejectWithBadRequest() throws Exception {
        var request  = "{\"customer\":\"customerExample\"}";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/wishlist")
                .content(request)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void whenExceedMaxSizeProduct_rejectWithBadRequest() throws Exception {
        var products = createStringCollection(maximumProductLimit);

        var wishlistModel = new WishlistModel(factory.builder()
            .setId(UUID.randomUUID().toString())
            .setCustomer("customer")
            .setProducts(products)
            .build()
        );

        dataRepository.save(wishlistModel);

        var request  = "{\"customer\":\"customer\",\"product\":\"new Product\"}";
        mockMvc.perform(MockMvcRequestBuilders
            .post("/wishlist")
            .content(request)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    }

    @Test
    void whenReceiverDuplicatedProduct_doNotDuplicate_and_returnSuccess() throws Exception {
        var products = List.of("do not duplicate-me");
        var id = UUID.randomUUID().toString();

        var wishlistModel = new WishlistModel(factory.builder()
            .setId(id)
            .setCustomer("customer")
            .setProducts(products)
            .build()
        );

        dataRepository.save(wishlistModel);

        var request  = "{\"customer\":\"customer\",\"product\":\"do not duplicate-me\"}";
        mockMvc.perform(MockMvcRequestBuilders
                    .post("/wishlist")
                    .content(request)
                    .contentType(APPLICATION_JSON)
                    .accept(APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful());

        var savedWishList = dataRepository.findById(id);

       assertEquals(savedWishList.get().getProducts(), products);
    }

    public List<String> createStringCollection(int n) {
        return IntStream.range(0, n)
            .mapToObj(i -> "Product " + i)
            .collect(Collectors.toList());
    }

}
