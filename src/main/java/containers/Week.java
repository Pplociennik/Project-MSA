package containers;

import users.Profile;

import java.util.ArrayList;

public class Week {

    private ArrayList<Product>products;
    private String weekName;

    public Week(Profile userProfile) {
        this.products = new ArrayList<Product>();
        this.weekName = "Tydzien " + userProfile.getWeeksCounter();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getWeekName() {
        return weekName;
    }
}
