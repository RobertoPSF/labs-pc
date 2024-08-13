import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class FileSimilarity {

    public static void main(String[] args) throws IOException {
        Path dirPath = Paths.get(args[0]);
        Map<String, List<Double>> fileChunks = new HashMap<>();
        
        // Step 1: Read files and divide content into chunks
        try (Stream<Path> paths = Files.walk(dirPath)) {
            paths.filter(Files::isRegularFile)
                 .forEach(file -> {
                     try {
                         List<Double> chunks = getChunks(file.toFile(), 100);
                         fileChunks.put(file.getFileName().toString(), chunks);
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 });
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
            
            similarities.forEach((file2, similarity) -> 
                result.append(" ").append(file2).append(" ").append(String.format("%.2f", similarity))
            );
            
            System.out.println(result.toString());
        }
    }

    private static List<Double> getChunks(File file, int chunkSize) throws IOException {
        List<Double> chunks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder chunk = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                chunk.append(line).append("\n");
                if (chunk.length() >= chunkSize) {
                    chunks.add(calculateSum(chunk.toString()));
                    chunk.setLength(0);
                }
            }
            if (chunk.length() > 0) {
                chunks.add(calculateSum(chunk.toString()));
            }
        }
        return chunks;
    }

    private static double calculateSum(String text) {
        // Simple sum calculation based on character values for demo purposes
        return text.chars().mapToDouble(c -> (double) c).sum();
    }

    private static double calculateSimilarity(List<Double> chunks1, List<Double> chunks2) {
        double sum1 = chunks1.stream().mapToDouble(Double::doubleValue).sum();
        double sum2 = chunks2.stream().mapToDouble(Double::doubleValue).sum();
        
        // Normalize sums to get similarity
        double maxSum = Math.max(sum1, sum2);
        return maxSum == 0 ? 0 : Math.min(sum1, sum2) / maxSum;
    }
}
