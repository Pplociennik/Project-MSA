package containers;

import users.Profile;

import java.util.ArrayList;

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
