package app;

import userInterface.ApplicationConsoleController;
import users.Profile;
import users.ProfileService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ProfileService profileService = new ProfileService();
        ApplicationConsoleController app = new ApplicationConsoleController();
        try {
            app.profilesMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
