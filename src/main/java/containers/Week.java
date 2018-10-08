package containers;

import users.Profile;

import java.util.ArrayList;

import app.ConsoleService;

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

    private double actualSpendings;

    public Week(Profile userProfile) {
        this.listOne = new ArrayList<Product>();
        this.listTwo = new ArrayList<Product>();
        this.listThree = new ArrayList<Product>();
        this.weekName = "Tydzien " + userProfile.getWeeksCounter();

        this.listOneActualValue = 0.0;
        this.listTwoActualValue = 0.0;
        this.listThreeActualValue = 0.0;

        this.actualSpendings = 0.0;
    }

    public void addProductToList(Profile userProfile, String productName, double productPrize, String productCategory) throws InterruptedException {
        if (productCategory.toUpperCase().equals(userProfile.getListOneName().toUpperCase())) {
            if (listOneMAX > listOneActualValue + productPrize) {
                this.listOne.add(new Product(productName, productPrize, false));
                this.listOneActualValue += productPrize;
            } else {
                ConsoleService.showError("Zbyt drogie!");
            }
        } else if (productCategory.toUpperCase().equals(userProfile.getListTwoName().toUpperCase())) {
            if (listTwoMAX > listTwoActualValue + productPrize) {
                this.listTwo.add(new Product(productName, productPrize, false));
                this.listTwoActualValue += productPrize;
            } else {
                ConsoleService.showError("Zbyt drogie!");
            }
        } else if (productCategory.toUpperCase().equals(userProfile.getListThreeName().toUpperCase())) {
            if (listThreeMAX > listThreeActualValue + productPrize) {
                this.listThree.add(new Product(productName, productPrize, false));
                this.listThreeActualValue += productPrize;
            } else {
                ConsoleService.showError("Zbyt drogie!");
            }
        } else {
            ConsoleService.showError("Nie ma takiej kategorii!");
        }
    }

    public void addWalletElement(Profile userProfile, String productName, double productPrize, String productCategory) throws InterruptedException {
        if (productPrize < userProfile.getWallet()) {
            if (productCategory.toUpperCase().equals(userProfile.getListOneName().toUpperCase())) {
                this.listOne.add(new Product(productName, productPrize, true));
                // this.listOneActualValue += productPrize;
            } else if (productCategory.toUpperCase().equals(userProfile.getListTwoName().toUpperCase())) {
                this.listTwo.add(new Product(productName, productPrize, true));
                //  this.listTwoActualValue += productPrize;
            } else if (productCategory.toUpperCase().equals(userProfile.getListThreeName().toUpperCase())) {
                this.listThree.add(new Product(productName, productPrize, true));
                //  this.listThreeActualValue += productPrize;
            }

        } else {
            ConsoleService.showError("Niewystarczające fundusze w portfelu!");
        }
    }

    public void removeProductFromList(Profile userProfile, String category, int index) throws InterruptedException {
        if (index < 0) {
            ConsoleService.showError("Zły numer!");
        }
        if (userProfile.getListOneName().toUpperCase().equals(category.toUpperCase())) {
            if (index <= listOne.size()) {
                if (listOne.get(index).isFromWallet() == true) {
                    userProfile.setWallet(userProfile.getWallet() + listOne.get(index).getProductPrize());
                } else {
                    listOneActualValue = listOneActualValue - listOne.get(index).getProductPrize();
                }
                //userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().setActualSpendings(userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().getActualSpendings() - listOne.get(index).getProductPrize());
                this.listOne.remove(index);
            } else {
                ConsoleService.showError("Zły numer!");
            }
        } else if (userProfile.getListTwoName().toUpperCase().equals(category.toUpperCase())) {
            if (index <= listTwo.size()) {
                if (listTwo.get(index).isFromWallet() == true) {
                    userProfile.setWallet(userProfile.getWallet() + listTwo.get(index).getProductPrize());
                } else {
                    listTwoActualValue = listTwoActualValue - listTwo.get(index).getProductPrize();
                }
               // userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().setActualSpendings(userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().getActualSpendings() - listTwo.get(index).getProductPrize());
                this.listTwo.remove(index);
            } else {
                ConsoleService.showError("Zły numer!");
            }
        } else if (userProfile.getListThreeName().toUpperCase().equals(category.toUpperCase())) {
            if (index <= listThree.size()) {
                if (listThree.get(index).isFromWallet() == true) {
                    userProfile.setWallet(userProfile.getWallet() + listThree.get(index).getProductPrize());
                } else {
                    listThreeActualValue = listThreeActualValue - listThree.get(index).getProductPrize();
                }
               // userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().setActualSpendings(userProfile.getHistoryOfPeriods().get(userProfile.getPeriodsCounter()).getPresentWeek().getActualSpendings() - listThree.get(index).getProductPrize());
                this.listThree.remove(index);
            } else {
                ConsoleService.showError("Zły numer!");
            }
        } else {
            ConsoleService.showError("Nie ma takiej kategorii!");
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

    public ArrayList<Product> getListOne() {
        return listOne;
    }

    public ArrayList<Product> getListTwo() {
        return listTwo;
    }

    public ArrayList<Product> getListThree() {
        return listThree;
    }

    public double getActualSpendings() {
        return actualSpendings;
    }

    public void setActualSpendings(double actualSpendings) {
        this.actualSpendings = actualSpendings;
    }

    public void addToListOne(Product product) {
        this.listOne.add(product);
    }

    public void addToListTwo(Product product) {
        this.listTwo.add(product);
    }

    public void addToListThree(Product product) {
        this.listThree.add(product);
    }


}
