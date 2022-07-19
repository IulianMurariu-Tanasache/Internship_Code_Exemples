package parallel_streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ParallelStreams {

	public static void main(String[] args) {
		List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
		listOfNumbers.parallelStream().forEach(number ->
			System.out.println(number + " " + Thread.currentThread().getName())
		);

		long startTime = System.nanoTime();
		IntStream.range(0,100).forEach(System.out::println);
		long endTime = System.nanoTime();
		long seqTime = endTime - startTime;

		startTime = System.nanoTime();
		IntStream.range(0,100).parallel().forEach(System.out::println);
		endTime = System.nanoTime();
		long parallelTime = endTime - startTime;

		System.out.println("Sequential time:\t" + seqTime + "\nParallel time:  \t" + parallelTime);
	}

}
