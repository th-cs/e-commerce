package com.dev.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_order_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private int quantity;

	@Column(scale = 2, nullable = false)
	private double price;

	@ManyToOne
	@MapsId("orderId")
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Product product;

}
