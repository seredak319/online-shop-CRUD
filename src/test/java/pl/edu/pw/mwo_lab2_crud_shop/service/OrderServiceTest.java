//package pl.edu.pw.mwo_lab2_crud_shop.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import pl.edu.pw.mwo_lab2_crud_shop.dto.entities.client.ClientDto;
//import pl.edu.pw.mwo_lab2_crud_shop.dto.entities.product.ProductDto;
//import pl.edu.pw.mwo_lab2_crud_shop.dto.transactions.order.OrderDto;
//import pl.edu.pw.mwo_lab2_crud_shop.dto.transactions.order.OrderProduct;
//import pl.edu.pw.mwo_lab2_crud_shop.entity.transactions.Order;
//import pl.edu.pw.mwo_lab2_crud_shop.mapper.transactions.order.OrderConverter;
//import pl.edu.pw.mwo_lab2_crud_shop.mapper.transactions.order.OrderMapper;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Stream;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//class OrderServiceTest {
//
//    @Mock
//    private OrderMapper orderMapper;
//
//    @Mock
//    private ClientService clientService;
//
//    @Mock
//    private ProductService productService;
//
//    @Mock
//    private OrderConverter orderConverter;
//
//    @InjectMocks
//    private OrderService orderService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testFindAll() {
//        // Arrange
//        when(orderMapper.findAllOrders()).thenReturn(Collections.singletonList(Order.builder().build()));
//        when(orderConverter.toDto(any())).thenReturn(OrderDto.builder().build());
//
//        // Act
//        List<OrderDto> result = orderService.findAll();
//
//        // Assert
//        assertThat(result)
//                .isNotEmpty()
//                .hasSize(1)
//                .allSatisfy(orderDto -> assertThat(orderDto).isInstanceOf(OrderDto.class));
//    }
//
//    @Test
//    void testFindById() {
//        // Arrange
//        when(orderMapper.findAllOrders()).thenReturn(Collections.singletonList(Order.builder().build()));
//        when(orderConverter.toDto(any())).thenReturn(OrderDto.builder().build());
//
//        // Act
//        OrderDto result = orderService.findById(1L);
//
//        // Assert
//        assertThat(result)
//                .isNotNull()
//                .isInstanceOf(OrderDto.class);
//    }
//
//    @Test
//    void testCreateOrderWhenClientRegisteredAndProductsAvailable() {
//        // Arrange
//        final OrderDto givenOrderDto = OrderDto.builder()
//                .clientId(1)
//                .products(Collections.singletonList(new OrderProduct(
//                        "TEST",
//                        2
//                )))
//                .build();
//
//        when(clientService.findById(anyLong())).thenReturn(ClientDto.builder().build());
//        when(productService.findByCode(any())).thenReturn(ProductDto.builder()
//                .productCode("TEST")
//                .stockQuantity(10)
//                .build());
//
//        // Act
//        orderService.create(givenOrderDto);
//
//        // Assert
//        verify(orderMapper, times(1)).addOrder(any());
//        verify(orderMapper, times(1)).addOrderedProducts(anyInt(), anyList());
//        verify(clientService, times(1)).findById(any());
//        verify(productService, times(1)).findByCode("TEST");
//    }
//
//    @Test
//    void testCreateOrderWhenClientNotRegistered() {
//        // Arrange
//        final OrderDto givenOrderDto = OrderDto.builder()
//                .clientId(1)
//                .products(Collections.singletonList(new OrderProduct(
//                        "TEST",
//                        2
//                )))
//                .build();
//
//        when(clientService.findById(anyLong())).thenReturn(null);
//
//        // Act
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.create(givenOrderDto));
//
//        // Assert
//        assertThat(exception.getMessage()).isEqualTo("Customer not found!");
//        verify(orderMapper, never()).addOrder(any());
//        verify(orderMapper, never()).addOrderedProducts(anyInt(), anyList());
//        verify(clientService, times(1)).findById(any());
//        verify(productService, never()).findByCode(any());
//    }
//
//    @Test
//    void testCreateOrderWhenProductNotAvailable() {
//        // Arrange
//        final OrderDto givenOrderDto = OrderDto.builder()
//                .clientId(1)
//                .products(Collections.singletonList(new OrderProduct(
//                        "TEST",
//                        2
//                )))
//                .build();
//
//        when(clientService.findById(anyLong())).thenReturn(ClientDto.builder().build());
//        when(productService.findByCode(any())).thenReturn(null);
//
//        // Act
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.create(givenOrderDto));
//
//        // Assert
//        assertThat(exception.getMessage()).isEqualTo("Not all products are available");
//        verify(orderMapper, never()).addOrder(any());
//        verify(orderMapper, never()).addOrderedProducts(anyInt(), anyList());
//        verify(clientService, times(1)).findById(any());
//        verify(productService, times(1)).findByCode("TEST");
//    }
//
//    @Test
//    void testCreateOrderWhenNotEnoughProductQuantity() {
//        // Arrange
//        final OrderDto givenOrderDto = OrderDto.builder()
//                .clientId(1)
//                .products(Collections.singletonList(new OrderProduct(
//                        "TEST",
//                        12
//                )))
//                .build();
//
//        when(clientService.findById(anyLong())).thenReturn(ClientDto.builder().build());
//        when(productService.findByCode(any())).thenReturn(ProductDto.builder()
//                .productCode("TEST")
//                .stockQuantity(10)
//                .build());
//
//        // Act
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.create(givenOrderDto));
//
//        // Assert
//        assertThat(exception.getMessage()).isEqualTo("Not all products are available");
//        verify(orderMapper, never()).addOrder(any());
//        verify(orderMapper, never()).addOrderedProducts(anyInt(), anyList());
//        verify(clientService, times(1)).findById(any());
//        verify(productService, times(1)).findByCode("TEST");
//    }
//
//    @Test
//    void testCreateOrderWithEmptyProductList() {
//        // Arrange
//        final OrderDto givenOrderDto = OrderDto.builder()
//                .clientId(1)
//                .products(Collections.emptyList())
//                .build();
//
//        when(clientService.findById(anyLong())).thenReturn(ClientDto.builder().build());
//
//        // Act
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.create(givenOrderDto));
//
//        // Assert
//        assertThat(exception.getMessage()).isEqualTo("Not all products are available");
//        verify(orderMapper, never()).addOrder(any());
//        verify(orderMapper, never()).addOrderedProducts(anyInt(), anyList());
//        verify(clientService, times(1)).findById(any());
//    }
//
//    @ParameterizedTest
//    @MethodSource("provideProductParameters")
//    void testCreateOrderWithDifferentProducts(ProductDto product) {
//        // Arrange
//        final OrderDto givenOrderDto = OrderDto.builder()
//                .clientId(1)
//                .products(Collections.singletonList(new OrderProduct(
//                        product.getProductCode(),
//                        2
//                )))
//                .build();
//
//        when(clientService.findById(anyLong())).thenReturn(ClientDto.builder().build());
//        when(productService.findByCode(any())).thenReturn(product);
//
//        // Act
//        orderService.create(givenOrderDto);
//
//        // Assert
//        verify(orderMapper, times(1)).addOrder(any());
//        verify(orderMapper, times(1)).addOrderedProducts(anyInt(), anyList());
//        verify(clientService, times(1)).findById(any());
//        verify(productService, times(1)).findByCode(product.getProductCode());
//    }
//
//    @ParameterizedTest
//    @ValueSource(ints = {1, 2, 3})
//    void testCreateOrderWithDifferentClientIds(int clientId) {
//        // Arrange
//        final OrderDto givenOrderDto = OrderDto.builder()
//                .clientId(clientId)
//                .products(Collections.singletonList(new OrderProduct(
//                        "TEST",
//                        2
//                )))
//                .build();
//
//        when(clientService.findById(anyLong())).thenReturn(ClientDto.builder().build());
//        when(productService.findByCode(any())).thenReturn(ProductDto.builder()
//                .productCode("TEST")
//                .stockQuantity(10)
//                .build());
//
//        // Act
//        orderService.create(givenOrderDto);
//
//        // Assert
//        verify(orderMapper, times(1)).addOrder(any());
//        verify(orderMapper, times(1)).addOrderedProducts(anyInt(), anyList());
//        verify(clientService, times(1)).findById(any());
//        verify(productService, times(1)).findByCode("TEST");
//    }
//
//    private static Stream<ProductDto> provideProductParameters() {
//        return Stream.of(
//                ProductDto.builder().productCode("TEST1").stockQuantity(10).build(),
//                ProductDto.builder().productCode("TEST2").stockQuantity(5).build(),
//                ProductDto.builder().productCode("TEST3").stockQuantity(8).build()
//        );
//    }
//
//    @Test
//    void testUpdateOrderStatus() {
//        // Arrange
//        final long orderId = 1L;
//        final OrderDto givenOrderDto = OrderDto.builder().status("NEW_STATUS").build();
//        when(orderMapper.fetchOrder(orderId)).thenReturn(Order.builder().build());
//
//        // Act
//        OrderDto result = orderService.update(orderId, givenOrderDto);
//
//        // Assert
//        assertThat(result).isEqualTo(givenOrderDto);
//        verify(orderMapper, times(1)).updateOrder(orderId, givenOrderDto.getStatus());
//    }
//
//    @Test
//    void testCancelOrderWithExistingOrderAndProducts() {
//        // Arrange
//        final long orderId = 1L;
//
//        Order order = Order.builder()
//                .orderId((int) orderId)
//                .build();
//        when(orderMapper.fetchOrder(orderId)).thenReturn(order);
//
//        List<OrderProduct> orderedProducts = new ArrayList<>();
//        orderedProducts.add(new OrderProduct("TEST_PRODUCT", 2));
//        when(orderMapper.findByOrderId(orderId)).thenReturn(orderedProducts);
//
//        ProductDto product = ProductDto.builder()
//                .productCode("TEST_PRODUCT")
//                .stockQuantity(5)
//                .build();
//        when(productService.findByCode("TEST_PRODUCT")).thenReturn(product);
//
//        // Act
//        List<ProductDto> result = orderService.cancelOrder(orderId);
//
//        // Assert
//        assertThat(result).hasSize(1);
//        assertThat(result.get(0).getStockQuantity()).isEqualTo(7); // (initial stock) + (quantity from order)
//
//        verify(productService, times(1)).updateAfterCancel("TEST_PRODUCT", product);
//        verify(orderMapper, times(1)).updateOrder(orderId, "CANCELED");
//    }
//
//    @Test
//    void testCancelOrderWithNonExistingOrder() {
//        // Arrange
//        final long orderId = 1L;
//        when(orderMapper.fetchOrder(orderId)).thenReturn(null);
//
//        // Act & Assert
//        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
//                IllegalArgumentException.class,
//                () -> orderService.cancelOrder(orderId)
//        );
//
//        assertThat(exception.getMessage()).isEqualTo("Order with id 1 not found");
//
//        verify(orderMapper, never()).findByOrderId(anyLong());
//        verify(productService, never()).findByCode(anyString());
//        verify(productService, never()).updateAfterCancel(anyString(), any());
//        verify(orderMapper, never()).updateOrder(anyLong(), anyString());
//    }
//
//    @Test
//    void testCancelOrderWithNonExistingProduct() {
//        // Arrange
//        final long orderId = 1L;
//
//        Order order = Order.builder()
//                .orderId((int) orderId)
//                .build();
//        when(orderMapper.fetchOrder(orderId)).thenReturn(order);
//
//        List<OrderProduct> orderedProducts = new ArrayList<>();
//        orderedProducts.add(new OrderProduct("TEST_PRODUCT", 2));
//        when(orderMapper.findByOrderId(orderId)).thenReturn(orderedProducts);
//
//        when(productService.findByCode("TEST_PRODUCT")).thenReturn(null);
//
//        // Act & Assert
//        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
//                IllegalArgumentException.class,
//                () -> orderService.cancelOrder(orderId)
//        );
//
//        assertThat(exception.getMessage()).isEqualTo("Product with code TEST_PRODUCT not found");
//
//        verify(productService, never()).updateAfterCancel(anyString(), any());
//        verify(orderMapper, never()).updateOrder(anyLong(), anyString());
//    }
//
//    @Test
//    void testCancelOrderReturnsRestoredProducts() {
//        // Arrange
//        final long orderId = 1L;
//
//        Order order = Order.builder()
//                .orderId((int) orderId)
//                .build();
//        when(orderMapper.fetchOrder(orderId)).thenReturn(order);
//
//        List<OrderProduct> orderedProducts = new ArrayList<>();
//        orderedProducts.add(new OrderProduct("TEST_PRODUCT", 2));
//        when(orderMapper.findByOrderId(orderId)).thenReturn(orderedProducts);
//
//        ProductDto product = ProductDto.builder()
//                .productCode("TEST_PRODUCT")
//                .stockQuantity(5)
//                .build();
//        when(productService.findByCode("TEST_PRODUCT")).thenReturn(product);
//
//        // Act
//        List<ProductDto> result = orderService.cancelOrder(orderId);
//
//        // Assert
//        assertThat(result).hasSize(1);
//        assertThat(result.get(0)).isEqualTo(product);
//
//        verify(productService, times(1)).updateAfterCancel(anyString(), any());
//        verify(orderMapper, times(1)).updateOrder(anyLong(), anyString());
//    }
//
//    @Test
//    void testUpdateNonExistingOrder() {
//        // Arrange
//        final long nonExistingOrderId = 99L;
//        final OrderDto givenOrderDto = OrderDto.builder().status("NEW_STATUS").build();
//        when(orderMapper.fetchOrder(nonExistingOrderId)).thenReturn(null);
//
//        // Act & Assert
//        assertThatThrownBy(() -> orderService.update(nonExistingOrderId, givenOrderDto))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("Given order does not exist");
//    }
//
//    @Test
//    void testUpdateOrderWithNullDto() {
//        // Arrange
//        final long orderId = 1L;
//
//        // Act & Assert
//        assertThrows(IllegalArgumentException.class, () -> orderService.update(orderId, null));
//    }
//
//    @Test
//    void testDeleteOrder() {
//        // Arrange
//        final long givenId = 1L;
//
//        // Act
//        Long result = orderService.deleteById(givenId);
//
//        // Assert
//        assertThat(result).isEqualTo(givenId);
//        verify(orderMapper, times(1)).deleteOrder(anyLong());
//    }
//
//    @Test
//    void testIsOrderExist() {
//        // Arrange
//        when(orderMapper.fetchOrder(anyLong())).thenReturn(Order.builder().build());
//
//        // Act
//        boolean result = orderService.isOrderExist(1L);
//
//        // Assert
//        assertThat(result).isTrue();
//    }
//
//    @Test
//    void testIsOrderNotExist() {
//        // Arrange
//        when(orderMapper.fetchOrder(anyLong())).thenReturn(null);
//
//        // Act
//        boolean result = orderService.isOrderExist(1L);
//
//        // Assert
//        assertThat(result).isFalse();
//    }
//}
