import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ToDoServiceStub  implements  ToDoService{

    @Override
    public List<String> retrieveTodos(String value) {
        return Arrays.asList("Learn Spring MVC", "Learn Spring Security", "Learn to dance");
    }
}
