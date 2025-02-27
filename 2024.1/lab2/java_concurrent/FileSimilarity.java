import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class FileSimilarity {

    private static final Semaphore semaphore = new Semaphore(1); // Semaphore for synchronization

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            System.err.println("Usage: java FileSimilarity filepath1 filepath2 filepathN");
            System.exit(1);
        }

        // Create a map to store the fingerprint for each file
        Map<String, List<Long>> fileFingerprints = new HashMap<>();
        List<Thread> fingerprintThreads = new ArrayList<>();
        List<Thread> comparisonThreads = new ArrayList<>();

        // Calculate the fingerprint for each file using threads
        for (String path : args) {
            Thread thread = new Thread(() -> {
                try {
                    List<Long> fingerprint = fileSum(path);
                    semaphore.acquire(); // Acquire semaphore before accessing the shared resource
                    try {
                        fileFingerprints.put(path, fingerprint);
                    } finally {
                        semaphore.release(); // Release semaphore after updating the map
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
            fingerprintThreads.add(thread);
            thread.start();
        }

        // Wait for all fingerprint calculation threads to complete
        for (Thread thread : fingerprintThreads) {
            thread.join();
        }

        // Compare each pair of files using threads
        for (int i = 0; i < args.length; i++) {
            for (int j = i + 1; j < args.length; j++) {
                String file1 = args[i];
                String file2 = args[j];
                Thread comparisonThread = new Thread(() -> {
                    try {
                        List<Long> fingerprint1 = fileFingerprints.get(file1);
                        List<Long> fingerprint2 = fileFingerprints.get(file2);
                        float similarityScore = similarity(fingerprint1, fingerprint2);
                        System.out.println("Similarity between " + file1 + " and " + file2 + ": " + (similarityScore * 100) + "%");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                comparisonThreads.add(comparisonThread);
                comparisonThread.start();
            }
        }

        // Wait for all comparison threads to complete
        for (Thread thread : comparisonThreads) {
            thread.join();
        }
    }

    private static List<Long> fileSum(String filePath) throws IOException {
        File file = new File(filePath);
        List<Long> chunks = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] buffer = new byte[100];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                long sum = sum(buffer, bytesRead);
                chunks.add(sum);
            }
        }
        return chunks;
    }

    private static long sum(byte[] buffer, int length) {
        long sum = 0;
        for (int i = 0; i < length; i++) {
            sum += Byte.toUnsignedInt(buffer[i]);
        }
        return sum;
    }

    private static float similarity(List<Long> base, List<Long> target) {
        int counter = 0;
        List<Long> targetCopy = new ArrayList<>(target);

        for (Long value : base) {
            if (targetCopy.contains(value)) {
                counter++;
                targetCopy.remove(value);
            }
        }

        return (float) counter / base.size();
    }
}
