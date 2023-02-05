public class Product {
    String pName, pBrand;
    int pQuantity, prodId;
    float pPrice;

    public void addNewProduct(String name, String brand, int quantity, float price, int prodId) {
        this.pName = name;
        this.pBrand = brand;
        this.pQuantity = quantity;
        this.pPrice = price;
        this.prodId = prodId;
        System.out.println("New product added !!");

    }
    // public static void main(String[] args) {
    // }
}
