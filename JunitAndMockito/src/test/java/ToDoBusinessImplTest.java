import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ToDoBusinessImplTest {

    @Test
    @DisplayName("Using a stub")
    void usingAStub() {
        ToDoService toDoService = new ToDoServiceStub();
        ToDoBusinessImpl toDoBusiness = new ToDoBusinessImpl(toDoService);
        List<String> todos = toDoBusiness.retrieveToDosRelatedToSpring("user");
        assertEquals(2, todos.size());
    }


}
