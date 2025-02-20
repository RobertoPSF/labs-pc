class Consumer {
    private final Buffer buffer;
    private final int sleepTime;
    
    public Consumer(Buffer buffer, int sleepTime) {
        this.buffer = buffer;
        this.sleepTime = sleepTime;
    }
    
    public void process() {
        while (true) {
            int item = buffer.remove();
            if (item == -1) break;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}