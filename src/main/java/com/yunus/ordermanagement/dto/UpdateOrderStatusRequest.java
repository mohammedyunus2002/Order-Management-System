package com.yunus.ordermanagement.dto;

import com.yunus.ordermanagement.model.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusRequest(@NotNull OrderStatus status) {}
