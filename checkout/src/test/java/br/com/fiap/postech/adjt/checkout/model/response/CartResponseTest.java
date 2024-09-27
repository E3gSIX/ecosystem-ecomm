package br.com.fiap.postech.adjt.checkout.model.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CartResponseTest {

    private CartResponse cartResponse;

    @BeforeEach
    void setUp() {
        CartItemResponse item1 = new CartItemResponse(1L, 2);
        CartItemResponse item2 = new CartItemResponse(2L, 3);
        cartResponse = new CartResponse("123e4567-e89b-12d3-a456-426614174000", List.of(item1, item2));
    }

    @Test
    void testGettersAndSetters() {
        assertEquals("123e4567-e89b-12d3-a456-426614174000", cartResponse.getCustomerId());
        assertEquals(2, cartResponse.getItems().size());
        cartResponse.setCustomerId("new-customer-id");
        assertEquals("new-customer-id", cartResponse.getCustomerId());
    }
}