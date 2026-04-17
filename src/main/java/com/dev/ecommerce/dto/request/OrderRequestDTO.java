package com.dev.ecommerce.dto.request;

import com.dev.ecommerce.entity.enums.OrderStatus;

public record OrderRequestDTO(
	Long id,
	OrderStatus status) {
}
