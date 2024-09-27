package br.com.fiap.postech.adjt.checkout.model.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentMethodRequestTest {

	private Validator validator;

	@BeforeEach
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void shouldPassValidationWhenAllFieldsAreValid() {
		PaymentFieldsRequest fields = new PaymentFieldsRequest("1234567812345678", "12", "2025", "123", "John Doe");
		PaymentMethodRequest paymentMethodRequest = new PaymentMethodRequest("credit_card", fields);
		Set<ConstraintViolation<PaymentMethodRequest>> violations = validator.validate(paymentMethodRequest);
		assertTrue(violations.isEmpty(), "There should be no validation errors");
	}

	@Test
	void shouldFailValidationWhenTypeIsBlank() {
		PaymentFieldsRequest fields = new PaymentFieldsRequest("1234567812345678", "12", "2025", "123", "John Doe");
		PaymentMethodRequest paymentMethodRequest = new PaymentMethodRequest("", fields);
		Set<ConstraintViolation<PaymentMethodRequest>> violations = validator.validate(paymentMethodRequest);
		assertEquals(1, violations.size(), "There should be one validation error");
		assertEquals("Payment type cannot be blank", violations.iterator().next().getMessage());
	}

	@Test
	void shouldFailValidationWhenFieldsAreNull() {
		PaymentMethodRequest paymentMethodRequest = new PaymentMethodRequest("credit_card", null);
		Set<ConstraintViolation<PaymentMethodRequest>> violations = validator.validate(paymentMethodRequest);
		assertEquals(1, violations.size(), "There should be one validation error");
		assertEquals("Payment fields cannot be null", violations.iterator().next().getMessage());
	}

	@Test
	void shouldFailValidationWhenFieldsAreInvalid() {
		PaymentFieldsRequest fields = new PaymentFieldsRequest("", "12", "2025", "123", "John Doe");
		PaymentMethodRequest paymentMethodRequest = new PaymentMethodRequest("credit_card", fields);
		Set<ConstraintViolation<PaymentMethodRequest>> violations = validator.validate(paymentMethodRequest);
		assertEquals(1, violations.size(), "There should be one validation error");
		assertEquals("Card number cannot be blank", violations.iterator().next().getMessage());
	}
}