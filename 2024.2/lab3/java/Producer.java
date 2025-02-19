class Producer extends Thread {
    private final Buffer buffer;
    private final int maxItems;
    private final int sleepTime;
    
    public Producer(Buffer buffer, int maxItems, int sleepTime) {
        this.buffer = buffer;
        this.maxItems = maxItems;
        this.sleepTime = sleepTime;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < maxItems; i++) {
            try {
                Thread.sleep(sleepTime);
                buffer.put((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}