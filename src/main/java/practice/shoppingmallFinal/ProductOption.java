package practice.shoppingmallFinal;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

@Entity
public class ProductOption {
    // 필드
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String optionName;

    @NotNull
    private String optionValue;

    @Max(999)
    private int maximumBuyCount;

    @ManyToOne
    Product product;

    // 기본 생성자
    protected ProductOption() {
    }

    // 생성자 오버로드
    public ProductOption(String optionName, String optionValue, int maximumBuyCount) {
        this.optionName = optionName;
        this.optionValue = optionValue;
        this.maximumBuyCount = maximumBuyCount;
    }

    // setter 필요함: 소속 정할 때 필요
    public void setProduct(Product product) {
        this.product = product;
    }

    // getter
    public Long getId() {
        return id;
    }

    public String getOptionName() {
        return optionName;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public int getMaximumBuyCount() {
        return maximumBuyCount;
    }

    public Product getProduct() {
        return product;
    }
}
