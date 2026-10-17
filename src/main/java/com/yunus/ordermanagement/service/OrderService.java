package com.yunus.ordermanagement.service;

import com.yunus.ordermanagement.dto.CreateOrderRequest;
import com.yunus.ordermanagement.exception.OrderNotFoundException;
import com.yunus.ordermanagement.model.CustomerOrder;
import com.yunus.ordermanagement.model.OrderStatus;
import com.yunus.ordermanagement.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {
    private final OrderRepository repository;
    public OrderService(OrderRepository repository) { this.repository = repository; }
    public CustomerOrder create(CreateOrderRequest request) {
        return repository.save(new CustomerOrder(request.customerName(), request.customerEmail(), request.totalAmount()));
    }
    @Transactional(readOnly = true)
    public CustomerOrder get(Long id) { return repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id)); }
    @Transactional(readOnly = true)
    public List<CustomerOrder> list(String email) {
        return (email == null || email.isBlank()) ? repository.findAll() : repository.findByCustomerEmailIgnoreCase(email);
    }
    public CustomerOrder updateStatus(Long id, OrderStatus status) {
        CustomerOrder order = get(id); order.setStatus(status); return repository.save(order);
    }
    public void delete(Long id) { if (!repository.existsById(id)) throw new OrderNotFoundException(id); repository.deleteById(id); }
}
