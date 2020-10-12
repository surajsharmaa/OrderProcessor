package com.ebook.orderprocessorservice.request;

public class ShippingInfo {
	
	private String recipient_first_name;
	
	private String recipient_last_name;
	
	private String recipient_email;
	
	private String recipient_phone_number;
	
	private String recipient_address1;
	
	private String recipient_city;
	
	private String recipient_state;
	
	private String recipient_zip_code;

	public String getRecipient_first_name() {
		return recipient_first_name;
	}

	public void setRecipient_first_name(String recipient_first_name) {
		this.recipient_first_name = recipient_first_name;
	}

	public String getRecipient_last_name() {
		return recipient_last_name;
	}

	public void setRecipient_last_name(String recipient_last_name) {
		this.recipient_last_name = recipient_last_name;
	}

	public String getRecipient_email() {
		return recipient_email;
	}

	public void setRecipient_email(String recipient_email) {
		this.recipient_email = recipient_email;
	}

	public String getRecipient_phone_number() {
		return recipient_phone_number;
	}

	public void setRecipient_phone_number(String recipient_phone_number) {
		this.recipient_phone_number = recipient_phone_number;
	}

	public String getRecipient_address1() {
		return recipient_address1;
	}

	public void setRecipient_address1(String recipient_address1) {
		this.recipient_address1 = recipient_address1;
	}

	public String getRecipient_city() {
		return recipient_city;
	}

	public void setRecipient_city(String recipient_city) {
		this.recipient_city = recipient_city;
	}

	public String getRecipient_state() {
		return recipient_state;
	}

	public void setRecipient_state(String recipient_state) {
		this.recipient_state = recipient_state;
	}

	public String getRecipient_zip_code() {
		return recipient_zip_code;
	}

	public void setRecipient_zip_code(String recipient_zip_code) {
		this.recipient_zip_code = recipient_zip_code;
	}

}
