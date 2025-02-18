package src;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Use: java Main <num_producers> <num_consumers>");
            return;
        }

        int numProducers = Integer.parseInt(args[0]);
        int numConsumers = Integer.parseInt(args[1]);
        
        Buffer buffer = new Buffer();
        
        for (int i = 0; i < numProducers; i++) {
            new Producer(buffer).start();
        }
        
        for (int i = 0; i < numConsumers; i++) {
            new Consumer(buffer).start();
        }
    }
}