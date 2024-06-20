package practice.shoppingmallFinal;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public record ProductCreateResponseDto(
        Long productId,
        String name,
        long price,
        String seller,
        String brand,
        DeliveryChargeType deliveryChargeType,
        List<ProductCreateResponseDto.ProductOptionDto> options,
        LocalDate deliveryETA,
        OffsetDateTime createdAt
) {
    public record ProductOptionDto(
            Long id,
            String optionName,
            String optionValue,
            int maximumBuyCount
    ) {}
}
