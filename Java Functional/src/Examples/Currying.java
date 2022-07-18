package Examples;

import java.util.function.Function;

public class Currying {

	private static Function<Double, Double> weightOnEarthClosure() {
		double gravity = 9.81;
		// gravity = 10; -> enclosed variables have to be final / effectively final
		return mass -> mass * gravity;
	}

	public static void main(String[] args) {

		// function that uses the result of another function -> chaining functions instead of using multiple arguments
		Function<Double, Function<Double, Double>> weight = mass -> gravity -> mass * gravity;

		Function<Double, Double> weightOnEarth = weight.apply(9.81);
		System.out.println("My weight on Earth: " + weightOnEarth.apply(60.0));

		Function<Double, Double> weightOnMars = weight.apply(3.75);
		System.out.println("My weight on Mars: " + weightOnMars.apply(60.0));

		Function closureExample = weightOnEarthClosure();
		System.out.println("Closure example, gravity is not garbage collected: " + closureExample.apply(60.0));
	}
}
