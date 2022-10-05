package supermarket.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    @Getter
    private final List<ProductQuantity> items = new ArrayList<>();
    @Getter
    private final Map<Product, Double> productQuantities = new HashMap<>();

    void addItem(Product product) {
        addItemQuantity(product, 1.0);
    }

    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        for (Product p : getProductQuantities().keySet()) {
            double quantity = productQuantities.get(p);
            if (offers.containsKey(p)) {
                Offer offer = offers.get(p);
                double unitPrice = catalog.getUnitPrice(p);
                Discount discount = null;

                switch (offer.offerType) {
                    case THREE_FOR_TWO:
                        discount =  threeForTwoOffer(p, (int) quantity, unitPrice);
                        break;
                    case TWO_FOR_AMOUNT:
                        discount =  twoForAmountOffer(p, offer, (int) quantity, unitPrice);
                        break;
                    case FIVE_FOR_AMOUNT:
                        discount =  fiveForAmountOffer(p, offer, (int) quantity, unitPrice);
                        break;
                    case TEN_PERCENT_DISCOUNT:
                        discount = new Discount(p, offer.argument + "% off", -quantity * unitPrice * offer.argument / 100.0);
                        break;
                }
                if (discount != null) receipt.addDiscount(discount);
            }
        }
    }

    private Discount threeForTwoOffer(Product p, int quantity, double unitPrice) {
        int x = 3;
        int numberOfXs = quantity / x;
        Discount discount = null;
        if (quantity > 2) {
            double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantity % 3 * unitPrice);
            discount = new Discount(p, "3 for 2", -discountAmount);
        }
        return discount;
    }

    private Discount twoForAmountOffer(Product p, Offer offer, int quantity, double unitPrice) {
        int x = 2;
        Discount discount = null;
        if (quantity >= 2) {
            int intDivision = quantity / x;
            double pricePerUnit = offer.argument * intDivision;
            double theTotal = (quantity % 2) * unitPrice;
            double total = pricePerUnit + theTotal;
            double discountN = unitPrice * quantity - total;
            discount = new Discount(p, "2 for " + offer.argument, -discountN);
        }
        return discount;
    }

    private Discount fiveForAmountOffer(Product p, Offer offer, int quantity, double unitPrice) {
        int x = 5;
        Discount discount = null;
        if (quantity >= 5) {
            int numberOfXs = quantity / x;
            double discountTotal = unitPrice * quantity - (offer.argument * numberOfXs + quantity % 5 * unitPrice);
            discount = new Discount(p, x + " for " + offer.argument, -discountTotal);
        }

        return discount;
    }
}
