package Examples;

import java.util.Optional;

public class FunctorsAndMonads {

	public static void main(String[] args) {
		// functor
		Optional wrapperForNumber = Optional.of(2).map(x -> x + 2);
		System.out.println("Functor result: " + wrapperForNumber.get());

		wrapperForNumber = Optional.of(2).map(f -> Optional.of(3).map(g -> f + g));
		System.out.println("Functor doesn't work here, we need monad: " + wrapperForNumber.get());

		// monad -> like functor but with flatmap
		wrapperForNumber = Optional.of(2).flatMap(f -> Optional.of(3).flatMap(g -> Optional.of(f + g)));
		System.out.println("Monad: " + wrapperForNumber.get());
	}
}
