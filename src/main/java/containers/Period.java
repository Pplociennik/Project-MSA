package containers;

import users.Profile;

public class Period {

    private Week[] weeks;
    private boolean[] weekDone;

    private String periodName;

    public Period(int length, Profile userProfile) {
        this.weeks = new Week[length];
        this.weekDone = new boolean[length];
        this.periodName = "Okres " + userProfile.getPeriodsCounter();
    }

    public Week[] getWeeks() {
        return weeks;
    }

    public String getPeriodName() {
        return periodName;
    }

    public boolean[] getWeekDone() {
        return weekDone;
    }
}
