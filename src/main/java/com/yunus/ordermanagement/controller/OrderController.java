package com.yunus.ordermanagement.controller;

import com.yunus.ordermanagement.dto.CreateOrderRequest;
import com.yunus.ordermanagement.dto.UpdateOrderStatusRequest;
import com.yunus.ordermanagement.model.CustomerOrder;
import com.yunus.ordermanagement.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service) { this.service = service; }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public CustomerOrder create(@Valid @RequestBody CreateOrderRequest request) { return service.create(request); }
    @GetMapping public List<CustomerOrder> list(@RequestParam(required = false) String email) { return service.list(email); }
    @GetMapping("/{id}") public CustomerOrder get(@PathVariable Long id) { return service.get(id); }
    @PatchMapping("/{id}/status") public CustomerOrder updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateOrderStatusRequest request) { return service.updateStatus(id, request.status()); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
}
