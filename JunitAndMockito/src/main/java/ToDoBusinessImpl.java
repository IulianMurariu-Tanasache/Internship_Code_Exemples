import java.util.ArrayList;
import java.util.List;

public class ToDoBusinessImpl {

    private ToDoService toDoService;

    public ToDoBusinessImpl(ToDoService service) {
        toDoService = service;
    }

    public List<String> retrieveToDosRelatedToSpring(String user) {
        List<String> filteredTools = new ArrayList<>();
        List<String> allToDos = toDoService.retrieveTodos(user);
        for (String todo : allToDos) {
            if (todo.contains("Spring")) {
                filteredTools.add(todo);
            }
        }

        return filteredTools;
    }
}
