import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateFiles {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Informe o total de arquivos");
            System.exit(1);
        }

        int N = Integer.parseInt(args[0]);
        String rootDirectory = "../root_directory";

        try {
            cleanDirectoriesAndFiles(rootDirectory);
            createDirectoriesAndFiles(rootDirectory, N);
            System.out.println("Diretórios e arquivos criados em '" + rootDirectory + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String createRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    private static void createDirectoriesAndFiles(String root, int N) throws IOException {
        File rootDir = new File(root);
        if (!rootDir.exists()) {
            if (!rootDir.mkdirs()) {
                throw new IOException("Falha ao criar o diretório: " + root);
            }
        }

        for (int j = 1; j <= N; j++) {
            File file = new File(root, "file_" + j + ".txt");
            try (FileWriter writer = new FileWriter(file)) {
                String randomString = createRandomString(100);  // Pode ajustar o comprimento da string
                writer.write(randomString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void cleanDirectoriesAndFiles(String root) throws IOException {
        File rootDir = new File(root);
        if (rootDir.exists()) {
            deleteRecursive(rootDir);
            System.out.println("Conteúdo de '" + root + "' removido com sucesso.");
        }
    }

    private static void deleteRecursive(File fileOrDir) {
        if (fileOrDir.isDirectory()) {
            for (File child : fileOrDir.listFiles()) {
                deleteRecursive(child);
            }
        }
        fileOrDir.delete();
    }
}
