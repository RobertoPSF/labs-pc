package src;

class Buffer {
    private int data = -1;
    
    public void put(int value) {
        System.out.println("Produced: " + value);
        data = value;
    }
    
    public int get() {
        System.out.println("Consumed: " + data);
        return data;
    }
}
