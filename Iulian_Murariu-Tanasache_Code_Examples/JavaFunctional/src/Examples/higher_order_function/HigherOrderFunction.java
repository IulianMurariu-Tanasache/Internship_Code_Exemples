package Examples.higher_order_function;

public class HigherOrderFunction {
	public static <T> IFactory<T> createFactory(IProducer<T> producer, IConfigurator<T> configurator) {
		return () -> {
			T instance = producer.produce();
			configurator.configure(instance);
			return instance;
		};
	}

	public static void main(String[] args) {
		IFactory<MutableInt> val = createFactory(() -> new MutableInt(2), (a) -> {a.setX(a.getX() + 1);
			System.out.println(a.getX());});
		System.out.println(val.create().getX());
	}
}
