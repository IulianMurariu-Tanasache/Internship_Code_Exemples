package Examples;

import java.util.function.Function;

public class FunctionComposition {

	public static void main(String[] args) {

		Function<Double, Double> log = Math::log;
		Function<Double, Double> sqrt = (value) -> Math.sqrt(value);
		Function<Double, Double> logThenSqrt = sqrt.compose(log); // the argument function is first -> log THEN sqrt
		System.out.println(String.valueOf(logThenSqrt.apply(3.14))); // Output: 1.06
		Function<Double, Double> sqrtThenLog = sqrt.andThen(log);
		System.out.println(String.valueOf(sqrtThenLog.apply(3.14))); // Output: 0.57

	}

}
