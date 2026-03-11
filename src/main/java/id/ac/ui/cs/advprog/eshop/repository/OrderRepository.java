package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Repository
public class OrderRepository {
    private final List<Order> orderData = new ArrayList<>();

    public Order save(final Order order) {
        Order savedOrder = null;
        int index = 0;
        for (final Order orderChecked : orderData) {
            if (orderChecked.getOrderId().equals(order.getOrderId())) {
                savedOrder = order;
                orderData.remove(index);
                orderData.add(index, savedOrder);
            }
            index += 1;
        }

        orderData.add(savedOrder);
        return savedOrder;
    }

    public Order findById(final String orderId) {
        Order orderToFind = null;
        for (final Order orderChecked : orderData) {
            if (orderChecked.getOrderId().equals(orderId)) {
                orderToFind = orderChecked;
            }
        }
        return orderToFind;
    }

    public List<Order> findAllByAuthor(final String author) {
        final List<Order> result = new ArrayList<>();
        for (final Order orderChecked : orderData) {
            if (orderChecked.getAuthor().equals(author)) {
                result.add(orderChecked);
            }
        }
        return result;
    }
}
