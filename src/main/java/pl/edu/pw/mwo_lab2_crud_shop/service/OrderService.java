package pl.edu.pw.mwo_lab2_crud_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pw.mwo_lab2_crud_shop.dto.entities.client.ClientDto;
import pl.edu.pw.mwo_lab2_crud_shop.dto.entities.product.ProductDto;
import pl.edu.pw.mwo_lab2_crud_shop.dto.transactions.order.OrderDto;
import pl.edu.pw.mwo_lab2_crud_shop.dto.transactions.order.OrderProduct;
import pl.edu.pw.mwo_lab2_crud_shop.entity.transactions.Order;
import pl.edu.pw.mwo_lab2_crud_shop.mapper.transactions.order.OrderConverter;
import pl.edu.pw.mwo_lab2_crud_shop.mapper.transactions.order.OrderMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static pl.edu.pw.mwo_lab2_crud_shop.entity.dictionary.OrderStatus.CANCELED;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderConverter orderConverter;
    private final Random random = new Random();

    public List<OrderDto> findAll() {
        return orderMapper.findAllOrders().stream()
                .map(orderConverter::toDto)
                .toList();
    }

    public OrderDto findById(Long id) {
        return orderConverter.toDto(orderMapper.fetchOrder(id));
    }

    public int create(OrderDto orderDto) {
        // only for test purpose, should be UUID
        final int orderId = random.nextInt();
        final int clientId = orderDto.getClientId();
        final List<OrderProduct> allProducts = orderDto.getProducts();

        if (!isClientPresent(clientId)) {
            throw new IllegalArgumentException("Customer not found!");
        }

        if (allProducts == null || allProducts.isEmpty() || !areProductsAvailable(allProducts)) {
            throw new IllegalArgumentException("Not all products are available");
        }

        Order order = orderConverter.toOrder(orderDto);
        orderMapper.addOrder(order);
        orderMapper.addOrderedProducts(orderId, allProducts);
        return orderId;
    }

    public boolean isClientPresent(int clientId) {
        ClientDto client = clientService.findById(Long.valueOf(clientId));
        return client != null;
    }

    public boolean areProductsAvailable(List<OrderProduct> allWantedProducts) {
        return allWantedProducts.stream()
                .allMatch(wantedProduct -> {
                    ProductDto product = productService.findByCode(wantedProduct.getProductCode());

                    return product != null && product.getStockQuantity() >= wantedProduct.getQuantity();
                });
    }

    public OrderDto update(Long id, OrderDto orderDto) {
        if (!isOrderExist(id)) {
            throw new IllegalArgumentException("Given order does not exist");
        }

        final String status = orderDto.getStatus();
        orderMapper.updateOrder(id, status);
        return orderDto;
    }

    public List<ProductDto> cancelOrder(Long orderId) {
        Order order = orderMapper.fetchOrder(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order with id " + orderId + " not found");
        }

        List<OrderProduct> orderedProducts = orderMapper.findByOrderId(orderId);

        List<ProductDto> restoredProducts = new ArrayList<>();
        for (OrderProduct orderedProduct : orderedProducts) {
            ProductDto product = productService.findByCode(orderedProduct.getProductCode());
            if (product != null) {
                int restoredQuantity = product.getStockQuantity() + orderedProduct.getQuantity();
                product.setStockQuantity(restoredQuantity);
                productService.updateAfterCancel(product.getProductCode(), product);
                restoredProducts.add(product);
            } else {
                throw new IllegalArgumentException("Product with code " + orderedProduct.getProductCode() + " not found");
            }
        }

        orderMapper.updateOrder(orderId, CANCELED.getReferenceName());
        return restoredProducts;
    }

    public boolean isOrderExist(Long id) {
        return orderMapper.fetchOrder(id) != null;
    }

    public Long deleteById(Long id) {
        orderMapper.deleteOrder(id);
        return id;
    }
}
