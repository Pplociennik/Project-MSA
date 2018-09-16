package app;

public class ConsoleService {

    public static void showError(String errorText) {
        cleanConsole();
        System.out.print(errorText);
        cleanConsole();
    }

    public static void cleanConsole() {
        for(int i=0; i<20; i++) {
            System.out.println();
        }
    }
}
