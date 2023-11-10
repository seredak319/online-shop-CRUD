package pl.edu.pw.mwo_lab2_crud_shop.mapper.transactions.order;

import org.springframework.stereotype.Component;
import pl.edu.pw.mwo_lab2_crud_shop.dto.transactions.order.OrderDto;
import pl.edu.pw.mwo_lab2_crud_shop.entity.transactions.Order;

@Component
public class OrderConverter {
    public Order toOrder(OrderDto orderDto) {
        return Order.builder()
                .clientId(orderDto.getClientId())
                .orderStatus(orderDto.getStatus())
                .build();
    }

    public OrderDto toDto(Order order) {
        return OrderDto.builder()
                .clientId(order.getClientId())
                .status(order.getOrderStatus())
                .build();
    }
}
