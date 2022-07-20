package parallel_streams;

import java.util.Arrays;
import java.util.List;

public class MapReduce {

	public static void main(String[] args) {
		List<Employee> employees = Arrays.asList(
			new Employee("Johnathan", 2689.98),
			new Employee("Christian", 4589.89),
			new Employee("Angela", 4506.9),
			new Employee("Logan", 3209.22)
		);

		long startTime = System.nanoTime();
		double avgSalary = employees.stream()
									.mapToDouble(Employee::getSalary)
									.average()
									.getAsDouble();
		long seqTime = System.nanoTime() - startTime;
		System.out.println("Sequential average salary: " + avgSalary);

		startTime = System.nanoTime();
		avgSalary = employees.parallelStream()
							.mapToDouble(Employee::getSalary)
							.average()
							.getAsDouble();
		long parallelTime = System.nanoTime() - startTime;
		System.out.println("Parallel average salary: " + avgSalary);

		System.out.println("Sequential time: " + seqTime + "\nParallel time:   " + parallelTime);
	}
}
