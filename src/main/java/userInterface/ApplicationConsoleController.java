package userInterface;

import app.ConsoleService;
import containers.Period;
import containers.Week;
import users.Profile;
import users.ProfileService;

import java.io.IOException;
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
        this.userChosenProfile.getHistoryOfPeriods().get(this.userChosenProfile.getPeriodsCounter()).getPresentWeek().setActualSpendings(this.userChosenProfile.getHistoryOfPeriods().get(this.userChosenProfile.getPeriodsCounter()).getPresentWeek().getActualSpendings() + productPrize);
    }

    private void removeFromList(String category, int index) {
        this.userChosenProfile.getHistoryOfPeriods().get(userChosenProfile.getPeriodsCounter()).getPresentWeek().removeProductFromList(this.userChosenProfile, category, index);
    }

    private void buyFromWallet(String category, String productName, double productPrize) {
        this.userChosenProfile.getHistoryOfPeriods().get(userChosenProfile.getPeriodsCounter()).getPresentWeek().addProductToList(this.userChosenProfile, productName, productPrize, category);
        this.userChosenProfile.setWallet(this.userChosenProfile.getWallet() - productPrize);
        this.userChosenProfile.getHistoryOfPeriods().get(this.userChosenProfile.getPeriodsCounter()).getPresentWeek().setActualSpendings(this.userChosenProfile.getHistoryOfPeriods().get(this.userChosenProfile.getPeriodsCounter()).getPresentWeek().getActualSpendings() + productPrize);
    }


    public void profilesMenu() throws IOException {
        int choose;
        profileService.readProfilesList();
        ConsoleService.cleanConsole();
        System.out.println("WYBIERZ PROFIL\n\n");

        for (int i = 0; i < profileService.getProfiles().size(); i++) {
            System.out.println(i + ". " + profileService.getProfiles().get(i));
        }

        System.out.println("\n99. Stwórz nowy profil \n100. Usun profil \n101. Wyjscie \n\nWybór: ");
        choose = entry.nextInt();

        switch (choose) {
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
            case 101:
                System.exit(0);
            default:
                ConsoleService.cleanConsole();
                if (!profileService.getProfiles().isEmpty()) {

                    this.userChosenProfile = profileService.readProfile(profileService.getProfiles().get(choose));
                }
                mainMenu();
                break;
        }
    }


    private void mainMenu() throws IOException {
        ConsoleService.cleanConsole();

        System.out.println("ZALOGOWANO: " + userChosenProfile.getProfileName() + "\n\n\n");

       this.userChosenProfile =  profileService.readProfile(this.userChosenProfile.getProfileName());

        if (userChosenProfile.getHistoryOfPeriods().isEmpty()) {
            System.out.println("1. Zrob pierwszy krok w strone oszczedzania! [w trakcie testów]");
        } else {
            System.out.println("1. Wznów uzupełnianie swojej listy [w trakcie testów]");
        }

        System.out.println("2. Wyświetl historię [niedostępne]");
        System.out.println("3. Ustawienia profilu [niedostępne]\n\n");

        System.out.println("4. Wyloguj profil \n5. Wyjście \n Wybór: ");

        int choose = entry.nextInt();

        switch (choose) {
            case 5:
                System.exit(0);
                break;
            case 4:
                profilesMenu();
                break;
            case 1:
                if (this.userChosenProfile.getHistoryOfPeriods().isEmpty()) {
                    ConsoleService.cleanConsole();
                    System.out.println("Podaj długość okresu (w tygodniach): ");
                    Scanner scan = new Scanner(System.in);
                    int weeks = scan.nextInt();

                    ConsoleService.cleanConsole();

                    System.out.println("Podaj dochód w okresie: ");
                    double income = Double.parseDouble(scan.next());

                    this.userChosenProfile.getHistoryOfPeriods().add(new Period(weeks, userChosenProfile));
                    this.userChosenProfile.getHistoryOfPeriods().get(this.userChosenProfile.getPeriodsCounter()).calculateNewPeriodFunds(userChosenProfile, income);
                    this.userChosenProfile.incrementPeriodsCounter();
                    profileService.saveProfile(this.userChosenProfile);
                    weekScreen();
                } else {
                    weekScreen();
                }
                break;
        }
    }

    private void weekScreen() throws IOException {
        ConsoleService.cleanConsole();
        Week presentWeek = this.userChosenProfile.getHistoryOfPeriods().get(this.userChosenProfile.getPeriodsCounter()).getPresentWeek();

        System.out.println("Aktualny okres dochodowy: " + this.userChosenProfile.getHistoryOfPeriods().get(this.userChosenProfile.getPeriodsCounter()).getPeriodName());
        System.out.println("Aktualny tydzień: " + presentWeek.getWeekName());
        System.out.println("Portfel: " + this.userChosenProfile.getWallet() + "\n\nAktualne zapotrzebowanie: " + presentWeek.getActualSpendings() + "/" + this.userChosenProfile.getHistoryOfPeriods().get(this.userChosenProfile.getPeriodsCounter()).getWeeklyBudget() + "\n");

        System.out.println(this.userChosenProfile.getListOneName() + "  " + presentWeek.getListOneActualValue() + "/" + presentWeek.getListOneMAX());
        for (int i = 0; i < presentWeek.getListOne().size(); i++) {
            System.out.println(i + ". " + presentWeek.getListOne().get(i).getProductName() + " " + presentWeek.getListOne().get(i).getProductPrize() + " zł");
        }

        System.out.println("\n");

        System.out.println(this.userChosenProfile.getListTwoName() + "  " + presentWeek.getListTwoActualValue() + "/" + presentWeek.getListTwoMAX());
        for (int i = 0; i < presentWeek.getListTwo().size(); i++) {
            System.out.println(i + ". " + presentWeek.getListTwo().get(i).getProductName() + " " + presentWeek.getListTwo().get(i).getProductPrize() + " zł");
        }

        System.out.println("\n");

        System.out.println(this.userChosenProfile.getListThreeName() + "  " + presentWeek.getListThreeActualValue() + "/" + presentWeek.getListThreeMAX());
        for (int i = 0; i < presentWeek.getListThree().size(); i++) {
            System.out.println(i + ". " + presentWeek.getListThree().get(i).getProductName() + " " + presentWeek.getListThree().get(i).getProductPrize() + " zł");
        }

        System.out.println("\n");

        System.out.println("MENU");
        System.out.println("1. Dodaj produkt");
        System.out.println("2. Usuń produkt");
        System.out.println("3. Kup z portfela");
        System.out.println("4. Wyjdz do menu \nWybór: ");

        Scanner entry = new Scanner(System.in);
        int choose = entry.nextInt();

        switch (choose) {
            case 1: {
                ConsoleService.cleanConsole();
                System.out.println("Wybierz kategorię (" + this.userChosenProfile.getListOneName() + "/" + this.userChosenProfile.getListTwoName() + "/" + this.userChosenProfile.getListThreeName() + ":");
                Scanner scan = new Scanner(System.in);
                String cat = scan.next();

                ConsoleService.cleanConsole();

                System.out.println("Nazwa produktu: ");
                scan.nextLine();
                String productName = scan.nextLine();

                ConsoleService.cleanConsole();

                System.out.println("Cena produktu: ");
                double productPrize = Double.parseDouble(scan.next());

                addToList(productName, productPrize, cat);

                try {
                    profileService.saveProfile(this.userChosenProfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                weekScreen();

                break;
            }
            case 2: {
                ConsoleService.cleanConsole();
                System.out.println("Wybierz kategorię (" + this.userChosenProfile.getListOneName() + "/" + this.userChosenProfile.getListTwoName() + "/" + this.userChosenProfile.getListThreeName() + ":");

                Scanner scan = new Scanner(System.in);
                String cat = scan.next();

                ConsoleService.cleanConsole();

                System.out.println("Podaj numer produktu do usunięcia: ");
                int number = scan.nextInt();

                removeFromList(cat, number);

                try {
                    profileService.saveProfile(this.userChosenProfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                weekScreen();
                break;
            }
            case 3: {
                ConsoleService.cleanConsole();
                System.out.println("Wybierz kategorię (" + this.userChosenProfile.getListOneName() + "/" + this.userChosenProfile.getListTwoName() + "/" + this.userChosenProfile.getListThreeName() + ":");

                Scanner scan = new Scanner(System.in);
                String cat = scan.next();

                ConsoleService.cleanConsole();

                System.out.println("Nazwa produktu: ");
                scan.nextLine();
                String productName = scan.next();

                ConsoleService.cleanConsole();

                System.out.println("Cena produktu: ");
                double productPrize = scan.nextDouble();

                buyFromWallet(cat, productName, productPrize);

                try {
                    profileService.saveProfile(this.userChosenProfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                weekScreen();
                break;
            }
            case 4:
                mainMenu();
                break;
        }
    }

    public void setUserChosenProfile(Profile userChosenProfile) {
        this.userChosenProfile = userChosenProfile;
    }
}
