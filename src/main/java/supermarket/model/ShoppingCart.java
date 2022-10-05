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

                switch (offer.getOfferType()) {
                    case THREE_FOR_TWO:
                        discount = DiscountCalculator.threeForTwoOffer(p, (int) quantity, unitPrice);
                        break;
                    case TWO_FOR_AMOUNT:
                        discount = DiscountCalculator.twoForAmountOffer(p, offer, (int) quantity, unitPrice);
                        break;
                    case FIVE_FOR_AMOUNT:
                        discount = DiscountCalculator.fiveForAmountOffer(p, offer, (int) quantity, unitPrice);
                        break;
                    case TEN_PERCENT_DISCOUNT:
                        discount = new Discount(p, offer.getArgument() + "% off", -quantity * unitPrice * offer.getArgument() / 100.0);
                        break;
                }
                if (discount != null) receipt.addDiscount(discount);
            }
        }
    }
}
