package practice.shoppingmallFinal;

public record ProductResponseDto(
        Long productId,
        String name,
        long price,
        DeliveryChargeType deliveryChargeType
) {
}
