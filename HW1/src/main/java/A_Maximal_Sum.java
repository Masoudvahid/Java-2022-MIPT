import java.util.Scanner;

public class A_Maximal_Sum {
    public static void main(String[] args) {
        int array_size;
        Scanner scan = new Scanner(System.in);
        array_size = scan.nextInt();
        int[] first_array = new int[array_size];
        for (int i = 0; i < array_size; i++) {
            first_array[i] = scan.nextInt();
        }
        int[] second_array = new int[array_size];
        for (int i = 0; i < array_size; i++) {
            second_array[i] = scan.nextInt();
        }

        int max = Integer.MIN_VALUE;
        int[] max_position_1 = new int[array_size];
        int current_max = 0;
        for (int i = 0; i < array_size; i++) {
            if (first_array[i] > max) {
                max = first_array[i];
                current_max = i;
            }
            max_position_1[i] = current_max;
        }
        int first_pos = 0;
        int second_pos = 0;
        for (int i = 0; i < array_size; i++) {
            current_max = second_array[i] + first_array[max_position_1[i]];
            if (current_max > max) {
                max = current_max;
                first_pos = max_position_1[i];
                second_pos = i;
            }
        }
        System.out.print(first_pos + " " + second_pos);
    }
}