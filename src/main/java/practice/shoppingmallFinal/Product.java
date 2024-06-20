package practice.shoppingmallFinal;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    // 필드
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotNull
    private String name;

    @NotNull
    private long price;

    @NotNull
    private String seller;

    @Pattern(regexp = "\\S+")
    private String brand;

    @NotNull
    private DeliveryChargeType deliveryChargeType;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductOption> options = new ArrayList<>(); // 생성하기 전까지는 틀 조차 없는 null, 생성을 해야 빈 틀이 생김.

    private LocalDate deliveryETA = LocalDate.now().plusDays(2);
    private OffsetDateTime createdAt = OffsetDateTime.now();

    // 기본 생성자
    public Product() {
    }

    // 생성자 오버로드
    public Product(String name, long price, String seller, String brand, DeliveryChargeType deliveryChargeType) {
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.brand = brand;
        this.deliveryChargeType = deliveryChargeType;
    }

    // 메서드
    public void addOption(ProductOption productOption) {
        productOption.setProduct(this); // 소속을 먼저 정하고
        options.add(productOption); // 들어온다.
    }

    // getter
    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public String getSeller() {
        return seller;
    }

    public String getBrand() {
        return brand;
    }

    public DeliveryChargeType getDeliveryChargeType() {
        return deliveryChargeType;
    }

    public List<ProductOption> getOptions() {
        return options;
    }

    public LocalDate getDeliveryETA() {
        return deliveryETA;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

}
