class Consumer extends Thread {
    private final Buffer buffer;
    private final int maxItems;
    
    public Consumer(Buffer buffer, int maxItems) {
        this.buffer = buffer;
        this.maxItems = maxItems;
    }
    
    public void process() {
        for (int i = 0; i < maxItems; i++) {
            buffer.remove();
        }
    }
}