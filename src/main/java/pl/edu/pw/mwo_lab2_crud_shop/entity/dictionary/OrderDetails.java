package pl.edu.pw.mwo_lab2_crud_shop.entity.dictionary;

import lombok.Value;

@Value
public class OrderDetails {
    int statusId;
    OrderStatus orderStatus;
}
