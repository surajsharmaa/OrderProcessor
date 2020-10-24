package com.ebook.orderprocessorservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ebook.api.message.Email;
import com.ebook.api.message.ShippingMessage;
import com.ebook.orderprocessorservice.entities.BillingInfoTable;
import com.ebook.orderprocessorservice.entities.LineItemTable;
import com.ebook.orderprocessorservice.entities.OrderTable;
import com.ebook.orderprocessorservice.entities.ShippingInfoTable;
import com.ebook.orderprocessorservice.repositories.OrderRepository;
import com.ebook.orderprocessorservice.request.LineItem;
import com.ebook.orderprocessorservice.request.OrderRequest;
import com.ebook.orderprocessorservice.request.ShippingInfo;
import com.ebook.orderprocessorservice.response.OrderResponse;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class Controller {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private OrderRepository orderRepository;

	@RequestMapping("/suraj")
	public String suraj() {
		return "Hello Suraj!";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/order")
	public ResponseEntity<OrderResponse> createEventException(@Valid @RequestBody OrderRequest message) throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpStatus statusCode = HttpStatus.OK;
		
		OrderResponse response = new OrderResponse();
		
		try {

			OrderTable order = new OrderTable();
			order.setPayment_type(message.getPayment_type());
			order.setCard_last_four_digit(message.getCreditCardNumber().substring(message.getCreditCardNumber().length()-4));
			order.setPayment_status("Accept"); //accept all order for now
			
			//line items
			List<LineItemTable> lineItemTables  = new ArrayList<LineItemTable>();
			for(LineItem l:message.getLineItems()) {
				LineItemTable lineItemTable = new LineItemTable();
				lineItemTable.setQuantity(l.getQuantity());
				lineItemTable.setSku(l.getSku());
				lineItemTables.add(lineItemTable);
			}
			
			order.setLineItems(lineItemTables);
			
			//billing info
			BillingInfoTable billing = new BillingInfoTable();
			billing.setFirst_name(message.getFirst_name());
			billing.setLast_name(message.getLast_name());
			billing.setEmail(message.getEmail());
			billing.setPhone_number(message.getPhone_number());
			billing.setAddress1(message.getAddress1());
			billing.setCity(message.getCity());
			billing.setState(message.getState());
			billing.setZip_code(message.getZip_code());
			
			order.setBillingInfo(billing);
			
			//Shipping info
			ShippingInfoTable shipping = new ShippingInfoTable();
			ShippingInfo s = message.getShippingInfo();
			shipping.setRecipient_first_name(s.getRecipient_first_name());
			shipping.setRecipient_last_name(s.getRecipient_last_name());
			shipping.setRecipient_email(s.getRecipient_email());
			shipping.setRecipient_phone_number(s.getRecipient_phone_number());
			shipping.setRecipient_address1(s.getRecipient_address1());
			shipping.setRecipient_city(s.getRecipient_city());
			shipping.setRecipient_state(s.getRecipient_state());
			shipping.setRecipient_zip_code(s.getRecipient_zip_code());
			
			order.setShippingInfo(shipping);
			
			order.setDataCenter("a-");
			
			OrderTable newOrder = orderRepository.save(order);
			
			response.setOrderId(newOrder.getDataCenter()+newOrder.getOrder_id());

			System.out.println("Sending an email message.");
			jmsTemplate.convertAndSend("mailbox", createEmailMessage(message, s, newOrder));
			jmsTemplate.convertAndSend("shipping", createShippingMessage(s, newOrder));
			
		} catch (Exception e) {
			System.out.println("Something went wrong!"+ e);
		}
		return new ResponseEntity<OrderResponse>(response, httpHeaders, statusCode);

	}

	private Email createEmailMessage(OrderRequest message, ShippingInfo s, OrderTable newOrder) {
		Email email = new Email();
		email.setTo(s.getRecipient_email());
		email.setOrderId(newOrder.getDataCenter()+newOrder.getOrder_id());
		email.setFirst_name(message.getFirst_name());
		email.setRecipient_first_name(s.getRecipient_first_name());
		email.setRecipient_last_name(s.getRecipient_last_name());
		email.setRecipient_email(s.getRecipient_email());
		email.setRecipient_phone_number(s.getRecipient_phone_number());
		email.setRecipient_address1(s.getRecipient_address1());
		email.setRecipient_city(s.getRecipient_city());
		email.setRecipient_state(s.getRecipient_state());
		email.setRecipient_zip_code(s.getRecipient_zip_code());
		return email;
	}
	
	private ShippingMessage createShippingMessage(ShippingInfo s, OrderTable newOrder) {
		ShippingMessage shippingInfo = new ShippingMessage();
		shippingInfo.setOrderId(newOrder.getDataCenter()+newOrder.getOrder_id());
		shippingInfo.setRecipient_first_name(s.getRecipient_first_name());
		shippingInfo.setRecipient_last_name(s.getRecipient_last_name());
		shippingInfo.setRecipient_email(s.getRecipient_email());
		shippingInfo.setRecipient_phone_number(s.getRecipient_phone_number());
		shippingInfo.setRecipient_address1(s.getRecipient_address1());
		shippingInfo.setRecipient_city(s.getRecipient_city());
		shippingInfo.setRecipient_state(s.getRecipient_state());
		shippingInfo.setRecipient_zip_code(s.getRecipient_zip_code());
		return shippingInfo;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{orderId}")
	public ResponseEntity<OrderTable> getOrder(@PathVariable String orderId){
		HttpStatus statusCode = HttpStatus.OK;
        HttpHeaders httpHeaders = new HttpHeaders();
        OrderTable order = orderRepository.findById(Integer.parseInt(orderId)).orElse(null);
        if (order == null) {
            statusCode = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<OrderTable>(order, httpHeaders, statusCode);
        
        
	}

}