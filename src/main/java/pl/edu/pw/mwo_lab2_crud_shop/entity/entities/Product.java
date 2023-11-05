package pl.edu.pw.mwo_lab2_crud_shop.entity.entities;

import lombok.Value;

@Value
public class Product {
    int productId;
    String name;
    double price;
    int stockQuantity;
    String productCode;
}
