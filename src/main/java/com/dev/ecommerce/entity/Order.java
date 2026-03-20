package com.dev.ecommerce.entity;

import com.dev.ecommerce.enums.OrderStatus;
import java.time.Instant;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Entity
@Table(name = "tb_orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Instant timestamp = Instant.now();

	@Column(nullable = false)
	private OrderStatus status = OrderStatus.WAITING_FOR_PAYMENT;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(
	mappedBy = "order",
	cascade = CascadeType.ALL)
	private Payment payment;
}
