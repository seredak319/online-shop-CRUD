package pl.edu.pw.mwo_lab2_crud_shop.entity.transactions;

import lombok.Value;

@Value
public class Order {
    int orderId;
    int clientId;
    int orderStatus;
}
