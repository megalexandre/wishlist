package wishlist.domain.service.impl;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import wishlist.domain.*;
import wishlist.domain.service.*;
import wishlist.resouce.*;

public class WishlistServiceImp implements WishlistService{

    WishlistRepository wishlistRepository;

    public WishlistServiceImp(@Autowired WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public void save(Wishlist wishlist) {
        wishlistRepository.save(wishlist);
    }

    @Override
    public void remove(String uuid) {
        wishlistRepository.remove(uuid);
    }

    @Override
    public List<Wishlist> listAll(String uuid) {
        return wishlistRepository.listAll(uuid);
    }

    @Override
    public Boolean clientHasItem(String client, String uuid) {
        return wishlistRepository.clientHasItem(client, uuid);
    }

}
