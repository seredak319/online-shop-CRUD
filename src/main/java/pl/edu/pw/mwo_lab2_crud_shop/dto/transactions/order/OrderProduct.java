package pl.edu.pw.mwo_lab2_crud_shop.dto.transactions.order;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Value;

@Value
public class OrderProduct {
    @JsonView({OrderView.Get.class, OrderView.Post.class, OrderView.Put.class})
    String productCode;
    @JsonView({OrderView.Get.class, OrderView.Post.class, OrderView.Put.class})
    int quantity;
}
