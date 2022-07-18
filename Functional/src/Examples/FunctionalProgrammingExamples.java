package Examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class FunctionalProgrammingExamples {

	// first we call recursively then we calculate backwards
	public static Integer headRecursionFactorial(Integer number) {
		return (number == 1) ? 1 : number * headRecursionFactorial(number - 1);
	}

	// first the multiplication is done, then the function is called recursively
	public static Integer tailRecursionFactorial(Integer number, Integer result) {
		return (number == 1) ? result : tailRecursionFactorial(number - 1, result * number);
	}

	public static void main(String[] args) {

		ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(1,3,2,5,6,2));
		System.out.println("List of number:" + numberList);

		// lambda example for sorting
		numberList.sort((n1, n2) -> n1.compareTo(n2));
		System.out.println("Sorted list: " + numberList);

		//calculate cumsum of list
		int cumsum = numberList.stream().collect(Collectors.summingInt(Integer::intValue));
		cumsum = numberList.stream().mapToInt(Integer::intValue).sum();
		cumsum = numberList.stream().reduce(0, (acc, x) -> {acc += x; return acc;});
		System.out.println("Cumulative sum: " + cumsum);

		System.out.println("Factorial of 4 (head recursion): " + headRecursionFactorial(4));
		System.out.println("Factorial of 4 (tail recursion): " + tailRecursionFactorial(4, 1));
	}
}
