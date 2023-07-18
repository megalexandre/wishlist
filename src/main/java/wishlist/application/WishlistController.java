package wishlist.application;

import wishlist.application.request.*;
import wishlist.application.response.*;

public class WishlistController {

    public WishlistResponse save(WishlistRequest wishlistRequest){
        return new WishlistResponse(wishlistRequest.getName());
    }

}
