import java.util.Scanner;

public class C_Required_Sum {
    public static int countIndexes(int arr1_len, int[] arr1, int arr2_len, int[] arr2, int k) {
        int prev_j = 0;
        int result = 0;

        // Starting arr2 from end
        for (int i = arr2_len - 1; i >= 0; i--) {
            int arr2_curr = arr2[i];

            // bypass arr1 with j, which did not satisfy the condition for the arr2_curr
            for (int j = prev_j; j < arr1_len; j++) {
                int sum = arr2_curr + arr1[j];
                if (sum == k) {
                    result++;
                } else if (sum > k) {
                    // amount is more than necessary
                    // store the value of j for the next b
                    prev_j = j;
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] arguments) {
        Scanner scan = new Scanner(System.in);

        int arr1_len;
        arr1_len = scan.nextInt();
        int[] arr1 = new int[arr1_len];
        for (int i = 0; i < arr1_len; i++) {
            arr1[i] = scan.nextInt();
        }

        int arr2_len;
        arr2_len = scan.nextInt();
        int[] arr2 = new int[arr2_len];
        for (int i = 0; i < arr2_len; i++) {
            arr2[i] = scan.nextInt();
        }

        int k;
        k = scan.nextInt();

        int result = countIndexes(arr1_len, arr1, arr2_len, arr2, k);
        System.out.print(result);
    }
}


