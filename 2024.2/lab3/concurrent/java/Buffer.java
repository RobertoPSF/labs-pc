import java.util.ArrayList;
import java.util.List;

class Buffer {
    private final List<Integer> data = new ArrayList<>();
    
    public void put(int value) {
        data.add(value);
        System.out.println("Produced: " + value + " | Buffer size: " + data.size());
    }
    
    public int remove() {
        if (!data.isEmpty()) {
            int value = data.remove(0);
            System.out.println("Consumed: " + value + " | Buffer size: " + data.size());
            return value;
        }
        return -1;
    }
}