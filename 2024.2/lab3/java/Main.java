public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Use: java Main <num_producers> <num_consumers> <max_itens>");
            return;
        }
        
        int numProducers = Integer.parseInt(args[0]);
        int numConsumers = Integer.parseInt(args[1]);
        int maxItemsPerProducer = Integer.parseInt(args[2]);

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
