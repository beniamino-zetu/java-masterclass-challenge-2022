package supermarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode
public class ReceiptItem {

    private final Product product;
    private final double quantity;
    private final double price;
    private final double totalPrice;

}
