import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Checksum {

    public static int calculateChecksum(String filePath) throws IOException {
        int checksum = 0;
        try (FileInputStream fis = new FileInputStream(filePath)) {
            int byteRead;
            while ((byteRead = fis.read()) != -1) {
                checksum += byteRead;
            }
        }
        return checksum;
    }

    public static long checksumFiles(String path) throws Exception{
        Path filePath = Paths.get(path);
        long checksum = 0;
        if (Files.isRegularFile(filePath)) {
            try {
                checksum = calculateChecksum(filePath.toString());
                
            } catch (IOException e) {
                System.err.println("Erro ao calcular checksum para o arquivo: " + filePath);
            }
        } else {
            throw new Exception("Arquivo irregular: " + path);
        }
        return checksum;
    }

    public static void main(String[] args) throws Exception{
        if (args.length < 1) {
            System.err.println("Uso: java Checksum <caminho_para_arquivos>");
            System.exit(1);
        }
        for (String path : args) {
            long sum = checksumFiles(path);
            System.out.println(path + " : " + sum);
        }
    }
}
