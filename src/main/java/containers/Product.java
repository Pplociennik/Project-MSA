package containers;

public class Product {

    private String productName;
    private double productPrize;

    public Product(String productName, double productPrize) {
        this.productName = productName;
        this.productPrize = productPrize;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrize() {
        return productPrize;
    }
}
