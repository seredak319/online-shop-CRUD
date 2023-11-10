package pl.edu.pw.mwo_lab2_crud_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pw.mwo_lab2_crud_shop.dto.entities.product.ProductDto;
import pl.edu.pw.mwo_lab2_crud_shop.entity.entities.Product;
import pl.edu.pw.mwo_lab2_crud_shop.mapper.entities.product.ProductConverter;
import pl.edu.pw.mwo_lab2_crud_shop.mapper.entities.product.ProductMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductConverter productConverter;

    public List<ProductDto> findAll() {
        return productMapper.findAllProducts().stream()
                .map(productConverter::toDto)
                .toList();
    }

    public ProductDto findById(Long id) {
        return productConverter.toDto(productMapper.fetchProductById(id));
    }

    public ProductDto findByCode(String code) {
        return productConverter.toDto(productMapper.fetchProductByCode(code));
    }

    public ProductDto create(ProductDto productDto) {
        Product product = productConverter.toProduct(productDto);
        productMapper.addProduct(product);
        return productConverter.toDto(product);
    }

    public ProductDto update(Long id, ProductDto productDto) {
        Product product = productConverter.toProduct(productDto);
        productMapper.updateProduct(id, product);
        return productConverter.toDto(product);
    }

    public Long deleteById(Long id) {
        productMapper.deleteProductById(id);
        return id;
    }

    public void updateAfterCancel(String productCode, ProductDto product) {
    }
}
