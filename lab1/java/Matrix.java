import java.util.Random;

public class Matrix {
    public static int[][] generateMatrix(int size) {
        int[][] matrix = new int[size][size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(29500 - 250 + 1) + 250;
            }
        }

        return matrix;
    }

    public static int Min(int[][] matrix) {
        int smallest = Integer.MAX_VALUE;

        for (int[] row : matrix) {
            for (int element : row) {
                if (element < smallest) {
                    smallest = element;
                }
            }
        }

        return smallest;
    }

    public static int Max(int[][] matrix) {
        int largest = Integer.MIN_VALUE;

        for (int[] row : matrix) {
            for (int element : row) {
                if (element > largest) {
                    largest = element;
                }
            }
        }

        return largest;
    }

    public static void main(String[] args) {
        int size = 5;  // Substitua pelo tamanho desejado
        int[][] matrix = generateMatrix(size);
        int minValue = Min(matrix);
        int maxValue = Max(matrix);

        // Imprimir os resultados
        System.out.println("Matriz gerada:");
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

        System.out.println("Menor valor: " + minValue);
        System.out.println("Maior valor: " + maxValue);
    }
}
