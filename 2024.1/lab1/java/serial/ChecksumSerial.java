import java.io.*;
import java.math.BigInteger;
import java.nio.file.*;
import java.security.*;

public class ChecksumSerial {

    public static void main(String[] args) {
        String rootDirectory = "../root_directory";
        checksumFilesInDirectory(rootDirectory);
    }

    public static BigInteger calculateChecksum(String filePath) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (InputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[4096];
            int n = 0;
            while ((n = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, n);
            }
        }
        byte[] hashBytes = digest.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return new BigInteger(hexString.toString().toUpperCase(), 16);
    }

    public static void checksumFilesInDirectory(String directory) {
        try {
            Files.walk(Paths.get(directory))
                    .filter(Files::isRegularFile)
                    .forEach(file -> {
                        try {
                            BigInteger checksum = calculateChecksum(file.toString());
                            System.out.println(file + ": " + checksum.toString());
                        } catch (IOException | NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
