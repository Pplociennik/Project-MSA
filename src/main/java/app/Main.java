package app;

import users.Profile;
import users.ProfileService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
    System.out.println("Tutaj będzie startować aplikacja!!");

        Profile profile = new Profile("PSZEMEG");
        ProfileService profileService = new ProfileService();
        try {
            profileService.saveProfile(profile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
