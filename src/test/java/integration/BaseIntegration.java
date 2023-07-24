package integration;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import wishlist.WishlistApplication;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(classes = WishlistApplication.class)
public class BaseIntegration {

    @Container
    public static MongoDBContainer mongoDBContainer =
        new MongoDBContainer("mongo:latest").withExposedPorts(27017);

    static {
        mongoDBContainer.start();
        var  mappedPort  = mongoDBContainer.getMappedPort( 27017 );
        System.setProperty( "mongodb.container.port" , String.valueOf(mappedPort));
    }
}
