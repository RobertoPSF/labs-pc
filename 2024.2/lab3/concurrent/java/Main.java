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
        
        for (int i = 0; i < numProducers; i++) {
            Producer producer = new Producer(buffer, maxItemsPerProducer, 100);
            producer.produce();
        }
        
        for (int i = 0; i < numConsumers; i++) {
            Consumer consumer = new Consumer(buffer,150, itemsPerConsumer);
            consumer.process();
        }
    }
}
