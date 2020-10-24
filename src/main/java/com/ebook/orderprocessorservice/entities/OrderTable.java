package com.ebook.orderprocessorservice.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class OrderTable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer order_id;

	private String payment_type;

	private String card_last_four_digit;

	private String payment_status;

	private Date order_date = new Date();
	
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LineItemTable> lineItems;
	
	@OneToOne(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BillingInfoTable billingInfo;
	
	@OneToOne(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ShippingInfoTable shippingInfo;
	
	private String dataCenter;

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getCard_last_four_digit() {
		return card_last_four_digit;
	}

	public void setCard_last_four_digit(String card_last_four_digit) {
		this.card_last_four_digit = card_last_four_digit;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public List<LineItemTable> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItemTable> lineItems) {
		this.lineItems = lineItems;
	}

	public BillingInfoTable getBillingInfo() {
		return billingInfo;
	}

	public void setBillingInfo(BillingInfoTable billingInfo) {
		this.billingInfo = billingInfo;
	}

	public ShippingInfoTable getShippingInfo() {
		return shippingInfo;
	}

	public void setShippingInfo(ShippingInfoTable shippingInfo) {
		this.shippingInfo = shippingInfo;
	}

	public String getDataCenter() {
		return dataCenter;
	}

	public void setDataCenter(String dataCenter) {
		this.dataCenter = dataCenter;
	}


}
