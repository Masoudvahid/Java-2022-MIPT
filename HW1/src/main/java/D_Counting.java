import java.util.Scanner;

public class D_Counting {
    public static void main(String[] arguments) {
        Scanner scan = new Scanner(System.in);

        int n_people = scan.nextInt();
        int k = scan.nextInt();

        int person = 0;
        for (int count = 2; count <= n_people; count++) {
            person = (person + k) % count;
        }
        System.out.print(person + 1);
    }
}
