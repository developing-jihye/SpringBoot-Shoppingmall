package practice.shoppingmallFinal;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/v1/products")
    public ProductDetailResponseDto create (@Valid @RequestBody ProductCreateRequestDto requestDto) {
        return productService.create(requestDto);
    }

    @GetMapping("v1/products")
    public List<ProductResponseDto> findAll () {
        return productService.findAll();
    }

    @GetMapping("v1/products/{productId}")
    public ProductDetailResponseDto findOne (@PathVariable Long productId) {
        return productService.findOne(productId);
    }
}
