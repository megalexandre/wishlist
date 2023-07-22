package wishlist.application.presenter.removeproduct;

import org.junit.jupiter.api.Test;
import static com.mongodb.assertions.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RemoveProductPresenterResponseTest {

    @Test
    void whenReceiverFalse_shouldReturnFalse() {
        assertFalse(new RemoveProductPresenterResponse(false).isRemoved());
    }

    @Test
    void whenReceiverTrue_shouldReturnTrue() {
        assertTrue(new RemoveProductPresenterResponse(true).isRemoved());
    }
}