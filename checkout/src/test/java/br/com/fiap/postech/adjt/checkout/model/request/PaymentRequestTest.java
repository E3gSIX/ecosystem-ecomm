package br.com.fiap.postech.adjt.checkout.model.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentRequestTest {

    private PaymentRequest paymentRequest;

    @BeforeEach
    void setUp() {
        PaymentFieldsRequest fields = new PaymentFieldsRequest("1234567890123456", "12", "2025", "123", "John Doe");
        PaymentMethodRequest paymentMethod = new PaymentMethodRequest("credit_card", fields);
        paymentRequest = new PaymentRequest("123e4567-e89b-12d3-a456-426614174000", 100, "BRL", paymentMethod);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals("123e4567-e89b-12d3-a456-426614174000", paymentRequest.getOrderId());
        assertEquals(100, paymentRequest.getAmount());
        assertEquals("BRL", paymentRequest.getCurrency());
        assertEquals("credit_card", paymentRequest.getPayment_method().getType());
    }
}