package supermarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Discount {

    private final Product product;
    private final String description;
    private final double discountAmount;

}
