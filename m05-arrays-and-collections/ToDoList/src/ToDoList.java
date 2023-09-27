import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ToDoList {

    private final List<String> todoList = new ArrayList<>();

    public void add(String todo) {
        todoList.add(todo);
    }

    public void add(Integer index, String todo) {
        if (exist(index)) todoList.add(index, todo);
        else add(todo);
    }

    public void edit(Integer index, String todo) {
        if (exist(index)) todoList.set(index, todo);
    }

    public void delete(int index) {
        if (exist(index)) todoList.remove(index);
    }

    public String list() {
        return IntStream.range(0, todoList.size())
                .mapToObj(i -> i + " - " + todoList.get(i))
                .collect(Collectors.joining("\n"));
    }

    private boolean exist(Integer index) {
        return index != null && index >= 0 && index < todoList.size();
    }

}
