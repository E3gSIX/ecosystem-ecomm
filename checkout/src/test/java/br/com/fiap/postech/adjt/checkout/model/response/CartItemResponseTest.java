package br.com.fiap.postech.adjt.checkout.model.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CartItemResponseTest {

	@Test
	void testAllArgsConstructor() {
		Long id = 1L;
		Long itemId = 100L;
		Integer quantity = 2;
		CartItemResponse cartItemResponse = new CartItemResponse(id, itemId, quantity);
		assertEquals(id, cartItemResponse.getId());
		assertEquals(itemId, cartItemResponse.getItemId());
		assertEquals(quantity, cartItemResponse.getQuantity());
	}

	@Test
	void testPartialConstructor() {
		Long itemId = 100L;
		Integer quantity = 2;
		CartItemResponse cartItemResponse = new CartItemResponse(itemId, quantity);
		assertNull(cartItemResponse.getId());
		assertEquals(itemId, cartItemResponse.getItemId());
		assertEquals(quantity, cartItemResponse.getQuantity());
	}

	@Test
	void testSettersAndGetters() {
		CartItemResponse cartItemResponse = new CartItemResponse();
		Long id = 1L;
		Long itemId = 100L;
		Integer quantity = 2;
		cartItemResponse.setId(id);
		cartItemResponse.setItemId(itemId);
		cartItemResponse.setQuantity(quantity);
		assertEquals(id, cartItemResponse.getId());
		assertEquals(itemId, cartItemResponse.getItemId());
		assertEquals(quantity, cartItemResponse.getQuantity());
	}

	@Test
	void testNoArgsConstructor() {
		CartItemResponse cartItemResponse = new CartItemResponse();
		assertNull(cartItemResponse.getId());
		assertNull(cartItemResponse.getItemId());
		assertNull(cartItemResponse.getQuantity());
	}
}