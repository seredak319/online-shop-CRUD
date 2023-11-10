package pl.edu.pw.mwo_lab2_crud_shop.dto.transactions.order;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class OrderDto {
    @JsonView({OrderView.Get.class, OrderView.Post.class})
    int clientId;
    @JsonView({OrderView.Get.class, OrderView.Post.class})
    List<OrderProduct> products;
    @JsonView({OrderView.Get.class, OrderView.Post.class, OrderView.Put.class})
    String status;
}
