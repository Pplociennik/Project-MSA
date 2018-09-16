package userInterface;

import users.Profile;

public class ApplicationConsoleController {

    private Profile userChosenProfile;

    public ApplicationConsoleController(Profile userChosenProfile) {
        this.userChosenProfile = userChosenProfile;
    }

    private void addToList(String productName, double productPrize, String category) {
        this.userChosenProfile.getHistoryOfPeriods().get(userChosenProfile.getPeriodsCounter()).getPresentWeek().addProductToList(this.userChosenProfile, productName, productPrize, category);
    }

    private void removeFromList(String category, int index) {
        this.userChosenProfile.getHistoryOfPeriods().get(userChosenProfile.getPeriodsCounter()).getPresentWeek().removeProductFromList(this.userChosenProfile, category, index);
    }
}
