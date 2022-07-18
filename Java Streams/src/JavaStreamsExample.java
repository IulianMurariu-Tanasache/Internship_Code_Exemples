import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class JavaStreamsExample {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("Element 1");
		list.add("Element 2");
		list.add("Element 3");

		Stream<String> stream = list.stream();

		//non-terminal operation -> returns a new stream
		Stream<String> lowercaseStream = stream.map(String::toLowerCase);

		//stream = stream.map(String::toLowerCase);
		//stream = stream.map(String::toUpperCase);

		// can't operate multiple times on the same stream
		// Stream<String> uppercaseStream = stream.map(String::toUpperCase);

		// terminal operation -> does not return a new stream
		lowercaseStream.forEach((el) -> System.out.println(el));

		// testing flatmap on custom objects
		List<RandomTetsClassForFlatMap> testList = List.of(
			new RandomTetsClassForFlatMap(1,2,3),
			new RandomTetsClassForFlatMap(4,5,6),
			new RandomTetsClassForFlatMap(7,8,9)
		);

		testList.stream().map(RandomTetsClassForFlatMap::getList).forEach(System.out::println);
		testList.stream().flatMap(x -> x.getList().stream()).forEach(System.out::println);
		testList.stream().flatMap(RandomTetsClassForFlatMap::getStream).forEach(System.out::println);
	}
}
