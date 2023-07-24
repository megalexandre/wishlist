package integration;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import wishlist.domain.entity.WishlistFactory;
import wishlist.resouce.model.WishlistModel;
import wishlist.resouce.repository.WishlistRepositoryData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AddWishlistTest extends BaseIntegration {

    @Autowired
    private WishlistRepositoryData dataRepository;

    @Autowired
    private WishlistFactory factory;

    @Value("${wishlist.product.limit}")
    private int maximumProductLimit;

    @BeforeEach
    public void resetDateRepository() {
       dataRepository.deleteAll();
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
    void whenReceiverWishlistWitNoCustomer_rejectWithBadRequest() throws Exception {
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
    void whenReceiverWishlistWitNoProducts_rejectWithBadRequest() throws Exception {
        var request  = "{\"customer\":\"customerExample\"}";

        mockMvc
            .perform(MockMvcRequestBuilders
                .post("/wishlist")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
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
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
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
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
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
