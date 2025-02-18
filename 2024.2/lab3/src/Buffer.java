package src;

import java.util.ArrayList;
import java.util.List;

class Buffer {
    private List<Integer> data = new ArrayList<>();
    
    public void put(int value) {
        data.add(value);
        System.out.println("Produced: " + value);
    }
    
    public int get() {
        if (!data.isEmpty()) {
            System.out.println("Buffer: " + data);
        }
        return -1;
    }
}
