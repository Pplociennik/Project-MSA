package app;

public class Error {

    public static void showError(String errorText) {
        cleanConsole();
        System.out.print(errorText);
        cleanConsole();
    }

    public static void cleanConsole() {
        for(int i=0; i<200; i++) {
            System.out.println();
        }
    }
}
