public class Main {
    public static void main(String[] args) {
        int size = 8;
        int [][] board = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                board[i][k] = (i + k) % 2;
            }
        }

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                System.out.print((board[i][k] == 0 ? "◽" : "◾") + " ");
            }
            System.out.println();
        }
    }
}