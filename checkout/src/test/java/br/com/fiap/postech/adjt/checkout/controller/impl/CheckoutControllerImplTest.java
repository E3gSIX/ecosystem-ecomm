package br.com.fiap.postech.adjt.checkout.controller.impl;

import br.com.fiap.postech.adjt.checkout.model.OrderEntity;
import br.com.fiap.postech.adjt.checkout.model.request.CheckoutRequest;
import br.com.fiap.postech.adjt.checkout.model.response.CheckoutResponse;
import br.com.fiap.postech.adjt.checkout.model.response.OrderCheckoutsResponse;
import br.com.fiap.postech.adjt.checkout.service.CheckoutService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CheckoutControllerImplTest {

	@Mock
	private CheckoutService checkoutService;

	@InjectMocks
	private CheckoutControllerImpl checkoutController;

	private UUID consumerId;
	private UUID orderId;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		consumerId = UUID.randomUUID();
		orderId = UUID.randomUUID();
	}

	@Test
	void createCheckout_success() {
		CheckoutRequest checkoutRequest = new CheckoutRequest();
		checkoutRequest.setConsumerId(consumerId.toString());
		checkoutRequest.setAmount(100);
		checkoutRequest.setCurrency("BRL");
		CheckoutResponse mockResponse = new CheckoutResponse(orderId.toString(), "success");
		when(checkoutService.processCheckout(any(UUID.class), any(Integer.class), any(String.class), any()))
				.thenReturn(mockResponse);
		ResponseEntity<CheckoutResponse> response = checkoutController.createCheckout(checkoutRequest);
		assertEquals(200, response.getStatusCode().value());
		assertEquals("success", response.getBody().getStatus());
		assertEquals(orderId.toString(), response.getBody().getOrderId());
	}

	@Test
	void getOrdersByConsumerId_success() {
		OrderCheckoutsResponse mockOrder = new OrderCheckoutsResponse(orderId.toString(), null, "credit_card", 100.0,
				"paid");
		when(checkoutService.getOrdersByConsumerId(any(UUID.class))).thenReturn(List.of(mockOrder));
		ResponseEntity<List<OrderCheckoutsResponse>> response = checkoutController.getOrdersByConsumerId(consumerId);
		assertEquals(200, response.getStatusCode().value());
		assertEquals(1, response.getBody().size());
		assertEquals(orderId.toString(), response.getBody().get(0).getOrderId());
	}

	@Test
	void getOrderById_success() {
		OrderEntity mockOrder = new OrderEntity(orderId, consumerId, "credit_card", 100, "paid", null, null);
		when(checkoutService.getOrderById(any(UUID.class))).thenReturn(mockOrder);
		ResponseEntity<OrderEntity> response = checkoutController.getOrderById(orderId);
		assertEquals(200, response.getStatusCode().value());
		assertEquals(orderId, response.getBody().getOrderId());
	}
}