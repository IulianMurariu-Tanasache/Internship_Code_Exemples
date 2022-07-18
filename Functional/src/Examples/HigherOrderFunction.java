package Examples;

interface IFactory<T> {
	T create();
}

interface IProducer<T> {
	T produce();
}

interface IConfigurator<T> {
	void configure(T t);
}

class MutableInt {
	private int x;

	public MutableInt(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
}


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
