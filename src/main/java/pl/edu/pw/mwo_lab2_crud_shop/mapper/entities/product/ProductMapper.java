package pl.edu.pw.mwo_lab2_crud_shop.mapper.entities.product;
import org.apache.ibatis.annotations.Mapper;
import pl.edu.pw.mwo_lab2_crud_shop.entity.entities.Product;

import java.util.List;

@Mapper
public interface ProductMapper {

    Product fetchProductById(Long productId);

    Product fetchProductByCode(String productCode);

    void deleteProductById(Long clientId);

    void addProduct(Product product);

    void updateProduct(Long productId, Product product);

    List<Product> findAllProducts();
}
