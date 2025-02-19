import java.util.ArrayList;
import java.util.List;

class Buffer {
    private final List<Integer> data = new ArrayList<>();
    
    public synchronized void put(int value) {
        data.add(value);
        System.out.println("Produced: " + value + " | Buffer size: " + data.size());
        notifyAll();
    }
    
    public synchronized int remove() {
        while (data.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        int value = data.remove(0);
        System.out.println("Consumed: " + value + " | Buffer size: " + data.size());
        return value;
    }
}