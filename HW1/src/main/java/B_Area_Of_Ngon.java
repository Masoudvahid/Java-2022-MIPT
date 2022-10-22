import java.util.Scanner;

public class B_Area_Of_Ngon {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n_vertices;
        n_vertices = scan.nextInt();

        int[] x_axis = new int[n_vertices];
        int[] y_axis = new int[n_vertices];

        for (int i = 0; i < n_vertices; i++) {
            x_axis[i] = scan.nextInt();
            y_axis[i] = scan.nextInt();
        }

        double area = 0;
        for (int i = 0; i < n_vertices; i++) {
            area += x_axis[i] * y_axis[(i + 1) % (n_vertices)] - y_axis[i] * x_axis[(i + 1) % (n_vertices)];
        }
        System.out.print(Math.abs(area / 2));
    }
}