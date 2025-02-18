package src;

class Producer extends Thread {
    private Buffer buffer;
    
    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            buffer.put(i);
            try {
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}