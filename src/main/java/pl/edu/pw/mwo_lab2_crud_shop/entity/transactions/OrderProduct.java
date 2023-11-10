package pl.edu.pw.mwo_lab2_crud_shop.entity.transactions;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderProduct {
    int orderProductId;
    int orderId;
    int productCode;
    int quantity;
}
