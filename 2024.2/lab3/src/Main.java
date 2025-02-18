package src;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        
        int numProducers = 2;
        int numConsumers = 2;
        
        Thread[] producers = new Thread[numProducers];
        Thread[] consumers = new Thread[numConsumers];
        
        for (int i = 0; i < numProducers; i++) {
            producers[i] = new Producer(buffer);
            producers[i].start();
        }
        
        for (int i = 0; i < numConsumers; i++) {
            consumers[i] = new Consumer(buffer);
            consumers[i].start();
        }
        
        try {
            for (Thread producer : producers) {
                producer.join();
            }
            for (Thread consumer : consumers) {
                consumer.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}