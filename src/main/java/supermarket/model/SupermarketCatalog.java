package supermarket.model;

public interface SupermarketCatalog {

    void addProduct(Product product, Double price);

    double getUnitPrice(Product product);
}
