package app;

public class ConsoleService {

    public static void showError(String errorText) throws InterruptedException {
        cleanConsole();
        System.out.print(errorText);
        Thread.sleep(1000);
        cleanConsole();
    }

    public static void cleanConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println("\b");
        }
    }
}
