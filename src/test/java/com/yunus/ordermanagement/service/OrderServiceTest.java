package com.yunus.ordermanagement.service;

import com.yunus.ordermanagement.dto.CreateOrderRequest;
import com.yunus.ordermanagement.model.CustomerOrder;
import com.yunus.ordermanagement.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock OrderRepository repository;
    @InjectMocks OrderService service;
    @Test void createSavesOrderWithCreatedStatus() {
        when(repository.save(any(CustomerOrder.class))).thenAnswer(invocation -> invocation.getArgument(0));
        CustomerOrder order = service.create(new CreateOrderRequest("Yunus", "yunus@example.com", new BigDecimal("499.00")));
        assertEquals("Yunus", order.getCustomerName());
        assertEquals(new BigDecimal("499.00"), order.getTotalAmount());
        assertEquals("CREATED", order.getStatus().name());
    }
}
