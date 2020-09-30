package com.ebook.orderprocessorservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ebook.api.message.Email;
import com.ebook.api.message.ShippingMessage;
import com.ebook.orderprocessorservice.request.OrderRequest;
import com.ebook.orderprocessorservice.response.OrderResponse;

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

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/suraj")
	public String suraj() {
		return "Hello Suraj!";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/order")
    public ResponseEntity<OrderResponse> createEventException(@Valid @RequestBody OrderRequest message,
            @RequestHeader("token") String tokenFromRequest, BindingResult bindingResult)
            throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpStatus statusCode = HttpStatus.OK;;
		OrderResponse response = new OrderResponse();
		response.setOrderId(message.getFirstName());
		try {
			System.out.println("Sending an email message.");
		    jmsTemplate.convertAndSend("mailbox", new Email("info@example.com", "Hello " + message.getFirstName()));
		    jmsTemplate.convertAndSend("shipping", new ShippingMessage("info@example.com", "Delivered to  " + message.getFirstName()));
		}catch (Exception e) {
			System.out.println("Something went wrong!");
		}
		return new ResponseEntity<OrderResponse>(response, httpHeaders, statusCode);
		
	}
    
}