public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java Main <num_producers> <num_consumers>");
            return;
        }
        
        int numProducers = Integer.parseInt(args[0]);
        int numConsumers = Integer.parseInt(args[1]);
        int maxItemsPerProducer = 10;
        int totalItems = numProducers * maxItemsPerProducer;
        int itemsPerConsumer = totalItems / numConsumers;
        
        Buffer buffer = new Buffer();
        Thread[] producers = new Thread[numProducers];
        Thread[] consumers = new Thread[numConsumers];
        
        for (int i = 0; i < numProducers; i++) {
            producers[i] = new Producer(buffer, maxItemsPerProducer, 100);
            producers[i].start();
        }
        
        for (int i = 0; i < numConsumers; i++) {
            consumers[i] = new Consumer(buffer, 150, itemsPerConsumer);
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
            Thread.currentThread().interrupt();
        }
    }
}
