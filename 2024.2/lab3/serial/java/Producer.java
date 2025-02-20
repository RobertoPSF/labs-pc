class Producer extends Thread {
    private final Buffer buffer;
    private final int maxItems;
    
    public Producer(Buffer buffer, int maxItems) {
        this.buffer = buffer;
        this.maxItems = maxItems;
    }
    
    public void produce() {
        for (int i = 0; i < maxItems; i++) {
            buffer.put((int) (Math.random() * 100));
        }
    }
}