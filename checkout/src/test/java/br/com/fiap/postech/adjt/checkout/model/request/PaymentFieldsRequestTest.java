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

class PaymentFieldsRequestTest {

	private Validator validator;

	@BeforeEach
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void shouldPassValidationWhenAllFieldsAreValid() {
		PaymentFieldsRequest paymentFieldsRequest = new PaymentFieldsRequest("1234567812345678", 
				"12", "2025", "123", "John Doe");
		Set<ConstraintViolation<PaymentFieldsRequest>> violations = validator.validate(paymentFieldsRequest);
		assertTrue(violations.isEmpty(), "There should be no validation errors");
	}

	@Test
	void shouldFailValidationWhenCardNumberIsBlank() {
		PaymentFieldsRequest paymentFieldsRequest = new PaymentFieldsRequest("", 
				"12", "2025", "123", "John Doe");
		Set<ConstraintViolation<PaymentFieldsRequest>> violations = validator.validate(paymentFieldsRequest);
		assertEquals(1, violations.size(), "There should be one validation error");
		assertEquals("Card number cannot be blank", violations.iterator().next().getMessage());
	}

	@Test
	void shouldFailValidationWhenExpirationMonthIsBlank() {
		PaymentFieldsRequest paymentFieldsRequest = new PaymentFieldsRequest("1234567812345678",
				"", "2025", "123", "John Doe");
		Set<ConstraintViolation<PaymentFieldsRequest>> violations = validator.validate(paymentFieldsRequest);
		assertEquals(1, violations.size(), "There should be one validation error");
		assertEquals("Expiration month cannot be blank", violations.iterator().next().getMessage());
	}

	@Test
	void shouldFailValidationWhenExpirationYearIsBlank() {
		PaymentFieldsRequest paymentFieldsRequest = new PaymentFieldsRequest("1234567812345678",
				"12", "", "123", "John Doe");
		Set<ConstraintViolation<PaymentFieldsRequest>> violations = validator.validate(paymentFieldsRequest);
		assertEquals(1, violations.size(), "There should be one validation error");
		assertEquals("Expiration year cannot be blank", violations.iterator().next().getMessage());
	}

	@Test
	void shouldFailValidationWhenCvvIsBlank() {
		PaymentFieldsRequest paymentFieldsRequest = new PaymentFieldsRequest("1234567812345678",
				"12", "2025", "", "John Doe");
		Set<ConstraintViolation<PaymentFieldsRequest>> violations = validator.validate(paymentFieldsRequest);
		assertEquals(1, violations.size(), "There should be one validation error");
		assertEquals("CVV cannot be blank", violations.iterator().next().getMessage());
	}

	@Test
	void shouldFailValidationWhenCardholderNameIsBlank() {
		PaymentFieldsRequest paymentFieldsRequest = new PaymentFieldsRequest("1234567812345678",
				"12", "2025", "123", "");
		Set<ConstraintViolation<PaymentFieldsRequest>> violations = validator.validate(paymentFieldsRequest);
		assertEquals(1, violations.size(), "There should be one validation error");
		assertEquals("Cardholder name cannot be blank", violations.iterator().next().getMessage());
	}
}