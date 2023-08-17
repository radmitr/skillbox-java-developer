import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * LRU, или LRU cache (Least Recently Used) — алгоритм для хранения
 * ограниченного объема данных: из хранилища вытесняется информация,
 * которая не использовалась дольше всего.
 */
public class LRUCash <T> {

    int size;
    List<T> elements;

    public LRUCash(int size) {
        this.size = size;
//        elements = new ArrayList<>();
        elements = new LinkedList<>();
    }

    public void addElement(T element) {
        int currentSize = elements.size();
        if (currentSize >= size) {
            for (int i = 0; i < currentSize - size + 1; i++) {
                elements.remove(0);
            }
        }
        elements.add(element);
    }

    public T getElement(int i) {
        if (i >= elements.size()) {
            return null;
        }
        return elements.get(i);
    }

    public List<T> getAllElements() {
        return elements;
    }
}
