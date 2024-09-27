package br.com.fiap.postech.adjt.checkout.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import br.com.fiap.postech.adjt.checkout.model.OrderEntity;
import br.com.fiap.postech.adjt.checkout.model.request.PaymentMethodRequest;
import br.com.fiap.postech.adjt.checkout.model.response.CheckoutResponse;
import br.com.fiap.postech.adjt.checkout.repository.OrderRepository;
import br.com.fiap.postech.adjt.checkout.service.CheckoutService;

@SpringBootTest
@ActiveProfiles("test")
class CheckoutServiceImplTest {

    @Autowired
    private CheckoutService checkoutService;

    @MockBean
    private OrderRepository orderRepository;

    private UUID consumerId;
    private PaymentMethodRequest paymentMethod;

    @BeforeEach
    void setUp() {
        consumerId = UUID.randomUUID();
        paymentMethod = new PaymentMethodRequest();
        paymentMethod.setType("credit_card");
    }

    @Test
    void shouldReturnPendingStatusWhenProcessCheckout_success() {
        OrderEntity mockOrder = new OrderEntity();
        mockOrder.setConsumerId(consumerId);
        mockOrder.setPaymentStatus("pending");

        when(orderRepository.save(any(OrderEntity.class))).thenReturn(mockOrder);

        CheckoutResponse response = checkoutService.processCheckout(consumerId, 100, "BRL", paymentMethod);
        assertEquals("pending", response.getStatus());
    }

    @Test
    void shouldThrowExceptionWhenOrderSaveFails() {
        when(orderRepository.save(any(OrderEntity.class)))
                .thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> checkoutService.processCheckout(consumerId, 100, "BRL", paymentMethod));
    }
}