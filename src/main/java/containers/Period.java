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

        for (int i = 0; i < length; i++) {
            weeks[i] = new Week(userProfile);
        }

        userProfile.getHistoryOfPeriods().add(this);
    }

    public Period() {
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

    public boolean getWeekDone(int index) {
        return weekDone[index];
    }

    public int getLength() {
        return length;
    }

    public double getPeriodIncome() {
        return periodIncome;
    }

    public double getWeeklyBudget() {
        return weeklyBudget;
    }

    public Week getWeek(int index) {
        return weeks[index];
    }

    public void setPresentWeekNumber(int presentWeekNumber) {
        this.presentWeekNumber = presentWeekNumber;
    }

    public void setWeeks(Week[] weeks) {
        this.weeks = weeks;
    }

    public void setWeekDone(boolean[] weekDone) {
        this.weekDone = weekDone;
    }

    public Week[] getWeeks() {
        return weeks;
    }

    public int getWeeksLength() {
        return weeks.length;
    }

    public void addWeek(int index, Week week) {
        this.weeks[index] = week;
    }

    public void addWeekDone(int index, Boolean bool) {
        weekDone[index] = bool;
    }

    public boolean[] getWeekDone() {
        return weekDone;
    }

    public int getPresentWeekNumber() {
        return presentWeekNumber;
    }

    public boolean isWeeksEmpty() {
        boolean status;
        if (this.weeks.length == 0) {
            status = true;
        } else {
            status = false;
        }

        return status;
    }
}
