package wishlist.domain.application.presenter.addwishlist;

import wishlist.domain.entity.Wishlist;

public record AddWishlistPresenterResponse(String id) implements AddWishListPresenter {

    public static AddWishlistPresenterResponse fromWishList(Wishlist wishlist) {
        return new AddWishlistPresenterResponse(wishlist.getId());
    }
}
