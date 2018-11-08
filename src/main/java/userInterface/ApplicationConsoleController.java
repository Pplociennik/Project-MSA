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

    private void addToList(String productName, double productPrize, String category) throws InterruptedException {
        this.userChosenProfile.getHistoryOfPeriods().get(userChosenProfile.getPeriodsCounter()).getPresentWeek().addProductToList(this.userChosenProfile, productName, productPrize, category);
        //this.userChosenProfile.getHistoryOfPeriods().get(this.userChosenProfile.getPeriodsCounter()).getPresentWeek().setActualSpendings(this.userChosenProfile.getHistoryOfPeriods().get(this.userChosenProfile.getPeriodsCounter()).getPresentWeek().getActualSpendings() + productPrize);
    }

    private void removeFromList(String category, int index) throws InterruptedException {
        this.userChosenProfile.getHistoryOfPeriods().get(userChosenProfile.getPeriodsCounter()).getPresentWeek().removeProductFromList(this.userChosenProfile, category, index);
    }

    private void buyFromWallet(String category, String productName, double productPrize) throws InterruptedException {
        this.userChosenProfile.getHistoryOfPeriods().get(userChosenProfile.getPeriodsCounter()).getPresentWeek().addWalletElement(this.userChosenProfile, "<PORTFEL> " + productName, productPrize, category);
        if (productPrize < this.userChosenProfile.getWallet()) {
            this.userChosenProfile.setWallet(this.userChosenProfile.getWallet() - productPrize);
        }
        // this.userChosenProfile.getHistoryOfPeriods().get(this.userChosenProfile.getPeriodsCounter()).getPresentWeek().setActualSpendings(this.userChosenProfile.getHistoryOfPeriods().get(this.userChosenProfile.getPeriodsCounter()).getPresentWeek().getActualSpendings() + productPrize);
    }


    public void profilesMenu() throws IOException, InterruptedException {
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

                    try {
                        this.userChosenProfile = profileService.readProfile(profileService.getProfiles().get(choose));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                mainMenu();
                break;
        }
    }


    private void mainMenu() throws IOException, InterruptedException {
        ConsoleService.cleanConsole();

        System.out.println("ZALOGOWANO: " + userChosenProfile.getProfileName() + "\n\n\n");

        try {
            this.userChosenProfile = profileService.readProfile(this.userChosenProfile.getProfileName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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
            case 3:
                settingsMenu();
                break;
        }
    }

    private void weekScreen() throws IOException, InterruptedException {
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

                profileService.saveProfile(this.userChosenProfile);

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

                profileService.saveProfile(this.userChosenProfile);

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

                profileService.saveProfile(this.userChosenProfile);

                weekScreen();
                break;
            }
            case 4:
                mainMenu();
                break;
        }
    }

    private void settingsMenu() throws InterruptedException, IOException {
        ConsoleService.cleanConsole();

        String calculationType = new String("defaultType");
        if(userChosenProfile.getWalletCalculationType().equals("%")) {calculationType = "Procent";}
        else {calculationType = "Wartość";}

        System.out.println("USTAWIENIA\n");

        System.out.println("1. Nazwa listy '1': [" + userChosenProfile.getListOneName() + "]");
        System.out.println("2. Nazwa listy '2': [" + userChosenProfile.getListTwoName() + "]");
        System.out.println("3. Nazwa listy '3': [" + userChosenProfile.getListThreeName() + "]\n");

        System.out.println("4. Przelicznik listy '1': [" + userChosenProfile.getListOnePercentage() + "]");
        System.out.println("5. Przelicznik listy '2': [" + userChosenProfile.getListTwoPercentage() + "]");
        System.out.println("6. Przelicznik listy '3': [" + userChosenProfile.getListThreePercentage() + "]\n");

        System.out.println("7. Typ przeliczania kwoty wolnej: [" + calculationType + "]");
        if(calculationType.equals("Procent")) {System.out.println("8. Przelicznik kwoty wolnej: [" + userChosenProfile.getWalletPercentage() + "]\n");}
        else {System.out.println("8. Wartość kwoty wolnej: [" + userChosenProfile.getWalletFreeValue() + "]\n");}

        System.out.println("9. Wróć\n");

        System.out.print("Wybór: ");

        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);

        int entry = scanner.nextInt();

        switch(entry) {
            default:
                ConsoleService.showError("Zły numer!");
                settingsMenu();
                break;
            case 1:
                ConsoleService.cleanConsole();
                System.out.print("Wpisz nową nazwę: ");
                String newName = new String(scan.nextLine());

                userChosenProfile.setListOneName(newName);
                profileService.saveProfile(userChosenProfile);
                settingsMenu();
                break;
            case 2:
                ConsoleService.cleanConsole();
                System.out.print("Wpisz nową nazwę: ");
                String newName2 = new String(scan.nextLine());

                userChosenProfile.setListTwoName(newName2);
                profileService.saveProfile(userChosenProfile);
                settingsMenu();
                break;
            case 3:
                ConsoleService.cleanConsole();
                System.out.print("Wpisz nową nazwę: ");
                String newName3 = new String(scan.nextLine());

                userChosenProfile.setListThreeName(newName3);
                profileService.saveProfile(userChosenProfile);
                settingsMenu();
                break;
            case 4:
                ConsoleService.cleanConsole();
                System.out.print("Podaj nowy przelicznik: [>0 i <1]");
                Double counter = Double.parseDouble(scan.nextLine());

                if(counter > 1 || counter < 0) {ConsoleService.showError("Zła wartość!"); settingsMenu();}

                userChosenProfile.setListOnePercentage(counter);
                profileService.saveProfile(userChosenProfile);
                settingsMenu();
                break;
            case 5:
                ConsoleService.cleanConsole();
                System.out.print("Podaj nowy przelicznik: [>0 i <1]");
                Double counter2 = Double.parseDouble(scan.nextLine());

                if(counter2 > 1 || counter2 < 0) {ConsoleService.showError("Zła wartość!"); settingsMenu();}

                userChosenProfile.setListTwoPercentage(counter2);
                profileService.saveProfile(userChosenProfile);
                settingsMenu();
                break;
            case 6:
                ConsoleService.cleanConsole();
                System.out.print("Podaj nowy przelicznik: [>0 i <1]");
                Double counter3 = Double.parseDouble(scan.nextLine());

                if(counter3 > 1 || counter3 < 0) {ConsoleService.showError("Zła wartość!"); settingsMenu();}


                userChosenProfile.setListThreePercentage(counter3);
                profileService.saveProfile(userChosenProfile);
                settingsMenu();
                break;
            case 7:
                ConsoleService.cleanConsole();
                System.out.print("Wybierz typ przeliczania kwoty wolnej [Procent/Wartość]: ");
                String choose = new String(scan.nextLine());

                if((!choose.toUpperCase().equals("PROCENT")) || (!choose.toUpperCase().equals("WARTOSC"))) {ConsoleService.showError("Nie ma takiego typu!"); settingsMenu();}

                if(choose.toUpperCase().equals("PROCENT")) {userChosenProfile.setWalletCalculationType("%"); profileService.saveProfile(userChosenProfile);}
                else {userChosenProfile.setWalletCalculationType("zł"); profileService.saveProfile(userChosenProfile);}

                settingsMenu();
                break;
            case 8:
                ConsoleService.cleanConsole();

                if(calculationType.equals("Procent")) {System.out.print("Podaj nowy przelicznik kwoty wolnej: [>0 i <1]");}
                else {System.out.print("Podaj wartość kwoty wolnej: ");}

                Double counterFree = Double.parseDouble(scan.nextLine());

                if((calculationType.equals("Procent") && counterFree < 0) || (calculationType.equals("Wartość") && counterFree > 1)) {ConsoleService.showError("Zła wartość!");settingsMenu();}

                if(calculationType.equals("Procent")) {userChosenProfile.setWalletCalculationType("%");}
                else {userChosenProfile.setWalletCalculationType("zł");}

                profileService.saveProfile(userChosenProfile);
                settingsMenu();
                break;
            case 9:
                ConsoleService.cleanConsole();
                mainMenu();
        }
    }

    public void setUserChosenProfile(Profile userChosenProfile) {
        this.userChosenProfile = userChosenProfile;
    }
}
