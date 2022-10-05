package supermarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Offer {

    private final SpecialOfferType offerType;
    private final Product product;
    private final Double argument;
}
