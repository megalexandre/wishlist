package wishlist.application.presenter.isproductpresent;

import java.util.Optional;

public record IsProductPresentResponse(Boolean isProductPresent) implements IsProductPresent {

    public static IsProductPresentResponse from(Optional isPresent) {
        return new IsProductPresentResponse(isPresent.isPresent());
    }

}
