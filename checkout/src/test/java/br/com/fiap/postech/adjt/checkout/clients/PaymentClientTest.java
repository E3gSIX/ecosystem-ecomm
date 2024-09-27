package br.com.fiap.postech.adjt.checkout.clients;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.fiap.postech.adjt.checkout.model.request.PaymentRequest;
import br.com.fiap.postech.adjt.checkout.model.response.CheckoutResponse;

class PaymentClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PaymentClient paymentClient;

    private PaymentRequest paymentRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentRequest = new PaymentRequest();
        paymentRequest.setOrderId("12345");
        paymentRequest.setAmount(100);
        paymentRequest.setCurrency("BRL");
    }

    @Test
    void shouldReturnApprovedStatusWhenProcessPayment_success() {
        CheckoutResponse mockResponse = new CheckoutResponse();
        mockResponse.setOrderId("12345");
        mockResponse.setStatus("approved");

        when(restTemplate.postForObject(any(String.class), any(PaymentRequest.class), any(Class.class)))
                .thenReturn(mockResponse);

        CheckoutResponse response = paymentClient.processPayment("apiKey", paymentRequest);
        assertEquals("approved", response.getStatus());
    }

    @Test
    void shouldThrowExceptionWhenProcessPaymentFails() {
        when(restTemplate.postForObject(any(String.class), any(PaymentRequest.class), any(Class.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        assertThrows(HttpClientErrorException.class, () -> paymentClient.processPayment("apiKey", paymentRequest));
    }
}