package wishlist.domain.usecases;

public interface UseCase<input, output> {

    output execute(input input);

}
