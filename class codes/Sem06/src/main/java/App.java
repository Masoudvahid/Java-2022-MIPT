public final class App {
    private App() {
    }

    /**
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        if (args.length == 0) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }

        final int iterations = 10;
        for (int i = 0; i < iterations; i++) {
            if (i == 8) {
                break;
            }
        }
    }

    public static String getString(String str){
        return str;
    }
}
