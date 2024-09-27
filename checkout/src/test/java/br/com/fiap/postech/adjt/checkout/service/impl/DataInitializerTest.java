package br.com.fiap.postech.adjt.checkout.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.postech.adjt.checkout.clients.PaymentClient;
import br.com.fiap.postech.adjt.checkout.model.CardEntity;
import br.com.fiap.postech.adjt.checkout.model.OrderEntity;
import br.com.fiap.postech.adjt.checkout.model.response.CheckoutResponse;
import br.com.fiap.postech.adjt.checkout.repository.OrderRepository;

class DataInitializerTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentClient paymentClient;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private DataInitializer dataInitializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInitWithPendingOrders() throws Exception {
        // Arrange
        OrderEntity mockOrder = new OrderEntity();
        mockOrder.setOrderId(UUID.randomUUID());
        mockOrder.setValue(100);
        mockOrder.setPaymentStatus("pending");

        CardEntity mockCard = new CardEntity();
        mockCard.setNumber("1234567890123456");
        mockCard.setExpiration_month("12");
        mockCard.setExpiration_year("2025");
        mockCard.setCvv("123");
        mockCard.setName("John Doe");
        mockOrder.setCard(mockCard);
        when(orderRepository.findByPaymentStatus("pending")).thenReturn(List.of(mockOrder));
        CheckoutResponse mockResponse = new CheckoutResponse();
        mockResponse.setStatus("approved");
        when(paymentClient.processPayment(anyString(), any())).thenReturn(mockResponse);
        dataInitializer.init();
        verify(orderRepository, times(1)).findByPaymentStatus("pending");
        verify(paymentClient, times(1)).processPayment(anyString(), any());
        verify(orderRepository, times(1)).save(mockOrder);
        assertEquals("approved", mockOrder.getPaymentStatus());
    }

    @Test
    void testInitWithNoPendingOrders() {
        when(orderRepository.findByPaymentStatus("pending")).thenReturn(Collections.emptyList());
        dataInitializer.init();
        verify(orderRepository, times(1)).findByPaymentStatus("pending");
        verify(paymentClient, never()).processPayment(anyString(), any());
        verify(orderRepository, never()).save(any(OrderEntity.class));
    }

    @Test
    void testInitWithPaymentFailure() throws Exception {
        OrderEntity mockOrder = new OrderEntity();
        mockOrder.setOrderId(UUID.randomUUID());
        mockOrder.setValue(100);
        mockOrder.setPaymentStatus("pending");
        CardEntity mockCard = new CardEntity();
        mockCard.setNumber("1234567890123456");
        mockCard.setExpiration_month("12");
        mockCard.setExpiration_year("2025");
        mockCard.setCvv("123");
        mockCard.setName("John Doe");
        mockOrder.setCard(mockCard);
        when(orderRepository.findByPaymentStatus("pending")).thenReturn(List.of(mockOrder));
        when(paymentClient.processPayment(anyString(), any())).thenThrow(new RuntimeException("Payment failed"));
        dataInitializer.init();
        verify(orderRepository, times(1)).findByPaymentStatus("pending");
        verify(paymentClient, times(1)).processPayment(anyString(), any());
        verify(orderRepository, times(1)).save(mockOrder);
        assertEquals("declined", mockOrder.getPaymentStatus());
    }
}