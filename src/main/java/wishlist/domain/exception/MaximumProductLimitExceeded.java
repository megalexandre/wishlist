package wishlist.domain.exception;

public class MaximumProductLimitExceeded extends RuntimeException{

    public MaximumProductLimitExceeded(String message) {
        super(message);
    }

}
