import java.util.List;
import java.util.stream.Stream;

public class RandomTetsClassForFlatMap {
	private int x;
	private int y;
	private int z;

	public RandomTetsClassForFlatMap(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public List<Integer> getList() {
		return List.of(x,y,z);
	}

	public Stream<Integer> getStream() {
		return Stream.of(x,y,z);
	}
}
