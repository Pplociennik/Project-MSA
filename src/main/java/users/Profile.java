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

    public Profile() {
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

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setPeriodsCounter(int periodsCounter) {
        this.periodsCounter = periodsCounter;
    }

    public void setWeeksCounter(int weeksCounter) {
        this.weeksCounter = weeksCounter;
    }

    public void setProductsCounter(int productsCounter) {
        this.productsCounter = productsCounter;
    }

    public void setHistoryOfPeriods(ArrayList<Period> historyOfPeriods) {
        this.historyOfPeriods = historyOfPeriods;
    }

    public void setListOneName(String listOneName) {
        this.listOneName = listOneName;
    }

    public void setListTwoName(String listTwoName) {
        this.listTwoName = listTwoName;
    }

    public void setListThreeName(String listThreeName) {
        this.listThreeName = listThreeName;
    }

    public void setListOnePercentage(double listOnePercentage) {
        this.listOnePercentage = listOnePercentage;
    }

    public void setListTwoPercentage(double listTwoPercentage) {
        this.listTwoPercentage = listTwoPercentage;
    }

    public void setListThreePercentage(double listThreePercentage) {
        this.listThreePercentage = listThreePercentage;
    }

    public void setWalletPercentage(double walletPercentage) {
        this.walletPercentage = walletPercentage;
    }

    public void setWalletFreeValue(double walletFreeValue) {
        this.walletFreeValue = walletFreeValue;
    }

    public void setWalletCalculationType(String walletCalculationType) {
        this.walletCalculationType = walletCalculationType;
    }
}
