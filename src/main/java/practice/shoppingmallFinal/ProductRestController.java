package practice.shoppingmallFinal;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductRestController {

    ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/v1/products")
    public ProductDetailResponseDto create (@Valid @RequestBody ProductCreateRequestDto requestDto) {
        return productService.create(requestDto);
    }

    @GetMapping("v1/products/{productId}")
    public ProductDetailResponseDto findOne (@PathVariable Long productId) {
        return productService.findOne(productId);
    }
}
