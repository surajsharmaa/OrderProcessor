package com.ebook.orderprocessorservice.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LineItemTable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer lineItem_id;

	private String sku;

	private int quantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	OrderTable order;

	public Integer getLineItem_id() {
		return lineItem_id;
	}

	public void setLineItem_id(Integer lineItem_id) {
		this.lineItem_id = lineItem_id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderTable getOrder() {
		return order;
	}

	public void setOrder(OrderTable order) {
		this.order = order;
	}

}
