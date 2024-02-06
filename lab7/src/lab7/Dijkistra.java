package lab7;
asdfghjkl;
public class Dijkistra {
    public static void main(String[] args) {
        // Матрицын хэмжээ
        int M = 3; // A матрицын мөр
        int K = 2; // A, B матрицын багана
        int N = 3; // B матрицын hgfмөр

        // Матриц A, B болон үржвэр матриц C
        int[][] A = {{1, 4}, {2, 5}, {3, 6}};
        int[][] B = {{8, 7, 6}, {5, 4, 3}};
        int[][] C = new int[M][N];

        // Worker thread-үүдийн массив
        Thread[] workers = new Thread[M * N];

        // Матрицын үржвэр
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                C[i][j] = 0;

                // Тухайн элементийн үржвэрт ашиглагддаг worker thread-ийг үүсгэнэ
                workers[i * N + j] = new Thread(new WorkThread(i, j, A, B, C));

                // Worker thread-ийг эхлүүлэх
                workers[i * N + j].start();
            }
        }

        // Бүх worker thread-ийг холбох
        try {
            for (Thread worker : workers) {
                worker.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Матрицуудыг хэвлэх
        System.out.println("Матриц A:");
        printMatrix(A);

        System.out.println("\nМатриц B:");
        printMatrix(B);

        System.out.println("\nМатриц C (A * B):");
        printMatrix(C);
    }

    // Матрицыг хэвлэх функц
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

// Worker thread класс, тухайн элементийн үржүүлэхийн тулд Runnable интерфейс ашиглаж байна
class WorkThread implements Runnable {
    private int row;
    private int col;
    private int[][] A;
    private int[][] B;
    private int[][] C;

    public WorkThread(int row, int col, int[][] A, int[][] B, int[][] C) {
        this.row = row;
        this.col = col;
        this.A = A;
        this.B = B;
        this.C = C;
    }

    // Run метод нь тухайн worker thread-ээр хийгдэх үйлдлийг тодорхойлох функц
    public void run() {
        // C[row][col] дахь матрицын үржвэрийг тооцоолох
        for (int k = 0; k < A[0].length; k++) {
            C[row][col] += A[row][k] * B[k][col];
        }
    }
}
