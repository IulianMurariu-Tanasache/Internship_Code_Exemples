package Examples;

public class ImmutableDataStructure {
	private final String someData;
	private final AnotherImmutableStruct moreData;

	public ImmutableDataStructure(String someData, AnotherImmutableStruct moreData) {
		this.someData = someData;
		this.moreData = moreData;
	}

	public String getSomeData() {
		return someData;
	}

	public AnotherImmutableStruct getMoreData() {
		return moreData;
	}
}

class AnotherImmutableStruct {
	private final Integer number;
	private final Object object;

	AnotherImmutableStruct(Integer number, Object object) {
		this.number = number;
		this.object = object;
	}

	public Integer getNumber() {
		return number;
	}

	public Object getObject() {
		return object;
	}
}
