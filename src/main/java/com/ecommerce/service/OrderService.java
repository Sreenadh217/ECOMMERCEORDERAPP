package main.java.com.ecommerce.service;

import com.ecommerce.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(Order order);
    Optional<Order> getOrderById(Long orderId);
    List<Order> getAllOrders();
    List<Order> getOrdersByCustomerEmail(String email);
    List<Order> getOrdersByStatus(Order.OrderStatus status);
    Order updateOrder(Long orderId, Order order);
    boolean deleteOrder(Long orderId);
    List<Order> getProductById(String product);


}
