package br.com.fiap.postech.adjt.checkout.model.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheckoutResponseTest {

    private CheckoutResponse checkoutResponse;

    @BeforeEach
    void setUp() {
        checkoutResponse = new CheckoutResponse("123e4567-e89b-12d3-a456-426614174000", "approved");
    }

    @Test
    void testGetters() {
        assertEquals("123e4567-e89b-12d3-a456-426614174000", checkoutResponse.getOrderId());
        assertEquals("approved", checkoutResponse.getStatus());
    }

    @Test
    void testSetters() {
        checkoutResponse.setOrderId("new-order-id");
        checkoutResponse.setStatus("pending");
        assertEquals("new-order-id", checkoutResponse.getOrderId());
        assertEquals("pending", checkoutResponse.getStatus());
    }
}