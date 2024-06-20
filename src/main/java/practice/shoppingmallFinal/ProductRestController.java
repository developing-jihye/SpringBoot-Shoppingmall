package practice.shoppingmallFinal;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {

    ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/v1/products")
    public ProductCreateResponseDto create (@Valid @RequestBody ProductCreateRequestDto requestDto) {
        return productService.create(requestDto);
    }
}
