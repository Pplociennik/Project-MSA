package containers;

import users.Profile;

import java.util.ArrayList;

import app.Error;

public class Week {

    private ArrayList<Product> listOne;
    private ArrayList<Product> listTwo;
    private ArrayList<Product> listThree;
    private String weekName;

    private double listOneMAX;
    private double listTwoMAX;
    private double listThreeMAX;

    private double listOneActualValue;
    private double listTwoActualValue;
    private double listThreeActualValue;

    public Week(Profile userProfile) {
        this.listOne = new ArrayList<Product>();
        this.listTwo = new ArrayList<Product>();
        this.listThree = new ArrayList<Product>();
        this.weekName = "Tydzien " + userProfile.getWeeksCounter();

        this.listOneActualValue = 0.0;
        this.listTwoActualValue = 0.0;
        this.listThreeActualValue = 0.0;
    }

    public void addProductToList(Profile userProfile, String productName, double productPrize, String productCategory) {
        if (productCategory.equals(userProfile.getListOneName())) {
            if (listOneMAX > listOneActualValue + productPrize) {
                this.listOne.add(new Product(productName, productPrize));
                this.listOneActualValue += productPrize;
            } else {
                Error.showError("Zbyt drogie!");
            }
        } else if (productCategory.equals(userProfile.getListTwoName())) {
            if (listTwoMAX > listTwoActualValue + productPrize) {
                this.listTwo.add(new Product(productName, productPrize));
                this.listTwoActualValue += productPrize;
            } else {
                Error.showError("Zbyt drogie!");
            }
        } else if (productCategory.equals(userProfile.getListThreeName())) {
            if (listThreeMAX > listThreeActualValue + productPrize) {
                this.listThree.add(new Product(productName, productPrize));
                this.listThreeActualValue += productPrize;
            } else {
                Error.showError("Zbyt drogie!");
            }
        } else {
            Error.showError("Nie ma takiej kategorii!");
        }
    }

    public void removeProductFromList(Profile userProfile, String category, int index) {
        if (userProfile.getListOneName().equals(category)) {
            this.listOne.remove(index);
        } else if (userProfile.getListTwoName().equals(category)) {
            this.listTwo.remove(index);
        } else if (userProfile.getListThreeName().equals(category)) {
            this.listThree.remove(index);
        } else {
            Error.showError("Nie ma takiej kategorii!");
        }
    }

    public String getWeekName() {
        return weekName;
    }

    public double getListOneMAX() {
        return listOneMAX;
    }

    public void setListOneMAX(double listOneMAX) {
        this.listOneMAX = listOneMAX;
    }

    public double getListTwoMAX() {
        return listTwoMAX;
    }

    public void setListTwoMAX(double listTwoMAX) {
        this.listTwoMAX = listTwoMAX;
    }

    public double getListThreeMAX() {
        return listThreeMAX;
    }

    public void setListThreeMAX(double listThreeMAX) {
        this.listThreeMAX = listThreeMAX;
    }

    public double getListOneActualValue() {
        return listOneActualValue;
    }

    public void setListOneActualValue(double listOneActualValue) {
        this.listOneActualValue = listOneActualValue;
    }

    public double getListTwoActualValue() {
        return listTwoActualValue;
    }

    public void setListTwoActualValue(double listTwoActualValue) {
        this.listTwoActualValue = listTwoActualValue;
    }

    public double getListThreeActualValue() {
        return listThreeActualValue;
    }

    public void setListThreeActualValue(double listThreeActualValue) {
        this.listThreeActualValue = listThreeActualValue;
    }
}
