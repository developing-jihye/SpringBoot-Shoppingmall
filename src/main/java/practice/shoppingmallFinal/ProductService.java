package practice.shoppingmallFinal;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 상품 등록
    public ProductDetailResponseDto create (ProductCreateRequestDto requestDto) {

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
        List<ProductDetailResponseDto.ProductOptionDto> responseOptionDto = savedProduct.getOptions().stream().map(
                option -> new ProductDetailResponseDto.ProductOptionDto(
                        option.getId(),
                        option.getOptionName(),
                        option.getOptionValue(),
                        option.getMaximumBuyCount()
                )
        ).toList();

        ProductDetailResponseDto responseDto = new ProductDetailResponseDto(
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

    // 상품 목록 조회 (현실에서 거의 사용하지 않는 기능)
    public List<ProductResponseDto> findAll() {
        List<Product> products = productRepository.findAll();

        List<ProductResponseDto> responseDtos = products.stream().map(product -> new ProductResponseDto(
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getDeliveryChargeType()
        )).toList();
        return responseDtos;
    }

    // 상품 상세 조회
    public ProductDetailResponseDto findOne(Long prodictId) {
        Product product = productRepository.findById(prodictId).orElseThrow(() -> new NoSuchElementException("해당 아이디를 가진 상품이 존재하지 않습니다."));

        // 옵션 리스트: 이제는 저장된 엔티티를 응답Dto로 변환
        List<ProductDetailResponseDto.ProductOptionDto> responseOptionDto = product.getOptions().stream().map(
                option -> new ProductDetailResponseDto.ProductOptionDto(
                        option.getId(),
                        option.getOptionName(),
                        option.getOptionValue(),
                        option.getMaximumBuyCount()
                )
        ).toList();

        ProductDetailResponseDto responseDto = new ProductDetailResponseDto(
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getSeller(),
                product.getBrand(),
                product.getDeliveryChargeType(),
                responseOptionDto,
                product.getDeliveryETA(),
                product.getCreatedAt()
        );
        return responseDto;
    }

    // 상품 삭제
    public void delete(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new NoSuchElementException("해당 아이디를 가진 상품이 존재하지 않습니다.");
        }
        productRepository.deleteById(productId);
    }


}
