package integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import wishlist.WishlistApplication;
import wishlist.domain.entity.WishlistFactory;
import wishlist.infrastucture.CustomExceptionHandler;
import wishlist.resouce.repository.WishlistRepositoryData;


@AutoConfigureMockMvc
@SpringBootTest(classes = {WishlistApplication.class, CustomExceptionHandler.class})
public class BaseIntegrationTest {

    @Autowired
    public WishlistRepositoryData dataRepository;

    @Autowired
    public WishlistFactory factory;

    @Value("${wishlist.product.limit}")
    public int maximumProductLimit;

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    public void resetDateRepository() {
        dataRepository.deleteAll();
    }

    @Container
    public static MongoDBContainer mongoDBContainer =
            new MongoDBContainer("mongo:latest")
                .withExposedPorts(27017).withReuse(true);

    static {
        mongoDBContainer.start();
        var mappedPort= mongoDBContainer.getMappedPort( 27017 );
        System.setProperty( "mongodb.container.port" , String.valueOf(mappedPort));
    }


}
