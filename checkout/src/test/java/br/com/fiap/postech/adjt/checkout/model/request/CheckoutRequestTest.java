package br.com.fiap.postech.adjt.checkout.model.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheckoutRequestTest {

	private CheckoutRequest checkoutRequest;

	@BeforeEach
	void setUp() {
		checkoutRequest = new CheckoutRequest();
		checkoutRequest.setConsumerId("123e4567-e89b-12d3-a456-426614174000");
		checkoutRequest.setAmount(100);
		checkoutRequest.setCurrency("BRL");
		checkoutRequest.setPaymentMethod(new PaymentMethodRequest("credit_card",
				new PaymentFieldsRequest("1234567890123456", "12", "2025", "123", "John Doe")));
	}

	@Test
	void testValidateConsumerId() {
		assertDoesNotThrow(() -> checkoutRequest.validateConsumerId());
	}

	@Test
	void testInvalidConsumerId() {
		checkoutRequest.setConsumerId("invalid-uuid");
		assertThrows(IllegalArgumentException.class, () -> checkoutRequest.validateConsumerId());
	}

	@Test
	void testGettersAndSetters() {
		assertEquals("123e4567-e89b-12d3-a456-426614174000", checkoutRequest.getConsumerId());
		assertEquals(100, checkoutRequest.getAmount());
		assertEquals("BRL", checkoutRequest.getCurrency());
		assertEquals("credit_card", checkoutRequest.getPaymentMethod().getType());
	}
}