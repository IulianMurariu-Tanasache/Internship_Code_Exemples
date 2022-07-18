import java.util.Optional;

public class OptionalExamples {

	public static void main(String[] args) {
		Object value = Optional.empty().orElseGet(() -> "default value");
		System.out.println(value);

		value = Optional.of("Not a default value").orElse("default value");
		System.out.println(value);

		Optional.of("This is an element").ifPresentOrElse(
				System.out::println,
				() -> System.out.println("Empty optional")
		);

		value = Optional.empty().orElseThrow(IllegalStateException::new);
		System.out.println(value);
	}
}
