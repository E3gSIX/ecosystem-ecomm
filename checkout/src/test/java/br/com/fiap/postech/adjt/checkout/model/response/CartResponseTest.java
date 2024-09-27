package br.com.fiap.postech.adjt.checkout.model.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class CartResponseTest {

	@Test
	void testAllArgsConstructor() {
		Long id = 1L;
		String customerId = "customer123";
		List<CartItemResponse> items = List.of(new CartItemResponse());
		CartResponse cartResponse = new CartResponse(id, customerId, items);
		assertEquals(id, cartResponse.getId());
		assertEquals(customerId, cartResponse.getCustomerId());
		assertEquals(items, cartResponse.getItems());
	}

	@Test
	void testPartialConstructor() {
		String customerId = "customer123";
		List<CartItemResponse> items = List.of(new CartItemResponse());
		CartResponse cartResponse = new CartResponse(customerId, items);
		assertNull(cartResponse.getId());
		assertEquals(customerId, cartResponse.getCustomerId());
		assertEquals(items, cartResponse.getItems());
	}

	@Test
	void testNoArgsConstructor() {
		CartResponse cartResponse = new CartResponse();
		assertNull(cartResponse.getId());
		assertNull(cartResponse.getCustomerId());
		assertNull(cartResponse.getItems());
	}

	@Test
	void testSettersAndGetters() {
		CartResponse cartResponse = new CartResponse();
		Long id = 1L;
		String customerId = "customer123";
		List<CartItemResponse> items = List.of(new CartItemResponse());
		cartResponse.setId(id);
		cartResponse.setCustomerId(customerId);
		cartResponse.setItems(items);
		assertEquals(id, cartResponse.getId());
		assertEquals(customerId, cartResponse.getCustomerId());
		assertEquals(items, cartResponse.getItems());
	}
}