package pl.edu.pw.mwo_lab2_crud_shop.entity.entities;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@ToString
public class Product {
    int productId;
    String name;
    double price;
    int stockQuantity;
    String productCode;
}
