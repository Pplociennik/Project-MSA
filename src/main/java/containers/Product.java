package containers;

public class Product {

    private String productName;
    private double productPrize;
    private boolean fromWallet;

    public Product(String productName, double productPrize, boolean isFromWallet) {
        this.productName = productName;
        this.productPrize = productPrize;
        this.fromWallet = isFromWallet;
    }

    public Product() {
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrize() {
        return productPrize;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrize(double productPrize) {
        this.productPrize = productPrize;
    }

    public boolean isFromWallet() {
        return fromWallet;
    }

    public void setFromWallet(boolean fromWallet) {
        this.fromWallet = fromWallet;
    }
}
