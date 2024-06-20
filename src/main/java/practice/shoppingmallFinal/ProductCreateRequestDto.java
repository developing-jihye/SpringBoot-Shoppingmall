package practice.shoppingmallFinal;

import java.util.List;

public record ProductCreateRequestDto(
        String name,
        long price,
        String seller,
        String brand,
        DeliveryChargeType deliveryChargeType,
        List<ProductOptionDto> options
) {
    public record ProductOptionDto(
            String optionName,
            String optionValue,
            int maximumBuyCount
    ) {}
}
