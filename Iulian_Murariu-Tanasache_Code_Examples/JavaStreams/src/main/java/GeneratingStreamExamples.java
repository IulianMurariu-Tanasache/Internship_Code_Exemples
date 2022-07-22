import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GeneratingStreamExamples {

	public static void main(String[] args) {
		Stream<String> newStream = Stream.empty();

		System.out.println("Creating a stream with a Stream.Builder");
		Stream.Builder<String> streamBuilder = Stream.builder();
		System.out.println(
			streamBuilder
				.add("element 1")
				.add("element 2")
				.add("element 3")
				.build()
				.max(Comparator.naturalOrder())
				.get());

		System.out.println("Generating elements with .generate() and supplier");
		Stream.generate(generateElements())
			.limit(10)
			.forEach(System.out::println);

		System.out.println("Generating elements with .iterate()");
		Stream.iterate(1, x -> x + 1)
			.limit(10)
			.map(x -> "element " + x)
			.forEach(System.out::println);

		System.out.println("Generating stream using IntStream");
		IntStream
			.range(1,11)
			.mapToObj(x -> "element " + x)
			.forEach(System.out::println);

	}

	// best way to generate numbers ?
	public static Supplier<String> generateElements() {
		AtomicInteger i = new AtomicInteger(0);
		return () -> "element " +  i.incrementAndGet();
	}
}
