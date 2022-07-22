package Examples.higher_order_function;

@FunctionalInterface
interface IFactory<T> {

	T create();
}
