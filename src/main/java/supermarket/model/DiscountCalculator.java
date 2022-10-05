package supermarket.model;

public class DiscountCalculator {

    public static Discount threeForTwoOffer(Product p, int quantity, double unitPrice) {
        int x = 3;
        int numberOfXs = quantity / x;
        Discount discount = null;
        if (quantity > 2) {
            double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantity % 3 * unitPrice);
            discount = new Discount(p, "3 for 2", -discountAmount);
        }
        return discount;
    }

    public static Discount twoForAmountOffer(Product p, Offer offer, int quantity, double unitPrice) {
        int x = 2;
        Discount discount = null;
        if (quantity >= 2) {
            int intDivision = quantity / x;
            double pricePerUnit = offer.getArgument() * intDivision;
            double theTotal = (quantity % 2) * unitPrice;
            double total = pricePerUnit + theTotal;
            double discountN = unitPrice * quantity - total;
            discount = new Discount(p, "2 for " + offer.getArgument(), -discountN);
        }
        return discount;
    }

    public static Discount fiveForAmountOffer(Product p, Offer offer, int quantity, double unitPrice) {
        int x = 5;
        Discount discount = null;
        if (quantity >= 5) {
            int numberOfXs = quantity / x;
            double discountTotal = unitPrice * quantity - (offer.getArgument() * numberOfXs + quantity % 5 * unitPrice);
            discount = new Discount(p, x + " for " + offer.getArgument(), -discountTotal);
        }

        return discount;
    }
}
