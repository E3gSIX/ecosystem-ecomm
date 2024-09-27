package br.com.fiap.postech.adjt.checkout.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

class CartItemEntityTest {

	@Test
	void testAllArgsConstructor() {
		UUID id = UUID.randomUUID();
		Long itemId = 1L;
		int quantity = 5;
		CartItemEntity cartItemEntity = new CartItemEntity(id, itemId, quantity);
		assertEquals(id, cartItemEntity.getId());
		assertEquals(itemId, cartItemEntity.getItemId());
		assertEquals(quantity, cartItemEntity.getQuantity());
	}

	@Test
	void testNoArgsConstructor() {
		CartItemEntity cartItemEntity = new CartItemEntity();
		assertNull(cartItemEntity.getId());
		assertNull(cartItemEntity.getItemId());
		assertEquals(0, cartItemEntity.getQuantity());
	}

	@Test
	void testCustomConstructor() {
		Long itemId = 1L;
		int quantity = 5;
		CartItemEntity cartItemEntity = new CartItemEntity(itemId, quantity);
		assertNull(cartItemEntity.getId());
		assertEquals(itemId, cartItemEntity.getItemId());
		assertEquals(quantity, cartItemEntity.getQuantity());
	}

	@Test
	void testSettersAndGetters() {
		CartItemEntity cartItemEntity = new CartItemEntity();
		UUID id = UUID.randomUUID();
		Long itemId = 1L;
		int quantity = 5;
		cartItemEntity.setId(id);
		cartItemEntity.setItemId(itemId);
		cartItemEntity.setQuantity(quantity);
		assertEquals(id, cartItemEntity.getId());
		assertEquals(itemId, cartItemEntity.getItemId());
		assertEquals(quantity, cartItemEntity.getQuantity());
	}
}