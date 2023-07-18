package wishlist.application;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.service.WishlistService;

@RestController
@RequestMapping("wishlist")
public class WishlistController {

    Logger logger = LoggerFactory.getLogger(WishlistController.class);

    private WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService){
        this.wishlistService = wishlistService;
    }

    @Validated
    @GetMapping("/list")
    public List<Wishlist> list(String clientId){
        logger.info("listing wishlist");
        return wishlistService.listAll("a");
    }

}
