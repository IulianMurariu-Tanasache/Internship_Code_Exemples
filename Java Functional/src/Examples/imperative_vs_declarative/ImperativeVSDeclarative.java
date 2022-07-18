package Examples.imperative_vs_declarative;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ImperativeVSDeclarative {

	public static void main(String[] args) {
		List<Person> persons = List.of(
			new Person("John", Gender.MALE),
			new Person("Karen", Gender.FEMALE),
			new Person("Kyle", Gender.MALE),
			new Person("Anna", Gender.FEMALE)
		);

		// create new list only with females and print it
		// imperative
		System.out.println("Imperative");
		List<Person> onlyFemales = new ArrayList<>();

		for(Person person: persons) {
			if(person.getGender().equals(Gender.FEMALE))
				onlyFemales.add(person);
		}

		for(Person person: onlyFemales) {
			System.out.println(person);
		}

		//declarative
		System.out.println("Declarative");
		Predicate<Person> isFemale = (person) -> person.getGender() == Gender.FEMALE;
		onlyFemales = persons.stream().filter(isFemale).collect(Collectors.toList());
		onlyFemales.forEach(System.out::println);
	}
}
