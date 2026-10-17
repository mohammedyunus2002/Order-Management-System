package com.yunus.ordermanagement.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "customer_orders")
public class CustomerOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120)
    private String customerName;
    @Column(nullable = false, length = 180)
    private String customerEmail;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20)
    private OrderStatus status = OrderStatus.CREATED;
    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    protected CustomerOrder() {}
    public CustomerOrder(String customerName, String customerEmail, BigDecimal totalAmount) {
        this.customerName = customerName; this.customerEmail = customerEmail; this.totalAmount = totalAmount;
        this.status = OrderStatus.CREATED; this.createdAt = Instant.now();
    }
    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public OrderStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCustomerName(String value) { customerName = value; }
    public void setCustomerEmail(String value) { customerEmail = value; }
    public void setTotalAmount(BigDecimal value) { totalAmount = value; }
    public void setStatus(OrderStatus value) { status = value; }
}
