package users;

import containers.Period;

import java.util.ArrayList;

public class Profile {

    /* profile info */
    private String profileName;

    /* counters */
    private int periodsCounter;
    private int weeksCounter;
    private int productsCounter;

    /* containers */
    ArrayList<Period> historyOfPeriods;
    private String listOneName;
    private String listTwoName;
    private String listThreeName;
    private double wallet;

    /* calculating info */
    private double listOnePercentage;
    private double listTwoPercentage;
    private double listThreePercentage;
    private double walletPercentage;
    private double walletFreeValue;

    private String walletCalculationType;

    private void addToWallet(double value) {
        wallet += value;
    }


    public Profile(String profileName) {
        this.profileName = profileName;
        this.periodsCounter = 0;
        this.weeksCounter = 0;
        this.productsCounter = 0;
        this.historyOfPeriods = new ArrayList<Period>();
        this.listOneName = "Żywność";
        this.listTwoName = "Napoje";
        this.listThreeName = "Wydatki";
        this.wallet = 0.0;
        this.listOnePercentage = 0.35;
        this.listTwoPercentage = 0.25;
        this.listThreePercentage = 0.40;
        this.walletPercentage = 0.60;
        this.walletFreeValue = 1000;
        this.walletCalculationType = "%";
    }

    public void incrementPeriodsCounter() {
        this.periodsCounter++;
    }

    public int getPeriodsCounter() {
        return periodsCounter;
    }

    public int getWeeksCounter() {
        return weeksCounter;
    }

    public double getWallet() {
        return wallet;
    }

    public double getWalletPercentage() {
        return walletPercentage;
    }

    public String getWalletCalculationType() {
        return walletCalculationType;
    }

    public double getWalletFreeValue() {
        return walletFreeValue;
    }

    public int getProductsCounter() {
        return productsCounter;
    }

    public String getProfileName() {
        return profileName;
    }

    public ArrayList<Period> getHistoryOfPeriods() {
        return historyOfPeriods;
    }

    public String getListOneName() {
        return listOneName;
    }

    public String getListTwoName() {
        return listTwoName;
    }

    public String getListThreeName() {
        return listThreeName;
    }

    public double getListOnePercentage() {
        return listOnePercentage;
    }

    public double getListTwoPercentage() {
        return listTwoPercentage;
    }

    public double getListThreePercentage() {
        return listThreePercentage;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }


}
