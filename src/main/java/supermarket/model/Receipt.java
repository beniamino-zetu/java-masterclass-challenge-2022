package supermarket.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class Receipt {

    @Getter
    private final List<ReceiptItem> items = new ArrayList<>();
    @Getter
    private final List<Discount> discounts = new ArrayList<>();

    public Double getTotalPrice() {
        Double total = 0.0;
        for (ReceiptItem item : items) {
            total += item.getTotalPrice();
        }
        for (Discount discount : discounts) {
            total += discount.getDiscountAmount();
        }
        return total;
    }

    public void addProduct(Product p, Double quantity, Double price, Double totalPrice) {
        items.add(new ReceiptItem(p, quantity, price, totalPrice));
    }

    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

}
