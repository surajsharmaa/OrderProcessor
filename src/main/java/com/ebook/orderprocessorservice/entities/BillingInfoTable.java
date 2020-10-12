package com.ebook.orderprocessorservice.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class BillingInfoTable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer billing_info_id;

	private String first_name;
	
	private String last_name;
	
	private String email;
	
	private String phone_number;
	
	private String address1;
	
	private String city;
	
	private String state;
	
	private String zip_code;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    OrderTable order;

	public Integer getBilling_info_id() {
		return billing_info_id;
	}

	public void setBilling_info_id(Integer billing_info_id) {
		this.billing_info_id = billing_info_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public OrderTable getOrder() {
		return order;
	}

	public void setOrder(OrderTable order) {
		this.order = order;
	}

}
