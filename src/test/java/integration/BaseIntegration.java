package integration;

import wishlist.infrastucture.CustomExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import wishlist.WishlistApplication;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(classes = {WishlistApplication.class, CustomExceptionHandler.class})
public class BaseIntegration {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Container
    public static MongoDBContainer mongoDBContainer =
        new MongoDBContainer("mongo:latest").withExposedPorts(27017);

    static {
        mongoDBContainer.start();
        var mappedPort= mongoDBContainer.getMappedPort( 27017 );
        System.setProperty( "mongodb.container.port" , String.valueOf(mappedPort));
    }
}
