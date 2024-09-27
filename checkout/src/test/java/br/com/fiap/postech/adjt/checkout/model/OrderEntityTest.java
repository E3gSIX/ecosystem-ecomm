package br.com.fiap.postech.adjt.checkout.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class OrderEntityTest {

    private OrderEntity orderEntity;

    @BeforeEach
    void setUp() {
        CartItemEntity item1 = new CartItemEntity(1L, 2);
        CartItemEntity item2 = new CartItemEntity(2L, 3);
        orderEntity = new OrderEntity(UUID.randomUUID(), UUID.randomUUID(), "credit_card", 100, "pending", List.of(item1, item2), new CardEntity());
    }

    @Test
    void testGettersAndSetters() {
        assertNotNull(orderEntity.getOrderId());
        assertEquals("credit_card", orderEntity.getPaymentType());
        assertEquals(100, orderEntity.getValue());
        assertEquals("pending", orderEntity.getPaymentStatus());
        assertEquals(2, orderEntity.getItems().size());
    }
}