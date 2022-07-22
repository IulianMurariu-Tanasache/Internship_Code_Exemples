package completable_future;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {

	public static void main(String[] args) {

		CompletableFuture.supplyAsync(() -> Arrays.asList(3,2,1,4,5,7,6))
			.thenApply(list -> {
				Collections.sort(list);
				return list;})
			.thenApply(list -> {
				List<Integer> newList = new ArrayList<>();
				for(Integer x : list) newList.add(x + 1);
				return newList;})
			.thenAccept(System.out::println);

		CompletableFuture<Integer> res = CompletableFuture.supplyAsync(() -> Arrays.asList(3,2,1,4,5,7,6))
			.thenApply(list -> {
				Collections.sort(list);
				return list;})
			.thenApply(list -> {
				List<Integer> newList = new ArrayList<>();
				for(Integer x : list) newList.add(x + 1);
				return newList;})
			.thenApply(list -> {
					Integer sum = 0;
					for(Integer x : list)
						sum += x;
					return sum;
				});
		try {
			System.out.println(res.get());
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}
}
