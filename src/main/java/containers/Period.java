package containers;

import users.Profile;

public class Period {

    private Week[] weeks;
    private boolean[] weekDone;
    private int presentWeekNumber;
    private int length;

    private String periodName;
    private double periodIncome;
    private double weeklyBudget;


    public Period(int length, Profile userProfile) {
        this.length = length;
        this.weeks = new Week[length];
        this.weekDone = new boolean[length];
        this.periodName = "Okres " + presentWeekNumber;
        this.presentWeekNumber = 0;
    }

    public void calculateNewPeriodFunds(Profile userProfile, double income) {
        double tempWallet = 0.0;
        double tempRest = 0.0;

        if (userProfile.getWalletCalculationType().equals("%")) {
            tempWallet = income * userProfile.getWalletPercentage();
            tempRest = income - (income * userProfile.getWalletPercentage());
        } else if (userProfile.getWalletCalculationType().equals("zl")) {
            tempWallet = userProfile.getWalletFreeValue();
            tempRest = income - userProfile.getWalletFreeValue();
        }

        this.periodIncome = income;
        this.weeklyBudget = tempRest / length;

        userProfile.setWallet(userProfile.getWallet() + tempWallet);
        this.weeks[presentWeekNumber].setListOneMAX(this.weeklyBudget * userProfile.getListOnePercentage());
        this.weeks[presentWeekNumber].setListTwoMAX(this.weeklyBudget * userProfile.getListTwoPercentage());
        this.weeks[presentWeekNumber].setListThreeMAX(this.weeklyBudget * userProfile.getListThreePercentage());
    }

    public Week getPresentWeek() {
        return weeks[presentWeekNumber];
    }

    public String getPeriodName() {
        return periodName;
    }

    public boolean[] getWeekDone() {
        return weekDone;
    }
}
