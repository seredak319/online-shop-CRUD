package pl.edu.pw.mwo_lab2_crud_shop.mapper.entities.product;

import org.springframework.stereotype.Component;
import pl.edu.pw.mwo_lab2_crud_shop.dto.entities.product.ProductDto;
import pl.edu.pw.mwo_lab2_crud_shop.entity.entities.Product;

@Component
public class ProductConverter {

    public ProductDto toDto(Product product) {
        if (product == null) return ProductDto.builder().build();
        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .productCode(product.getProductCode())
                .build();
    }

    public Product toProduct(ProductDto productDto) {
        if (productDto == null) return Product.builder().build();
        return Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .stockQuantity(productDto.getStockQuantity())
                .productCode(productDto.getProductCode())
                .build();
    }
}
