package userInterface;

import app.ConsoleService;
import users.Profile;
import users.ProfileService;

import java.util.Scanner;

public class ApplicationConsoleController {

    private Profile userChosenProfile;
    ProfileService profileService = new ProfileService();
    private Scanner entry = new Scanner(System.in);

    public ApplicationConsoleController(Profile userChosenProfile) {
        this.userChosenProfile = userChosenProfile;
    }

    public ApplicationConsoleController() {
    }

    private void addToList(String productName, double productPrize, String category) {
        this.userChosenProfile.getHistoryOfPeriods().get(userChosenProfile.getPeriodsCounter()).getPresentWeek().addProductToList(this.userChosenProfile, productName, productPrize, category);
    }

    private void removeFromList(String category, int index) {
        this.userChosenProfile.getHistoryOfPeriods().get(userChosenProfile.getPeriodsCounter()).getPresentWeek().removeProductFromList(this.userChosenProfile, category, index);
    }



    public void profilesMenu() {
        int choose;
        profileService.readProfilesList();
        ConsoleService.cleanConsole();
        System.out.println("WYBIERZ PROFIL\n\n");

        for(int i=0; i<profileService.getProfiles().size(); i++) {
            System.out.println(i + ". " + profileService.getProfiles().get(i));
        }

        System.out.println("\n99. Stwórz nowy profil \n100. Usun profil\n\nWybór: ");
        choose = entry.nextInt();

        switch(choose) {
            case 99:
                ConsoleService.cleanConsole();
                System.out.println("Wybierz nazwę dla swojego profilu: ");
                String tempName = entry.next();
                profileService.createNewProfile(tempName);
                profilesMenu();
                break;
            case 100:
                ConsoleService.cleanConsole();
                System.out.println("Wybierz profil do usunięcia: ");
                int deleteChoose = entry.nextInt();
                profileService.removeProfile(deleteChoose);
                profilesMenu();
                break;
            default:
                ConsoleService.cleanConsole();
                if(!profileService.getProfiles().isEmpty()) {

                this.userChosenProfile = profileService.readProfile(profileService.getProfiles().get(choose));}
                mainMenu();
                break;
        }
    }


    private void mainMenu() {
        ConsoleService.cleanConsole();

        System.out.println("ZALOGOWANO: " + userChosenProfile.getProfileName() + "\n\n\n");

        if(userChosenProfile.getHistoryOfPeriods().isEmpty()) {System.out.println("1. Zrob pierwszy krok w strone oszczedzania!");}
        else {System.out.println("1. Wznów uzupełnianie swojej listy");}

        System.out.println("2. Wyświetl historię [niedostępne]");
        System.out.println("3. Ustawienia profilu [niedostępne]\n\n");

        System.out.println("4. Wyloguj profil \n5. Wyscie \n Wybór: ");

        int choose = entry.nextInt();

        switch(choose) {
            case 5:
                System.exit(0);
                break;
        }
    }

    public void setUserChosenProfile(Profile userChosenProfile) {
        this.userChosenProfile = userChosenProfile;
    }
}
