package br.com.fiap.postech.adjt.checkout.model.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class OrderCheckoutsResponseTest {

	@Test
	void testAllArgsConstructor() {
		String orderId = "12345";
		List<CartResponse> items = List.of(new CartResponse());
		String paymentType = "Credit Card";
		double value = 100.0;
		String paymentStatus = "Completed";
		OrderCheckoutsResponse response = new OrderCheckoutsResponse(orderId, items, paymentType, value, paymentStatus);
		assertEquals(orderId, response.getOrderId());
		assertEquals(items, response.getItems());
		assertEquals(paymentType, response.getPaymentType());
		assertEquals(value, response.getValue());
		assertEquals(paymentStatus, response.getPaymentStatus());
	}

	@Test
	void testNoArgsConstructor() {
		OrderCheckoutsResponse response = new OrderCheckoutsResponse();
		assertNull(response.getOrderId());
		assertNull(response.getItems());
		assertNull(response.getPaymentType());
		assertEquals(0.0, response.getValue());
		assertNull(response.getPaymentStatus());
	}

	@Test
	void testSettersAndGetters() {
		OrderCheckoutsResponse response = new OrderCheckoutsResponse();
		String orderId = "12345";
		List<CartResponse> items = List.of(new CartResponse());
		String paymentType = "Credit Card";
		double value = 100.0;
		String paymentStatus = "Completed";
		response.setOrderId(orderId);
		response.setItems(items);
		response.setPaymentType(paymentType);
		response.setValue(value);
		response.setPaymentStatus(paymentStatus);
		assertEquals(orderId, response.getOrderId());
		assertEquals(items, response.getItems());
		assertEquals(paymentType, response.getPaymentType());
		assertEquals(value, response.getValue());
		assertEquals(paymentStatus, response.getPaymentStatus());
	}
}