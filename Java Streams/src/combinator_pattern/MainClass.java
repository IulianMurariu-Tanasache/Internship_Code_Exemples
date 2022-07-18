package combinator_pattern;

import java.time.LocalDate;
import java.util.function.Predicate;

public class MainClass {

	public static void main(String[] args) {
		Customer customer = new Customer(
			"Alice",
			"alice@gmail.com",
			"+07787878788",
			LocalDate.of(2000,1,1)
		);

		CustomerValidatorService validatorService = new CustomerValidatorService();
		System.out.println(validatorService.isValid(customer));

		// using combinator pattern
		System.out.println(
			CustomerRegistrationValidator
				.isEmailValid()
				.and(CustomerRegistrationValidator.isPhoneValid())
				.and(CustomerRegistrationValidator.isAdult())
				.apply(customer));

		Predicate<Customer> isEmailValid = c -> c.getEmail().contains("@");
		Predicate<Customer> isPhoneNumberValid = c -> c.getPhoneNumber().contains("+0");

		System.out.println(
			isEmailValid.and(isPhoneNumberValid).test(customer)
		);
	}
}
