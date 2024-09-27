package br.com.fiap.postech.adjt.checkout.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.postech.adjt.checkout.model.OrderEntity;
import br.com.fiap.postech.adjt.checkout.model.request.PaymentMethodRequest;
import br.com.fiap.postech.adjt.checkout.model.response.CheckoutResponse;
import br.com.fiap.postech.adjt.checkout.model.response.OrderCheckoutsResponse;

class CheckoutServiceTest {

	@Mock
	private CheckoutService checkoutService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testProcessCheckout() {
		UUID consumerId = UUID.randomUUID();
		int amount = 100;
		String currency = "BRL";
		PaymentMethodRequest paymentMethod = new PaymentMethodRequest();
		paymentMethod.setType("credit_card");
		CheckoutResponse mockResponse = new CheckoutResponse();
		mockResponse.setStatus("approved");
		when(checkoutService.processCheckout(consumerId, amount, currency, paymentMethod)).thenReturn(mockResponse);
		CheckoutResponse response = checkoutService.processCheckout(consumerId, amount, currency, paymentMethod);
		assertNotNull(response);
		assertEquals("approved", response.getStatus());
		verify(checkoutService, times(1)).processCheckout(consumerId, amount, currency, paymentMethod);
	}

	@Test
	void testGetOrdersByConsumerId() {
		UUID consumerId = UUID.randomUUID();
		List<OrderCheckoutsResponse> mockOrderList = new ArrayList<>();
		OrderCheckoutsResponse mockOrder = new OrderCheckoutsResponse();
		mockOrder.setOrderId(UUID.randomUUID().toString());
		mockOrder.setPaymentStatus("completed");
		mockOrderList.add(mockOrder);
		when(checkoutService.getOrdersByConsumerId(consumerId)).thenReturn(mockOrderList);
		List<OrderCheckoutsResponse> orders = checkoutService.getOrdersByConsumerId(consumerId);
		assertNotNull(orders);
		assertFalse(orders.isEmpty());
		assertEquals("completed", orders.get(0).getPaymentStatus());
		verify(checkoutService, times(1)).getOrdersByConsumerId(consumerId);
	}

	@Test
	void testGetOrderById() {
		UUID orderId = UUID.randomUUID();
		OrderEntity mockOrder = new OrderEntity();
		mockOrder.setOrderId(orderId);
		mockOrder.setPaymentStatus("pending");
		when(checkoutService.getOrderById(orderId)).thenReturn(mockOrder);
		OrderEntity order = checkoutService.getOrderById(orderId);
		assertNotNull(order);
		assertEquals("pending", order.getPaymentStatus());
		verify(checkoutService, times(1)).getOrderById(orderId);
	}
}