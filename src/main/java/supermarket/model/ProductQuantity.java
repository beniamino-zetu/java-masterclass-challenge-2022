package supermarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductQuantity {

    private final Product product;
    private final double quantity;

}
