class Consumer {
    private final Buffer buffer;
    private final int sleepTime;
    private final int maxItems;
    
    public Consumer(Buffer buffer, int sleepTime, int maxItems) {
        this.buffer = buffer;
        this.sleepTime = sleepTime;
        this.maxItems = maxItems;
    }
    
    public void process() {
        for (int i = 0; i < maxItems; i++) {
            buffer.remove();
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}