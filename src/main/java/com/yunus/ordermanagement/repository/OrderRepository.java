package com.yunus.ordermanagement.repository;

import com.yunus.ordermanagement.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
    List<CustomerOrder> findByCustomerEmailIgnoreCase(String customerEmail);
}
