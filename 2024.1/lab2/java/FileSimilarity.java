import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileSimilarity {

    public static void main(String[] args) throws IOException {
        Path dirPath = Paths.get(args[0]);
        Map<String, List<Double>> fileChunks = new HashMap<>();
        
        // Step 1: Read files and divide content into chunks
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
            for (Path path : stream) {
                if (Files.isRegularFile(path)) {
                    try {
                        List<Double> chunks = getChunks(path.toFile(), 100);
                        fileChunks.put(path.getFileName().toString(), chunks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Step 2: Calculate sums of chunks and compare
        Map<String, Map<String, Double>> similarityMap = new HashMap<>();
        for (Map.Entry<String, List<Double>> entry1 : fileChunks.entrySet()) {
            String file1 = entry1.getKey();
            List<Double> chunks1 = entry1.getValue();
            Map<String, Double> similarities = new HashMap<>();
            
            for (Map.Entry<String, List<Double>> entry2 : fileChunks.entrySet()) {
                String file2 = entry2.getKey();
                if (!file1.equals(file2)) {
                    List<Double> chunks2 = entry2.getValue();
                    double similarity = calculateSimilarity(chunks1, chunks2);
                    similarities.put(file2, similarity);
                }
            }
            similarityMap.put(file1, similarities);
        }

        // Print the results
        for (Map.Entry<String, Map<String, Double>> entry : similarityMap.entrySet()) {
            String file1 = entry.getKey();
            Map<String, Double> similarities = entry.getValue();
            StringBuilder result = new StringBuilder(file1);
            
            for (Map.Entry<String, Double> similarityEntry : similarities.entrySet()) {
                String file2 = similarityEntry.getKey();
                double similarity = similarityEntry.getValue();
                result.append(" ").append(file2).append(" ").append(String.format("%.2f", similarity));
            }
            
            System.out.println(result.toString());
        }
    }

    private static List<Double> getChunks(File file, int chunkSize) throws IOException {
        List<Double> chunks = new ArrayList<>();
        try (InputStream inputStream = new FileInputStream(file)) {
            byte[] buffer = new byte[chunkSize];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                double sum = calculateSum(buffer, bytesRead);
                chunks.add(sum);
            }
        }
        return chunks;
    }

    private static double calculateSum(byte[] buffer, int length) {
        double sum = 0;
        for (int i = 0; i < length; i++) {
            sum += Byte.toUnsignedInt(buffer[i]);
        }
        return sum;
    }

    private static double calculateSimilarity(List<Double> chunks1, List<Double> chunks2) {
        double sum1 = 0;
        for (double chunk : chunks1) {
            sum1 += chunk;
        }

        double sum2 = 0;
        for (double chunk : chunks2) {
            sum2 += chunk;
        }
        
        // Normalize sums to get similarity
        double maxSum = Math.max(sum1, sum2);
        return maxSum == 0 ? 0 : Math.min(sum1, sum2) / maxSum;
    }
}
