package pl.edu.pw.mwo_lab2_crud_shop.dto.entities.product;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;


@Builder
@ToString
@Getter
@Setter
public class ProductDto {
    @JsonView({ProductView.Get.class, ProductView.Post.class, ProductView.Put.class})
    String name;
    @JsonView({ProductView.Get.class, ProductView.Post.class, ProductView.Put.class})
    double price;
    @JsonView({ProductView.Get.class, ProductView.Post.class, ProductView.Put.class})
    int stockQuantity;
    @JsonView({ProductView.Get.class, ProductView.Post.class, ProductView.Put.class})
    String productCode;

    public void setStockQuantity(int restoredQuantity) {
        this.stockQuantity = restoredQuantity;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ProductDto other = (ProductDto) obj;

        return Objects.equals(name, other.name) &&
                Double.compare(other.price, price) == 0 &&
                stockQuantity == other.stockQuantity &&
                Objects.equals(productCode, other.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, stockQuantity, productCode);
    }
}
