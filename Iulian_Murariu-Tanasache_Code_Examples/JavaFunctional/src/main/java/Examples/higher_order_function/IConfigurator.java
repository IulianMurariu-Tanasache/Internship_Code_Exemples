package Examples.higher_order_function;

@FunctionalInterface
interface IConfigurator<T> {

	void configure(T t);
}
