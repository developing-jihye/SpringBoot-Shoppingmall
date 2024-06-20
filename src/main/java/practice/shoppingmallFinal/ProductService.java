package practice.shoppingmallFinal;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 상품 등록
    public ProductCreateResponseDto create (ProductCreateRequestDto requestDto) {

        // [계속 까먹음] 옵션 리스트: 요청Dto를 엔티티로 변환 (DB 저장하기 위해)
        List<ProductOption> productOptions = requestDto.options().stream().map(
                option -> new ProductOption(option.optionName(), option.optionValue(), option.maximumBuyCount())
        ).toList();

        Product product = new Product(
                requestDto.name(),
                requestDto.price(),
                requestDto.seller(),
                requestDto.brand(),
                requestDto.deliveryChargeType()
        );

        // 각 옵션 소속 정하기
        productOptions.forEach(option -> product.addOption(option));

        // DB 저장
        Product savedProduct = productRepository.save(product);

        // 옵션 리스트: 이제는 저장된 엔티티를 응답Dto로 변환
        List<ProductCreateResponseDto.ProductOptionDto> responseOptionDto = savedProduct.getOptions().stream().map(
                option -> new ProductCreateResponseDto.ProductOptionDto(
                        option.getId(),
                        option.getOptionName(),
                        option.getOptionValue(),
                        option.getMaximumBuyCount()
                )
        ).toList();

        ProductCreateResponseDto responseDto = new ProductCreateResponseDto(
                savedProduct.getProductId(),
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getSeller(),
                savedProduct.getBrand(),
                savedProduct.getDeliveryChargeType(),
                responseOptionDto,
                savedProduct.getDeliveryETA(),
                savedProduct.getCreatedAt()
        );
        return responseDto;
    }

    // 상품 목록 조회

    // 상품 상세 조회

    // 상품 삭제


}
