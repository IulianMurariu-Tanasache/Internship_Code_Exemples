package Examples;

import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionComposition {

	public static void main(String[] args) {

		Function<Double, Double> log = Math::log;
		Function<Double, Double> sqrt = (value) -> Math.sqrt(value);
		Function<Double, Double> logThenSqrt = sqrt.compose(log); // the argument function is first -> log THEN sqrt
		System.out.println(String.valueOf(logThenSqrt.apply(3.14))); // Output: 1.06
		Function<Double, Double> sqrtThenLog = sqrt.andThen(log);
		System.out.println(String.valueOf(sqrtThenLog.apply(3.14))); // Output: 0.57

		// predicates
		Predicate<Integer> higherThan5= (x) -> x > 5;
		Predicate<Integer> lowerThan10 = (x) -> x < 10;

		Predicate<Integer> inInterval_5_10 = higherThan5.and(lowerThan10);
		System.out.println("Is 1 in (5,10)? " + inInterval_5_10.test(1));
		System.out.println("Is 7 in (5,10)? " + inInterval_5_10.test(7));

	}

}
