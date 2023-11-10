package pl.edu.pw.mwo_lab2_crud_shop.mapper.transactions.order;

import org.apache.ibatis.annotations.Mapper;
import pl.edu.pw.mwo_lab2_crud_shop.dto.transactions.order.OrderProduct;
import pl.edu.pw.mwo_lab2_crud_shop.entity.transactions.Order;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<Order> findAllOrders();

    Order fetchOrder(Long id);

    void addOrder(Order order);

    void addOrderedProducts(int orderId, List<OrderProduct> allProducts);

    void updateOrder(Long orderId, String status);

    void deleteOrder(Long id);

    List<OrderProduct> findByOrderId(Long orderId);
}
