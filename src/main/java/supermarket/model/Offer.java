package supermarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Offer {

    SpecialOfferType offerType;
    private final Product product;
    double argument;
}
